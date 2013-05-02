package game;

import game.model.entity.Asteroid;
import game.model.entity.Bullet;
import game.model.entity.Entity;
import game.model.entity.Spaceship;

import java.util.ArrayList;

public class Game {
	private static final int MAX_TIME_BETWEEN_WAVES=10000;//in ms
	private static final int MIN_TIME_BETWEEN_WAVES=5000;
	private static final int MAX_ASTEROIDS_PER_WAVE = 3;
	private static final int MIN_ASTEROIDS_PER_WAVE = 1;
	private static final int ASTEROIDS_CAP=3;
	private long time=0;
	private long lastTime=0;

	private ArrayList<Spaceship> spaceships;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Bullet> bullets;
	private final int height;
	private final int width;
	private long nextWave=-1;

	public Game(int width, int height){
		this.spaceships=new ArrayList<Spaceship>();
		this.asteroids=new ArrayList<Asteroid>();
		this.bullets=new ArrayList<Bullet>();
		this.height=height;
		this.width=width;
	}

	public Spaceship getShip(String playerName){
		for(int i=0; i<spaceships.size();i++){
			if(spaceships.get(i).getPlayerName()==playerName)
				return spaceships.get(i);
		}
		return null;
	}
	public ArrayList<Spaceship> getShips(){
		return spaceships;
	}

	public ArrayList<Entity> getEntities(){
		ArrayList<Entity> entities=new ArrayList<Entity>();
		entities.addAll(spaceships);
		entities.addAll(asteroids);
		entities.addAll(bullets);
		return entities;
	}

	public void updateTime(long currentTime){
		lastTime=time;
		time=currentTime;
	}
	private long getDelta(){
		return time-lastTime;
	}

	public void gameLoop(){
		for(int i=0;i<getEntities().size();i++){
			Entity current = getEntities().get(i);
			current.updatePosition(getDelta());
			current.updateSpeed(getDelta());
		}
		processCollisions();
		asteroidsWave(time);
	}

	private void processCollisions(){
		ArrayList<Entity> entities= this.getEntities();
		for(int i=0;i<entities.size();i++){
			for(int j=i+1;j<entities.size();j++){
				if(entities.get(i).collides(entities.get(j))){
					System.out.println(entities.get(i) + " and "+ entities.get(j)+" collided");
					entities.get(i).onCollision(entities.get(j));
					entities.get(j).onCollision(entities.get(i));
				}
			}
		}
	}

	public void executeCommand(Command com, Entity entity){
		switch(com){
			case GO_FORWARD:
				entity.increaseMomentum(getDelta());
				break;
			case GO_BACKWARD:
				entity.increaseMomentum(getDelta(), true);
				break;
			case RESET_MOMENTUM:
				//System.out.println("reset");
				entity.resetMomentum();
				break;
			case TURN_LEFT:
				entity.rotate(getDelta(), true);
				break;
			case TURN_RIGHT:
				entity.rotate(getDelta(), false);
				break;
			case FIRE:
				System.out.println("firing");
				if(entity.getClass()==Spaceship.class){
					Spaceship player= (Spaceship) entity;
					if(player.canFire(time)){
						player.updateFireTime(time);
						this.bullets.add(new Bullet(player));
					}
				}
				break;
		}
	}

	public void asteroidsWave(long currentTime){
		if(nextWave==-1)
			nextWave=(long)(currentTime+MIN_TIME_BETWEEN_WAVES+Math.random()*(MAX_TIME_BETWEEN_WAVES-MIN_TIME_BETWEEN_WAVES));
		else if(currentTime>=nextWave){
			int asteroids=MIN_ASTEROIDS_PER_WAVE+(int)Math.round(Math.random()*(MAX_ASTEROIDS_PER_WAVE-MIN_ASTEROIDS_PER_WAVE));
			for(int i=0;i<asteroids && ASTEROIDS_CAP>this.asteroids.size();i++){
				this.asteroids.add(new Asteroid((int)(Math.random()*this.width),(int) (Math.random()*this.height)));
			}
			nextWave=(long)(currentTime+MIN_TIME_BETWEEN_WAVES+Math.random()*(MAX_TIME_BETWEEN_WAVES-MIN_TIME_BETWEEN_WAVES));
		}
	}

	public void addPlayer(String id){
		this.spaceships.add(new Spaceship(0,0,id));
	}

}