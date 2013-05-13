package game.model.entity;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import game.util.Polygon;
import game.util.Vector2D;

public class Bullet extends Entity {
	
	public Spaceship thrower;
	public static float BULLET_SPEED = .4f;
	public int lifespan= 4000;
	public static int[] BULLET_X={0,5,5,0,0};
	public static int[] BULLET_Y={0,0,1,1,0};
	private AudioClip currentSound;
	
	public Bullet(Spaceship thrower) {
		//super(thrower.getX(), thrower.getY(), new Vector2D(thrower.getOrientation(),BULLET_SPEED)); //TODO faire apparaitre le bullet devant le lanceur et pas SUR le lanceur
		//super(thrower.getCollisionBox().getXpoint(1), thrower.getCollisionBox().getYpoint(1), Vector2D.getSum(new Vector2D(thrower.getOrientation(),BULLET_SPEED), thrower.getVelocity()));
		super(thrower.getCollisionBox().getXpoint(thrower.firePosition()), thrower.getCollisionBox().getYpoint(thrower.firePosition()), new Vector2D(thrower.getOrientation(),BULLET_SPEED+thrower.getVelocity().getR()));
		thrower.pop();
		URL path = getClass().getResource("laser.wav");
		try{
			currentSound = Applet.newAudioClip(path);
			currentSound.play();
		}catch(Exception e){e.printStackTrace();}
		
		//this.setColor(1.0f, 1.0f, 0.0f);
		this.setColor(thrower.getColor()[0], thrower.getColor()[1], thrower.getColor()[2]);
		//this.setOrientation(thrower.getOrientation());
		//super.MOMENTUM_INCREASE_RATE=0;
		super.SPEED_WEAROFF_RATE=0;
		this.thrower = thrower; 
		this.setCollisionBox(new Polygon(BULLET_X, BULLET_Y));
		this.getMomentum().setT(thrower.getOrientation());
		this.getCollisionBox().rotate(this.getOrientation());
	}
	
	@Override
	public void updatePosition(long delta){
		super.updatePosition(delta);
		lifespan-=delta;
		if(lifespan<=0){
			this.destroy();
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCollision(Entity otherEntity) {
		if(!(otherEntity instanceof Bullet) && !(otherEntity instanceof Spaceship) && !this.thrower.equals(otherEntity)){
			if(otherEntity instanceof Asteroid){
				this.thrower.score(1);
			}else if(otherEntity instanceof Spaceship){
				this.thrower.score(10);
			}
			this.destroy();
		}
	}

}
