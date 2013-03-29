package game.model.entity;

public class Spaceship extends Entity {
	private String playerName;
	
	private long lastFired;
	private int  FIRE_COOLDOWN;//in ms

	public Spaceship(int x, int y, String playerName){
		super(x, y);
		this.playerName=playerName;
		lastFired=0;
		FIRE_COOLDOWN=2000;
	}
	
	@Override
	public boolean equals(Object o){
		return (o.getClass() == Spaceship.class && this.getPlayerName()== ((Spaceship) o).getPlayerName());
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public boolean canFire(long currentTime){
		if(currentTime-lastFired>=FIRE_COOLDOWN){
			return true;
		}
		else
			return false;
	}
	
	public void updateFireTime(long time){
		this.lastFired=time;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollision(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}
}
