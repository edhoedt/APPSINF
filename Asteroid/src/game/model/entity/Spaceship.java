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
	private int[] SPACESHIP_X = {0,27,0,8,0};//{-13,14,-13,-5,-13};
	private int[] SPACESHIP_Y = {0,15,30,15,0};//{-15,0,15,0,-15};
	private AudioClip currentSound;

	public Spaceship(int x, int y, String playerName){
		super(x, y);
		this.playerName=playerName;
		lastFired=0;
		FIRE_COOLDOWN=200;
		this.setCollisionBox(new Polygon(SPACESHIP_X, SPACESHIP_Y));
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
				super.reset(Settings.WIDTH-100,Settings.HEIGHT-100);
			}
			else{
				super.reset(100,100);
			}
			URL path = getClass().getResource("explosion.wav");
			try{
				currentSound = Applet.newAudioClip(path);
				currentSound.play();
			}catch(Exception e){e.printStackTrace();}
			this.setCollisionBox(new Polygon(SPACESHIP_X, SPACESHIP_Y));
		}
	}
}