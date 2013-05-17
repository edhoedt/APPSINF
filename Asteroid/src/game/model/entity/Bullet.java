package game.model.entity;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import game.Settings;
import game.util.Polygon;
import game.util.Vector2D;
/*
 * Représente un projectile
 */
public class Bullet extends Entity {
	
	public Spaceship thrower;//lanceur à l'origine du projectile
	public static float BULLET_SPEED = .4f;
	public int lifespan= 4000;//cette var était utilisée quand les bullets réagissaient à la physique comme le vaisseau
	public static int[] BULLET_X={0,5,5,0,0};
	public static int[] BULLET_Y={0,0,1,1,0};
	private AudioClip currentSound;
	
	//thrower est le lanceur
	//son à la création
	public Bullet(Spaceship thrower) {
		super(thrower.getCollisionBox().getXpoint(thrower.firePosition()), thrower.getCollisionBox().getYpoint(thrower.firePosition()), new Vector2D(thrower.getOrientation(),BULLET_SPEED+thrower.getVelocity().getR()));
		thrower.pop();
		URL path = getClass().getResource("laser.wav");
		try{
			currentSound = Applet.newAudioClip(path);
			currentSound.play();
		}catch(Exception e){e.printStackTrace();}
		
		this.setColor(thrower.getColor()[0], thrower.getColor()[1], thrower.getColor()[2]);
		super.SPEED_WEAROFF_RATE=0;
		this.thrower = thrower; 
		this.setCollisionBox(new Polygon(BULLET_X, BULLET_Y));
		this.getMomentum().setT(thrower.getOrientation());
		this.getCollisionBox().rotate(this.getOrientation());
	}
	
	//cette fct était utilisée quand les bullets réagissaient à la physique comme le vaisseau
	//maintenant, elle est toujours effective mais n'a plus d'incidence visible
	@Override
	public void updatePosition(long delta){
		super.updatePosition(delta);
		lifespan-=delta;
		if(lifespan<=0){
			this.destroy();
		}
	}

	//rien de particulier
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	}

	//destruction du bullet+ ajustement des scores
	@Override
	public void onCollision(Entity otherEntity) {
		if(!(otherEntity instanceof Bullet) && !(otherEntity instanceof Spaceship) && !this.thrower.equals(otherEntity)){
			if(otherEntity instanceof Asteroid){
				this.thrower.score((100/Settings.TIMEOUT)*(1+Settings.DIFFICULTY));
			}else if(otherEntity instanceof Spaceship){
				this.thrower.score((1000/Settings.TIMEOUT)*(1+Settings.DIFFICULTY));
			}
			this.destroy();
		}
	}

}
