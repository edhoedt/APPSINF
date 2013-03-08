package game.model.entity;

import game.util.Vector2D;
public abstract class Entity {
	private final static int SPEED_DECREASE_RATE = 10;//in px/s
	private final static int MOMENTUM_INCREASE_RATE = 10;//in px/s^2
	private final static int MOMENTUM_DECREASE_RATE = MOMENTUM_INCREASE_RATE;//in px/s^2
	private final static float ANGULAR_VELOCITY = (float) 2; //in rad/s
	private boolean destroyed = false; 
	protected float x; //in px
	protected float y; //in px
	protected float orientation; //in rad
	protected Vector2D velocity; //vector representing velocity
	protected float momentum;//represents a speed increase rate
	
	protected Entity(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/** moves the entity according to it's current speed vector and the time delta*/
	public void move(long delta){
		x+=velocity.x*delta;
		y+=velocity.y*delta;
	}
	
	/** flags the entity for destruction*/
	public void destroy(){
		destroyed=true;
	}
	
	/** true if the entity is flagged for destruction*/
	public boolean isDestroyed(){
		return destroyed;
	}
	
	/**sets a speed vector*/
	public void setMovement(Vector2D speed){
		velocity=speed;
	}
	
	/**returns the speed vector*/
	public Vector2D getMovement(){
		return velocity;
	}
	
	/** returns rounded x position*/
	public int getX(){
		return (int) x;
	}
	
	/** returns rounded y position*/
	public int getY(){
		return (int) y;
	}
	
	/**decreases the velocity vector according to time delta and fixed rate*/
	public void decreaseVelocity(long delta){
		velocity.addToVector(-delta*SPEED_DECREASE_RATE);
	}
	
	/**increases the momentum according to time delta and fixed rate*/
	public void increaseMomentum(long delta){
		momentum+=delta*MOMENTUM_INCREASE_RATE;
	}
	
	/**decreases the momentum according to time delta and fixed rate*/
	public void decreaseMomentum(long delta){
		momentum-=delta*MOMENTUM_DECREASE_RATE;
	}
	
	/**adds to the orientation of the entity according to time delta and fixed rate - keeps it in [0,2Pi[*/
	public void rotate(long delta, boolean counterClockwise){
		if(counterClockwise)orientation+=delta*ANGULAR_VELOCITY;
		else orientation-=delta*ANGULAR_VELOCITY;
		if(orientation<0)orientation+=2*Math.PI; 
		else if (orientation>=2*Math.PI)orientation -= 2*Math.PI;
	}
}
