package game.model.entity;

import game.util.Vector2D;
public abstract class Entity {
	protected float SPEED_WEAROFF_RATE = -.02f;//in units/ms
	protected float MOMENTUM_INCREASE_RATE = .03f;//in units/ms^2
	protected float DEFAULT_MOMENTUM = .3f;
	protected float ANGULAR_VELOCITY = .002f; //in rad/ms
	private boolean destroyed = false; 
	private int x; //position on X-axis
	private int y; //position on Y-axis
	private Vector2D momentum = new Vector2D(0,0); //vector representing momentum (always the same orientation as the entity)
	private Vector2D velocity = new Vector2D(0,0); //vector representing velocity
	private boolean backward=false;
	
	protected Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	protected Entity(int x, int y, Vector2D velocity){
		this.x = x;
		this.y = y;
		this.velocity=velocity;
	}
	protected Entity(int x, int y, Vector2D velocity, Vector2D momentum){
		this.x = x;
		this.y = y;
		this.velocity=velocity;
		this.momentum=momentum;
	}
	
	protected Vector2D getMomentum(){return momentum;}
	protected Vector2D getVelocity(){return velocity;}
	public float getOrientation(){
		float momentumDirection = getMomentum().getT();
		if(backward)
			momentumDirection+=Math.PI;
		while(momentumDirection>=2*Math.PI){
			momentumDirection-=2*Math.PI;
		}
		while(momentumDirection<0){
			momentumDirection+=2*Math.PI;
		}
		return momentumDirection;
	}		
	public int getX(){return x;}
	public int getY(){return y;}
	public void destroy(){
		destroyed=true; 
		this.onDestroy();
	}
	public boolean isDestroyed(){return destroyed;}
	
	/** moves the entity according to it's current speed vector and the time delta*/
	public void updatePosition(long delta){
		x+=delta*getVelocity().getX();
		y+=delta*getVelocity().getY();
	}
	
	/** updates the speed according to acceleration, velocity decay and time delta*/
	public void updateSpeed(long delta){
		//decreases velocity
		velocity.lengthen(delta*SPEED_WEAROFF_RATE);
		//adds momentum to velocity
		velocity.add(Vector2D.toScale(momentum, delta));
		resetMomentum(); // TODO delete this line when variable momentum system is fixed
	}

	public void increaseMomentum(long delta, boolean backward){
		/*if(!backward)
			momentum.lengthen(MOMENTUM_INCREASE_RATE*delta);
		else
			momentum.lengthen(-MOMENTUM_INCREASE_RATE*delta);
		this.backward=backward;
		*/
		if(!backward)
			momentum.setR(DEFAULT_MOMENTUM); //TODO variable momentum? - adapt anyway
		else
			momentum.setR(-DEFAULT_MOMENTUM);
		this.backward=backward;
	}	
	public void increaseMomentum(long delta){
		this.increaseMomentum(delta, false);
	}
	
	public void resetMomentum(){
		momentum.scale(0);
	}
	
	/**adds to the orientation of the entity according to time delta and fixed rate - keeps it in [0,2Pi[*/
	public void rotate(long delta, boolean counterClockwise){
		if(counterClockwise){
			momentum.rotate(delta*this.ANGULAR_VELOCITY);
		}
		else{
			momentum.rotate(-delta*this.ANGULAR_VELOCITY);
		}
	}
	public void setOrientation(float angle){
		momentum.rotate(momentum.getT()+angle);
	}
	
	public abstract void onDestroy();
	public abstract void onCollision(Entity otherEntity);
}
