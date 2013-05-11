package game.model.entity;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import game.Settings;
import game.util.Polygon;
import game.util.Vector2D;

public class Spaceship extends Entity {
	private String playerName;
	private int score=0;
	private long lastFired;
	private int  FIRE_COOLDOWN;//in ms
	private int[] SPACESHIP_X = {0*2,2*2,1*2,3*2,6*2,9*2,6*2,5*2,7*2,9*2,11*2,12*2,15*2,17*2,16*2,17*2,16*2,17*2,15*2,12*2,11*2,9*2,7*2,5*2,6*2,9*2,6*2,3*2,1*2,2*2,0*2};
	private int[] SPACESHIP_Y = {9*2,6*2,3*2,2*2,0*2,2*2,3*2,6*2,6*2,5*2,6*2,6*2,8*2,7*2,8*2,9*2,10*2,11*2,10*2,12*2,12*2,13*2,12*2,12*2,15*2,16*2,18*2,16*2,15*2,12*2,9*2};
	private int[] SPACESHIP_BAH_X = {0,27,0,8,0};
	private int[] SPACESHIP_BAH_Y = {0,15,30,15,0};
	private AudioClip currentSound;
	private boolean isSuperMode;

	public Spaceship(int x, int y, String playerName, boolean superMode){
		super(x, y);
		this.playerName=playerName;
		lastFired=0;
		FIRE_COOLDOWN=200;
		this.isSuperMode = superMode;
		if(superMode){
			this.setCollisionBox(new Polygon(SPACESHIP_X, SPACESHIP_Y));
		}
		else{
			this.setCollisionBox(new Polygon(SPACESHIP_BAH_X, SPACESHIP_BAH_Y));
		}
	}
	
	public boolean isSuperMode(){
		return isSuperMode;
	}
	
	public int firePosition(){
		if(isSuperMode){
			return 15;
		}
		else
			return 1;
	}

	@Override
	public boolean equals(Object o){
		return (o.getClass() == Spaceship.class && this.getPlayerName()== ((Spaceship) o).getPlayerName());
	}

	public String getPlayerName(){
		return playerName;
	}
	
	public int getScore(){
		return score;
	}
	
	public void score(int points){
		score+=points;
	}

	public boolean canFire(long currentTime){
		if(currentTime-lastFired>=FIRE_COOLDOWN){
			return true;
		}
		else
			return false;
	}

	public void updateFireTime(long time){
		this.lastFired=time;
	}

	@Override
	public void onDestroy() {
		
	}

	@Override
	public void onCollision(Entity otherEntity) {
		if(otherEntity instanceof Bullet && ((Bullet) otherEntity).thrower.equals(this)){
			
		}
		else{
			//this.destroy();
			if(score >= 10)
				this.score(-10);
			else
				score = 0;
			lastFired=0;
			if(playerName.equals(Settings.PLAYER2)){
				reset(Settings.WIDTH-100,Settings.HEIGHT-100);
			}
			else{
				reset(100,100);
			}
			URL path = getClass().getResource("explosion.wav");
			try{
				currentSound = Applet.newAudioClip(path);
				currentSound.play();
			}catch(Exception e){e.printStackTrace();}
			if(isSuperMode){
				setCollisionBox(new Polygon(SPACESHIP_X, SPACESHIP_Y));
			}
			else{
				setCollisionBox(new Polygon(SPACESHIP_BAH_X, SPACESHIP_BAH_Y));
			}
		}
	}
}