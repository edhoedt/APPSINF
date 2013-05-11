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
	private AudioClip currentSound;

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
			case SMALL:hp=1;
			case NORMAL:hp=1;
			case BIG:hp=1;
		}
		//this.setOrientation(getVelocity().getT());
		this.setCollisionBox(new Polygon(ASTEROID_NORMAL_X, ASTEROID_NORMAL_Y));
		this.setSpeed((float) (Math.random()*2*Math.PI), (float)Math.random()/10+.1f);
		super.SPEED_WEAROFF_RATE=0;
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