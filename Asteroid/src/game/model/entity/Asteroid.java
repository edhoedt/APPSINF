package game.model.entity;

import game.model.Sprite;
import game.model.Vector2D;

public class Asteroid extends Entity{
	private int size;
	
	//TODO
	public Asteroid(Sprite sprite, float x, float y, Vector2D velocity, int momentum) {
		super(sprite, x, y);
	}

	//TODO
		public Asteroid(Sprite sprite, float x, float y) {
			super(sprite, x, y);
		}
}
