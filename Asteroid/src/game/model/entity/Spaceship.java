package game.model.entity;

import game.model.Vector2D;

public class Spaceship extends Entity {
	private String playerName;
	private long lastFired;
	
	//TODO
	public Spaceship(float x, float y, String playerName, Vector2D velocity, int momentum){
		super(x, y);
	}
	
	//TODO
	public Spaceship(float x, float y, String playerName){
		super(x, y);
	}
	
	//TODO
	public boolean collision(Entity other){
		return false;
	}
	
	//TODO
	public void lastFired(){
		
	}
	
	//TODO
	public boolean canFire(){
		return false;
	}
}
