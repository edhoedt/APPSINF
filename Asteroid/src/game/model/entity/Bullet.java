package game.model.entity;


public class Bullet extends Entity {
	public Spaceship thrower;
	
	//TODO
	public Bullet(float x, float y, Spaceship thrower) {
		super(x, y);
		this.thrower = thrower;
	}

}
