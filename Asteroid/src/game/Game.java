package game;

import game.model.entity.Asteroid;
import game.model.entity.Asteroid.Size;
import game.model.entity.Bullet;
import game.model.entity.Entity;
import game.model.entity.Spaceship;

import java.util.ArrayList;
/*
 * Contrôleur.
 * Se charge de garder une trace en mémoire de toutes les entités.
 * Contient la boucle de jeu 
 */

public class Game {
	//game constants & hardcoded params
	private static final int MAX_TIME_BETWEEN_WAVES=10000;//in ms
	private static final int MIN_TIME_BETWEEN_WAVES=5000;
	private static final int MAX_ASTEROIDS_PER_WAVE = 3+Settings.DIFFICULTY;
	private static final int MIN_ASTEROIDS_PER_WAVE = 1;
	private static int ASTEROIDS_CAP=5*(Settings.DIFFICULTY+1);
	
	private long time=0;//currentloop time
	private long lastTime=0;// last loop time
	//liste des entités
	private ArrayList<Spaceship> spaceships;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Bullet> bullets;
	private long nextWave=-1; //timestamp de la prochaine vague d 'asteroides
	
	//taille de la carte
	private final int height;
	private final int width;

	public Game(int width, int height){
		this.spaceships=new ArrayList<Spaceship>();
		this.asteroids=new ArrayList<Asteroid>();
		this.bullets=new ArrayList<Bullet>();
		this.height=height;
		this.width=width;
	}
	
	
	//détruit toutes les entités pour recommencer une partie
	public void clear(){
		this.spaceships=new ArrayList<Spaceship>();
		this.asteroids=new ArrayList<Asteroid>();
		this.bullets=new ArrayList<Bullet>();
	}

	//retrouve un vaisseau à partir du nom du propriétaire
	public Spaceship getShip(String playerName){
		for(int i=0; i<spaceships.size();i++){
			if(spaceships.get(i).getPlayerName()==playerName)
				return spaceships.get(i);
		}
		return null;
	}
	
	//retourne la liste complète des vaisseaux
	public ArrayList<Spaceship> getShips(){
		return spaceships;
	}

	//retourne la liste complète des entités
	public ArrayList<Entity> getEntities(){
		ArrayList<Entity> entities=new ArrayList<Entity>();
		entities.addAll(spaceships);
		entities.addAll(asteroids);
		entities.addAll(bullets);
		return entities;
	}

	//met à jour le delta depuis la dernière boucle de jeu
	public void updateTime(long currentTime){
		lastTime=time;
		time=currentTime;
	}
	//retourne le temps actuel
	public long getTime(){
		return time;
	}
	//retourne le delta depuis la dernière boucle de jeu
	private long getDelta(){
		return time-lastTime;
	}

	/*
	 *Boucle de jeu
	 * -suppression des bullets hors map 
	 * -suppression des entités précedement détruites
	 * -update des positions
	 * -update des vitesses
	 * -vague d'astéroide  
	 */
	public void gameLoop(){
		for(int i=0;i<getEntities().size();i++){
			Entity current = getEntities().get(i);
			if(current instanceof Bullet){
				if(current.getX() <= 0 || current.getY() <= 0 || current.getX() >= Settings.WIDTH || current.getY() >= Settings.HEIGHT)
					current.destroy();
			}
			if(!current.isDestroyed()){
				current.updatePosition(getDelta());
				current.updateSpeed(getDelta());
			}else{
				spaceships.remove(current);
				asteroids.remove(current);
				bullets.remove(current);
			}
		}
		processCollisions();
		asteroidsWave(time);
	}

	//vérification des collisions & appel de onCollision le cas échéant
	private void processCollisions(){
		ArrayList<Entity> entities= this.getEntities();
		for(int i=0;i<entities.size();i++){
			for(int j=i+1;j<entities.size();j++){
				if(entities.get(i).collides(entities.get(j))){
					if(entities.get(i).hasPoped() && entities.get(j).hasPoped()){
						entities.get(i).onCollision(entities.get(j));
						entities.get(j).onCollision(entities.get(i));
					}
				}
			}
		}
	}
	
	//executes the command com on entity entity
	public void executeCommand(Command com, Entity entity){
		switch(com){
			case GO_FORWARD:
				entity.increaseMomentum(getDelta());
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
				Spaceship player= (Spaceship) entity;
				if(player.canFire(time)){
					player.updateFireTime(time);
					this.bullets.add(new Bullet(player));
				}
				break;
		}
	}

	//si currentTime>temps de la prochaine vague -> nouveau temps + générer une vague
	public void asteroidsWave(long currentTime){
		if(nextWave==-1)
			nextWave=(long)(currentTime+MIN_TIME_BETWEEN_WAVES+Math.random()*(MAX_TIME_BETWEEN_WAVES-MIN_TIME_BETWEEN_WAVES));
		else if(currentTime>=nextWave){
			int asteroids=MIN_ASTEROIDS_PER_WAVE+(int)Math.round(Math.random()*(MAX_ASTEROIDS_PER_WAVE-MIN_ASTEROIDS_PER_WAVE));
			for(int i=0;i<asteroids && ASTEROIDS_CAP>this.asteroids.size();i++){
				double d = Math.random(); 
				Size s; if(d<1/4d) s=Size.SMALL; else if(d>3/4d)s=Size.BIG; else s=Size.NORMAL;
				this.asteroids.add(new Asteroid((int)(Math.random()*this.width),(int)(Math.random()*this.height),s));
			}
			nextWave=(long)(currentTime+MIN_TIME_BETWEEN_WAVES+Math.random()*(MAX_TIME_BETWEEN_WAVES-MIN_TIME_BETWEEN_WAVES));
		}
	}
	
	//ajouter un spaceship à la partie (id est le nom du joueur propriétaire)
	public void addPlayer(String id){
		Spaceship player=null;
		if(id.equals(Settings.PLAYER1)){
			player =new Spaceship(100,100,id,Settings.SUPERMODE);
			player.setColor(1.0f, .0f, .0f);
		}else if(id.equals(Settings.PLAYER2)){
			player =new Spaceship(Settings.WIDTH-100,Settings.HEIGHT-100,id,Settings.SUPERMODE);
			player.setColor(.2f, 1.0f, 1.0f);
			ASTEROIDS_CAP = ASTEROIDS_CAP*2;
		}
		if(player!=null)
			this.spaceships.add(player);
	}
}
