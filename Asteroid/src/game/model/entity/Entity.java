package game.model.entity;

import game.model.Sprite;
import game.model.Vector2D;

import java.awt.Rectangle;

public abstract class Entity {
	protected float x;
	protected float y;
	protected int orientation;
	protected Vector2D velocity;
	protected int momentum;
	protected Rectangle collisionBox;
	protected Sprite sprite;
	
	protected Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//TODO
	public void move(long delta){
		
	}
	
	//TODO
	public void setMovement(Vector2D speed){
		
	}
	
	//TODO
	public Vector2D getMovement(){
		return velocity;
	}
	
	//TODO
	public int getX(){
		return (int) x;
	}
	
	//TODO
	public int getY(){
		return (int) y;
	}
	
	//TODO
	public boolean collision(Entity other){
		return false;
	}
	
	//TODO
	public void decreaseVelocity(long delta){
		
	}
	
	//TODO
	public void increaseMomentum(long delta){
		
	}
	
	//TODO
	public void rotate(int angle){
		
	}
}
