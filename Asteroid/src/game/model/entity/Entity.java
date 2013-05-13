package game.model.entity;

import game.Settings;
import game.util.Polygon;
import game.util.Vector2D;

public abstract class Entity {
	protected float SPEED_WEAROFF_RATE = .0002f;//in units/ms
	//protected float MOMENTUM_INCREASE_RATE = .0005f;//in units/ms^2
	protected float DEFAULT_MOMENTUM = .0004f;
	protected float ANGULAR_VELOCITY = .005f - 0.001f*Settings.DIFFICULTY; //in rad/ms
	protected boolean destroyed = false; 
	private int MAX_BOUNDS = 15;
	private double x; //position on X-axis
	private double y; //position on Y-axis
	private Vector2D momentum = new Vector2D(0,0); //vector representing momentum (always the same orientation as the entity)
	private Vector2D velocity = new Vector2D(0,0); //vector representing velocity
	private boolean backward=false;
	private boolean poped=false; //
	private Polygon collisionBox;
	private float colorR=.0f, colorG=1.0f, colorB=.0f;
	private int GHOST_MAXTIME=0;
	private int GHOST_TIMELEFT=0;

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
	
	public void pop(){
		this.poped=true;
	}
	public boolean hasPoped(){
		return this.poped;
	}
	
	public void setColor(float r, float g, float b){
		this.colorR=r;
		this.colorG=g;
		this.colorB=b;
	}
	
	public float[] getColor(){
		float[] color = {0f,0f,0f};
		if(this.hasPoped()){
			color[0]=this.colorR;
			color[1]=this.colorG;
			color[2]=this.colorB;
		}else{
			color[0]=.54f;
			color[1]=.54f;
			color[2]=.54f;
		}
		return color;
	}
	
	protected void setGhostTime(int maxTime){
		this.GHOST_MAXTIME=maxTime;
		this.GHOST_TIMELEFT=this.GHOST_MAXTIME;
	}
	
	public void reset(int x, int y){
		this.velocity=new Vector2D(0,0);
		this.momentum=new Vector2D(0,0);
		this.x=x;
		this.y=y;
		this.destroyed=false;
		this.GHOST_TIMELEFT=this.GHOST_MAXTIME;
		this.poped=false;
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
	public int getX(){return (int) (x+.5d);}
	public int getY(){return (int) (y+.5d);}
	public void destroy(){
		destroyed=true; 
		this.onDestroy();
	}
	public Polygon getCollisionBox(){return this.collisionBox;}
	public boolean isDestroyed(){return destroyed;}
	public boolean collides(Entity other){
		return collisionBox.intersects(other.getCollisionBox());
	}
	public void setCollisionBox(Polygon box){
		this.collisionBox=box;
	}

	/** moves the entity according to it's current speed vector and the time delta*/
	public void updatePosition(long delta){
		this.GHOST_TIMELEFT-=delta;
		if(this.GHOST_TIMELEFT<0){
			this.pop();
		}
		if(x > Settings.WIDTH+MAX_BOUNDS){
			x = 0+MAX_BOUNDS+5;
		}
		else if(x < 1){
			x = Settings.WIDTH-MAX_BOUNDS-5;
		}
		if(y > Settings.HEIGHT+MAX_BOUNDS){
			y = 0+MAX_BOUNDS+5;
		}
		else if(y < 1){
			y = Settings.HEIGHT-MAX_BOUNDS-5;
		}
		x+=delta*getVelocity().getX();
		y+=delta*getVelocity().getY();
		this.collisionBox.moveTo(this.getX(), this.getY());
	}

	/** updates the speed according to acceleration, velocity decay and time delta*/
	public void updateSpeed(long delta){
		//decreases velocity
		if(delta*SPEED_WEAROFF_RATE<=velocity.getR())
			velocity.lengthen(-delta*SPEED_WEAROFF_RATE);
		else
			velocity.setR(0);
		//adds momentum to velocity
		velocity.add(Vector2D.toScale(momentum, delta));
		this.resetMomentum();
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
		else{
			momentum.setT((float) (momentum.getT()+Math.PI));
			momentum.setR(DEFAULT_MOMENTUM);
		}
		this.backward=backward;
	}

	public void increaseMomentum(long delta){
		this.increaseMomentum(delta, false);
	}

	public void resetMomentum(){
		momentum.setR(0);
	}

	/**adds to the orientation of the entity according to time delta and fixed rate - keeps it in [0,2Pi[*/
	public void rotate(long delta, boolean counterClockwise){
		float angle=delta*this.ANGULAR_VELOCITY;
		if(counterClockwise){
			momentum.rotate(angle);
			collisionBox.rotate(this.getOrientation());
		}
		else{
			momentum.rotate(-angle);
			collisionBox.rotate(this.getOrientation());
		}
		/*System.out.println(this.getOrientation());
		if(this.getOrientation()>=0 && this.getOrientation()<Math.PI/2){
			System.out.println("Q1");
		}
		else if(this.getOrientation()>=Math.PI/2 && this.getOrientation()<Math.PI){
			System.out.println("Q2");
		}
		else if(this.getOrientation()>=Math.PI && this.getOrientation()<3*Math.PI/2){
			System.out.println("Q3");
		}
		else{
			System.out.println("Q4");
		}*/
	}

	/*public void setOrientation(float angle){
		momentum.setT(angle);
		collisionBox.rotate(angle);
		//collisionBox.setOrientation(angle);
	}*/

	public void setSpeed(float speed){
		this.velocity.setR(speed);
	}
	public void setSpeed(float angle, float speed){
		this.velocity.setT(angle);
		this.setSpeed(speed);
	}

	public abstract void onDestroy();
	public abstract void onCollision(Entity otherEntity);
}
