package game.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
import game.util.KeyToLwjgl;
import game.util.VertexDrawer;

public class GameState extends JFrame{

	private Game game;
	private long startTime;
	private boolean running;
	private ControlsStore controls;
	private JPanel panel;
	private JPanel panelLabels;
	private JLabel timeLabel;
	private JLabel scoreP1;
	private JLabel scoreP2;
	private final MenuView menuView;

	public GameState(final MenuView menuView){
		super("Asteroids");
		this.menuView= menuView;
		this.setLayout(new BorderLayout());
		final Canvas canvas = new Canvas();
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panelLabels = new JPanel();
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.X_AXIS));
		panelLabels.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		timeLabel = new JLabel(""+this.getTime());
		timeLabel.setForeground(Color.WHITE);
		scoreP1 = new JLabel("Score "+Settings.PLAYER1+" : 0");
		scoreP1.setForeground(new Color(1.0f, 0.0f, 0.0f));
		scoreP2 = new JLabel("Score "+Settings.PLAYER2+" : 0");
		scoreP2.setForeground(new Color(0.2f, 1.0f, 1.0f));

		this.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e)
			{ canvas.requestFocusInWindow(); }
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{ 
				running = false;
				menuView.setLocationRelativeTo(null);
				menuView.setVisible(true);
			}
		});
		panelLabels.add(scoreP1);
		panelLabels.add(new JLabel("                            "));
		panelLabels.add(timeLabel);
		panelLabels.add(new JLabel("                            "));
		panelLabels.add(scoreP2);
		panel.add(panelLabels);
		panel.add(canvas, BorderLayout.CENTER);
		this.add(panel);
		
		startTime = this.getTime();

		controls = ControlsStore.getInstance();
		this.game=new Game(Settings.HEIGHT,Settings.WIDTH);
		try {
			Display.setParent(canvas);
			Display.setDisplayMode(new DisplayMode(Settings.WIDTH,Settings.HEIGHT));
			Display.setVSyncEnabled(Settings.VSYNC);
			this.setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
			this.setMinimumSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
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

	public void clear(){
		this.startTime = getTime();
		this.controls.clear();
		this.game.clear();
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
				System.out.print("");
			}
			if(this.getTime()-this.startTime>Settings.TIMEOUT*60000){
				running = false;
				menuView.setLocationRelativeTo(null);
				this.setVisible(false);
				menuView.setVisible(true);
				setScores();
				menuView.showScores();
			}
			game.updateTime(this.getTime());
			game.gameLoop();
			timeLabel.setText("Time "+(((this.getTime() - startTime)/60000))+":"+(((this.getTime() - startTime)/1000)%60));

			// Update des noms des players
			scoreP1.setText("Score "+Settings.PLAYER1+" : "+((float)game.getShip(Settings.PLAYER1).getScore())/Settings.TIMEOUT);
			if(game.getShip(Settings.PLAYER2) != null)
				scoreP2.setText("Score "+Settings.PLAYER2+" : "+((float)game.getShip(Settings.PLAYER2).getScore())/Settings.TIMEOUT);

			VertexDrawer.clear();
			for(Entity e : this.game.getEntities()){
				//VertexDrawer.drawPolygonTo(e.getCollisionBox(), 1, e.getX()+1, e.getY()+1, e.getOrientation());
				if(e.hasPoped()){
					VertexDrawer.setColor(e.getColor()[0], e.getColor()[1], e.getColor()[2]);
					VertexDrawer.drawPolygon(e.getCollisionBox(), 1);
				}else{
					VertexDrawer.setColor(.54f, .54f, .54f);
					VertexDrawer.drawPolygon(e.getCollisionBox(), 1);
				}
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
					if(actions.get(player)==Command.GO_FORWARD || actions.get(player)==Command.GO_BACKWARD)
						movingShips.add(player);
					game.executeCommand(actions.get(player), player);
				}
			}
		}
	}
	
	private void loadScores(String nomsSingle[],float scoresSingle[],String nomsMulti[],float scoresMulti[]){
		Properties prop = new Properties();
		
		try {
            //load a properties file
    		prop.load(new FileInputStream("config/scores.properties"));
 
            //get the property value and print it out
    		for(int j = 1 ; j < 11 ; j++){
    			nomsSingle[j-1] = prop.getProperty("single"+j+"Name");
    			scoresSingle[j-1] = Float.parseFloat(prop.getProperty("single"+j+"Score"));
    			nomsMulti[j-1] = prop.getProperty("multi"+j+"Name");
    			scoresMulti[j-1] = Float.parseFloat(prop.getProperty("multi"+j+"Score"));
    		}
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	
	private void setScores(){
		
		String nomsSingle[] = new String[10];
		float scoresSingle[] = new float[10];
		String nomsMulti[] = new String[10];
		float scoresMulti[] = new float[10];
		
		loadScores(nomsSingle,scoresSingle,nomsMulti,scoresMulti);
		
		boolean joueur1Ok = false;
		boolean joueur2Ok = false;
		Properties prop2 = new Properties();
		
    	try {
    		//Boucle pour joueur 1
    		for(int j = 1 ; j < 11 ; j++){
    			if(game.getShip(Settings.PLAYER2)==null){
    				if((float)game.getShip(Settings.PLAYER1).getScore() > scoresSingle[j-1] && !joueur1Ok){
    					prop2.setProperty("single"+j+"Name", Settings.PLAYER1);
    					prop2.setProperty("single"+j+"Score", ""+((float)game.getShip(Settings.PLAYER1).getScore())/Settings.TIMEOUT);
    					joueur1Ok = true;
    				}
    				if(joueur1Ok && (j+1)<11){
    					prop2.setProperty("single"+(j+1)+"Name", nomsSingle[j-1]);
    					prop2.setProperty("single"+(j+1)+"Score", ""+scoresSingle[j-1]);
    				}
    				prop2.setProperty("multi"+j+"Name", nomsMulti[j-1]);
					prop2.setProperty("multi"+j+"Score", ""+scoresMulti[j-1]);
    			}
    			else{
    				if((float)game.getShip(Settings.PLAYER1).getScore() > scoresMulti[j-1] && !joueur1Ok){
    					prop2.setProperty("multi"+j+"Name", Settings.PLAYER1);
    					prop2.setProperty("multi"+j+"Score", ""+((float)game.getShip(Settings.PLAYER1).getScore())/Settings.TIMEOUT);
    					joueur1Ok = true;
    				}
    				if(joueur1Ok && (j+1)<11){
    					prop2.setProperty("multi"+(j+1)+"Name", nomsMulti[j-1]);
    					prop2.setProperty("multi"+(j+1)+"Score", ""+scoresMulti[j-1]);
    				}
    				prop2.setProperty("single"+j+"Name", nomsSingle[j-1]);
					prop2.setProperty("single"+j+"Score", ""+scoresSingle[j-1]);
    			}
    		}
    		
    		if(!joueur1Ok && !joueur2Ok){
    			//Do not update
    		}
    		else{
    			prop2.store(new FileOutputStream("config/scores.properties"), null);
    			loadScores(nomsSingle,scoresSingle,nomsMulti,scoresMulti);
    		}
    		
    		//Boucle pour joueur 2
    		for(int j = 1 ; j < 11 ; j++){
    			if(game.getShip(Settings.PLAYER2)!=null){
    				if((float)game.getShip(Settings.PLAYER2).getScore() > scoresMulti[j-1] && !joueur2Ok){
    					prop2.setProperty("multi"+j+"Name", Settings.PLAYER2);
    					prop2.setProperty("multi"+j+"Score", ""+((float)game.getShip(Settings.PLAYER2).getScore())/Settings.TIMEOUT);
    					joueur2Ok = true;
    				}
    				if(joueur2Ok && (j+1)<11){
    					prop2.setProperty("multi"+(j+1)+"Name", nomsMulti[j-1]);
    					prop2.setProperty("multi"+(j+1)+"Score", ""+scoresMulti[j-1]);
    				}
    			}
    		}
 
    		//save properties to project root folder
    		if(!joueur1Ok && !joueur2Ok){
    			//Do not update
    		}
    		else	
    			prop2.store(new FileOutputStream("config/scores.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}

	public void joinGame(String playerName){
		game.addPlayer(playerName);
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config/controls.properties"));

			if(playerName.equals(Settings.PLAYER1)){
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("forwardP1")), Command.GO_FORWARD);
				//controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("backwardP1")), Command.GO_BACKWARD);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnLeftP1")), Command.TURN_LEFT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnRightP1")), Command.TURN_RIGHT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("fireP1")), Command.FIRE);
			}
			else if(playerName.equals(Settings.PLAYER2)){
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("forwardP2")), Command.GO_FORWARD);
				//controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("backwardP2")), Command.GO_BACKWARD);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnLeftP2")), Command.TURN_LEFT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnRightP2")), Command.TURN_RIGHT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("fireP2")), Command.FIRE);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Game getGame() {
		return game;
	}

}
