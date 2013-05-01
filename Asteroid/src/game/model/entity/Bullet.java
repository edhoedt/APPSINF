package game.model.entity;

import game.util.Polygon;
import game.util.Vector2D;


public class Bullet extends Entity {
	
	public Spaceship thrower;
	public static int BULLET_SPEED = 10000;
	
	public Bullet(Spaceship thrower) {
		super(thrower.getX(), thrower.getY(), new Vector2D(thrower.getOrientation(),BULLET_SPEED)); //TODO faire apparaitre le bullet devant le lanceur et pas SUR le lanceur
		this.setOrientation(thrower.getOrientation());
		super.SPEED_WEAROFF_RATE=0;
		this.thrower = thrower;
		//this.setCollisionBox(new Polygon(SPACESHIP_X, SPACESHIP_Y, 27, 30));
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCollision(Entity otherEntity) {
		this.destroy();
	}

}
