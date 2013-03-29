package game.model.entity;

public class Asteroid extends Entity{

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
		this.setOrientation(getVelocity().getT());
	}

	//TODO
	public Asteroid(int x, int y) {
		this(x, y, Size.NORMAL);
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
		hp--;
		if(hp==0)
			this.destroy();
	}
}
