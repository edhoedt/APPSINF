package game.model.entity;

import game.util.Polygon;

public class Asteroid extends Entity{

	private static int[] ASTEROID_NORMAL_X = {10,17,26,34,27,36,26,14,8,1,5,1,10};
	private static int[] ASTEROID_NORMAL_Y = {0,5,1,8,13,20,31,28,31,22,16,7,0};

	public static enum Size{
		SMALL, BIG, NORMAL;
	}

	private Size size;
	private int hp;

	//TODO
	public Asteroid(int x, int y, Size size) {
		super(x,y);
		this.size=size;
		switch(this.size){
			case SMALL:hp=2;
			case NORMAL:hp=3;
			case BIG:hp=7;
		}
		//this.setOrientation(getVelocity().getT());
		this.setCollisionBox(new Polygon(ASTEROID_NORMAL_X, ASTEROID_NORMAL_Y));
	}

	//TODO
	public Asteroid(int x, int y) {
		this(x, y, Size.NORMAL);
		this.setCollisionBox(new Polygon(ASTEROID_NORMAL_X, ASTEROID_NORMAL_Y));
	}

	public Size getSize(){
		return this.size;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollision(Entity otherEntity) {
		if(!(otherEntity instanceof Asteroid)){
			hp--;
			if(hp==0)
				this.destroy();
		}
	}
}