package game.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import game.Game;
import game.RockTest;
import game.Settings;
import game.model.entity.Spaceship;
import game.Command;
import game.util.ControlsStore;
import game.util.Polygon;
import game.util.VertexDrawer;

public class GameState extends JFrame{
	
	private Game game;
	private boolean running;
	private ControlsStore controls;
	private MenuView menuView;
	
	int[] polyXArray = {-13,14,-13,-5,-13};
	int[] polyYArray = {-15,0,15,0,-15};
	int i = 0;
	int j = 0;
	int thickness = 1;
	Polygon ship = new Polygon(polyXArray, polyYArray, 5, 27, 30);
	private int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
	private int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};
	Polygon rock = new Polygon(sPolyXArray, sPolyYArray, 13, 26, 31);
	public static ArrayList<RockTest> rocks = new ArrayList<RockTest>();
	
	public GameState(final MenuView menuView){
		super("Asteroids");
		this.menuView = menuView;
		this.setLayout(new BorderLayout());
		final Canvas canvas = new Canvas();
		
		this.addWindowFocusListener(new WindowAdapter() {
	        public void windowGainedFocus(WindowEvent e)
	        { canvas.requestFocusInWindow(); }
	     });
		
		this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e)
	        { 
	        	running = false;
	        	menuView.setVisible(true);
	        }
	     });
		
		this.add(canvas, BorderLayout.CENTER);
		
		for(int i = 0; i < 10; i++){
			
			int randomStartXPos = (int) (Math.random() * (800 - 40) + 1);
			int randomStartYPos = (int) (Math.random() * (600 - 40) + 1);
			
			rocks.add(new RockTest(RockTest.getpolyXArray(randomStartXPos), RockTest.getpolyYArray(randomStartYPos), 13, 35, 31, randomStartXPos, randomStartYPos));
			
			RockTest.rocks = rocks;
			
		}
		
		controls = ControlsStore.getInstance();
		this.game=new Game(Settings.HEIGHT,Settings.WIDTH);
		try {
			Display.setParent(canvas);
	        Display.setVSyncEnabled(true);
	        this.setPreferredSize(new Dimension(800, 600));
	        this.setMinimumSize(new Dimension(800, 600));
	        this.setLocationRelativeTo(null);
	        this.pack();
	        this.setResizable(false);
	        this.setVisible(true);
	        Display.create();
	        this.setVisible(false);
	        VertexDrawer.initGL(Settings.WIDTH,Settings.HEIGHT);
	        Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void setRunning(){
		running = true;
	}
	
	public boolean getRunning(){
		return running;
	}
	
	private void renderLoop(){
		while(game!=null && !Display.isCloseRequested()){
			while(!running){
				System.out.println("Pause");
			}
			game.updateTime(this.getTime());
			game.gameLoop();
			i = i+j+1;
			VertexDrawer.clear();

			VertexDrawer.setColor(1.0f, 1.0f, 1.0f);

			VertexDrawer.drawPolygonTo(ship, thickness, i, 300);
			if(i > 800)
			{
				i = 0;
				j++;
			}
			for(RockTest rock : rocks){
				
				rock.move(); 
				
				VertexDrawer.drawPolygon(rock, thickness);
				
			}
			
			Display.update();
			processInputs();
			try {
				Thread.sleep((10 * 1000) / Sys.getTimerResolution());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Display.destroy();
		this.dispose();
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void init(){
		this.running=false;
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
		controls.bind(game.getShip(playerName), Keyboard.KEY_W, Command.GO_FORWARD);
		controls.bind(game.getShip(playerName), Keyboard.KEY_S, Command.GO_BACKWARD);
		controls.bind(game.getShip(playerName), Keyboard.KEY_A, Command.TURN_LEFT);
		controls.bind(game.getShip(playerName), Keyboard.KEY_D, Command.TURN_RIGHT);
		controls.bind(game.getShip(playerName), Keyboard.KEY_SPACE, Command.FIRE);
	}
	
}