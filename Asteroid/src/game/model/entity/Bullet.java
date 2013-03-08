package game.model.entity;

import game.model.Sprite;

public class Bullet extends Entity {
	public Spaceship thrower;
	
	//TODO
	public Bullet(Sprite sprite, float x, float y, Spaceship thrower) {
		super(sprite, x, y);
		this.thrower = thrower;
	}

}
