package game.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import game.Game;
import game.Settings;
import game.model.entity.Entity;
import game.model.entity.Spaceship;
import game.Command;
import game.util.ControlsStore;
import game.util.VertexDrawer;

public class GameState {
	
	private Game game;
	private boolean running;
	private ControlsStore controls;
	
	public GameState(){
		controls = ControlsStore.getInstance();
		this.game=new Game(Settings.HEIGHT,Settings.WIDTH);
		try {
			Display.setDisplayMode(new DisplayMode(Settings.WIDTH,Settings.HEIGHT));
			Display.setVSyncEnabled(false);//TODO Hsync
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void renderLoop(){
		VertexDrawer.initGL(Settings.WIDTH, Settings.HEIGHT);
		while(game!=null && running && !Display.isCloseRequested()){
			game.updateTime(this.getTime());
			game.gameLoop();
			VertexDrawer.clear();
			VertexDrawer.setColor(.0f, 1.0f, .0f);
			for(Entity e : this.game.getEntities()){
					//VertexDrawer.drawPolygonTo(e.getCollisionBox(), 1, e.getX()+1, e.getY()+1, e.getOrientation());
					VertexDrawer.drawPolygon(e.getCollisionBox(), 1);
			}
			Display.update();
			
			processInputs();
			try {
				Thread.sleep((10 * 1000) / Sys.getTimerResolution());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Display.destroy();
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void init(){
		this.running=true;
		game.updateTime(getTime());
		this.renderLoop();
	}
	
	private void processInputs(){ //TODO improve this to be less resources-intensive //async multithread is probably the best idea
		ArrayList<Spaceship> movingShips = new ArrayList<Spaceship>();
		Iterator<Integer> keysIterator = controls.getKeys().iterator();
		HashMap<Spaceship, Command> actions;
		int currentKey;
		while(keysIterator.hasNext()){
			currentKey=keysIterator.next();
			if(Keyboard.isCreated() && Keyboard.isKeyDown(currentKey)){
				actions=controls.getAction(currentKey);
				Iterator<Spaceship> playersIterator = actions.keySet().iterator();
				Spaceship player;
				while(playersIterator.hasNext()){
					player=playersIterator.next();
					if(actions.get(player)==Command.GO_BACKWARD || actions.get(player)==Command.GO_BACKWARD)
						movingShips.add(player);
					game.executeCommand(actions.get(player), player);
				}
			}
		}
	}
	
	public void joinGame(String playerName){
		game.addPlayer(playerName);
		controls.bind(game.getShip(playerName), Keyboard.KEY_Z, Command.GO_FORWARD);
		controls.bind(game.getShip(playerName), Keyboard.KEY_S, Command.GO_BACKWARD);
		controls.bind(game.getShip(playerName), Keyboard.KEY_Q, Command.TURN_LEFT);
		controls.bind(game.getShip(playerName), Keyboard.KEY_D, Command.TURN_RIGHT);
		controls.bind(game.getShip(playerName), Keyboard.KEY_SPACE, Command.FIRE);
	}
	
}