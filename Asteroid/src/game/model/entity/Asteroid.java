package game.model.entity;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import game.Settings;
import game.util.Polygon;
import game.util.Vector2D;

public class Asteroid extends Entity{

	private static int[] ASTEROID_NORMAL_X = {10,17,26,34,27,36,26,14,8,1,5,1,10};
	private static int[] ASTEROID_NORMAL_Y = {0,5,1,8,13,20,31,28,31,22,16,7,0};
	private static int[] ASTEROID_SMALL_X = {0,4,9,10,8,3,0,1,0};
	private static int[] ASTEROID_SMALL_Y = {0,1,2,6,10,12,9,6,0};
	private static int[] ASTEROID_BIG_X = {10*3,17*3,26*3,34*3,27*3,36*3,26*3,14*3,8*3,1*3,5*3,1*3,10*3};
	private static int[] ASTEROID_BIG_Y = {0*3,5*3,1*3,8*3,13*3,20*3,31*3,28*3,31*3,22*3,16*3,7*3,0*3};
	private AudioClip currentSound;

	public static enum Size{
		SMALL, BIG, NORMAL;
	}

	private Size size;
	private int hp;

	//TODO
	public Asteroid(int x, int y, Size size) {
		super(x,y);
		float speed=0;
		this.size=size;
		this.setColor(0f, 1f, 0f);
		switch(this.size){
			case SMALL:
				hp=1;
				this.setCollisionBox(new Polygon(ASTEROID_SMALL_X, ASTEROID_SMALL_Y));
				speed=.15f;
			break;
			case NORMAL:
				hp=2; 
				this.setCollisionBox(new Polygon(ASTEROID_NORMAL_X, ASTEROID_NORMAL_Y));
				speed=.05f;
			break;
			case BIG:
				hp=3;
				this.setCollisionBox(new Polygon(ASTEROID_BIG_X, ASTEROID_BIG_Y));
				speed=.01f;
			break;
		}
		this.setSpeed((float) (Math.random()*2*Math.PI), speed);
		super.SPEED_WEAROFF_RATE=0;
	}

	//TODO
	/*public Asteroid(int x, int y) {
		this(x, y, Size.NORMAL);
		this.setCollisionBox(new Polygon(ASTEROID_NORMAL_X, ASTEROID_NORMAL_Y));
	}*/

	public Size getSize(){
		return this.size;
	}

	@Override
	public void onDestroy() {
		URL path = getClass().getResource("explosion.wav");
		try{
			currentSound = Applet.newAudioClip(path);
			currentSound.play();
		}catch(Exception e){e.printStackTrace();}
	}

	@Override
	public void onCollision(Entity otherEntity) {
		if(!(otherEntity instanceof Asteroid)){
			hp--;
			this.setColor(0, this.getColor()[1]-.3f, 0);
			if(hp<=0)
				this.destroy();
		}else{
			//System.out.print(this.getVelocity().getX()+" -> ");
			float tempT=otherEntity.getVelocity().getT();
			float tempR=otherEntity.getVelocity().getR();
			otherEntity.setSpeed(this.getVelocity().getT(), this.getVelocity().getR());
			this.setSpeed(tempT, tempR);
			//System.out.println(this.getVelocity().getX());
		}
		
	}
}