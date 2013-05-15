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

/*
 * Classe de la vue du jeu
 */
public class GameState extends JFrame{

	// DECLARATION DES VARIABLES
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

	/*
	 * Constructeur (garde le menu en paramètre pour y revenir ensuite)
	 */
	public GameState(final MenuView menuView){
		super("Asteroids");
		this.menuView= menuView;

		// COMPOSANTS JEU, SCORE, TEMPS
		this.setLayout(new BorderLayout());
		final Canvas canvas = new Canvas(); // Utilisation de canvas pour intégrer l'interface lwjgl
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panelLabels = new JPanel();
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.X_AXIS));
		panelLabels.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		timeLabel = new JLabel(""+this.getTime());
		timeLabel.setForeground(Color.WHITE);
		scoreP1 = new JLabel("Score "+Settings.PLAYER1+" : 0");
		scoreP1.setForeground(new Color(1.0f, 0.0f, 0.0f)); // Couleur du joueur 1
		scoreP2 = new JLabel("Score "+Settings.PLAYER2+" : 0");
		scoreP2.setForeground(new Color(0.2f, 1.0f, 1.0f)); // Couleur du joueur 2

		this.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e)
			{ canvas.requestFocusInWindow(); }
		});

		// LORS DE LA FERMETURE DE LA FENETRE DE JEU
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{ 
				running = false; // Met la boucle de jeu en pause
				menuView.setLocationRelativeTo(null);
				menuView.setVisible(true); // Renvoie au menu
			}
		});

		// AJOUT DES COMPOSANTS
		panelLabels.add(scoreP1);
		panelLabels.add(new JLabel("                            "));
		panelLabels.add(timeLabel);
		panelLabels.add(new JLabel("                            "));
		panelLabels.add(scoreP2);
		panel.add(panelLabels);
		panel.add(canvas, BorderLayout.CENTER);
		this.add(panel);

		// TEMPS DU LANCEMENTS
		startTime = this.getTime();

		// AJOUT DES CONTROLES JOUEURS
		controls = ControlsStore.getInstance();

		// AJOUT DU CONTROLLER
		this.game=new Game(Settings.HEIGHT,Settings.WIDTH);

		// AJOUT DE L'INTERFACE LWJGL + INTEGRATION SWING
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
			VertexDrawer.initGL(Settings.WIDTH,Settings.HEIGHT); // Initialisation GL utilisée pour dessiner les vecteurs
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Méthode de nettoyage lors de la relance du jeu
	 */
	public void clear(){
		this.startTime = getTime();
		this.controls.clear();
		this.game.clear();
	}

	/*
	 * Méthode qui relance la boucle de jeu
	 */
	public void setRunning(){
		running = true;
	}

	/*
	 * Méthode permetant de savoir si le jeu est en pause
	 */
	public boolean getRunning(){
		return running;
	}

	/*
	 * Méthode de la boucle de jeu
	 */
	private void renderLoop(){
		while(game!=null && !Display.isCloseRequested()){
			//  MET LA BOUCLE EN PAUSE
			while(!running){
				System.out.print("");
			}
			// TESTE SI LE TEMPS EST ECOULE
			if(this.getTime()-this.startTime>Settings.TIMEOUT*60000){
				running = false;
				menuView.setLocationRelativeTo(null);
				this.setVisible(false);
				menuView.setVisible(true);
				setScores();
				menuView.showScores();
			}

			// UPDATE DU TEMPS
			game.updateTime(this.getTime());
			game.gameLoop();
			timeLabel.setText("Time "+(((this.getTime() - startTime)/60000))+":"+(((this.getTime() - startTime)/1000)%60));

			// UPDATE DES SCORES
			scoreP1.setText("Score "+Settings.PLAYER1+" : "+(game.getShip(Settings.PLAYER1).getScore()));
			if(game.getShip(Settings.PLAYER2) != null)
				scoreP2.setText("Score "+Settings.PLAYER2+" : "+(game.getShip(Settings.PLAYER2).getScore()));

			// UPDATE DES VECTEURS
			VertexDrawer.clear();
			for(Entity e : this.game.getEntities()){
				VertexDrawer.setColor(e.getColor()[0], e.getColor()[1], e.getColor()[2]);
				VertexDrawer.drawPolygon(e.getCollisionBox(), 1);
			}
			Display.update();

			// UPDATE DES INPUTS
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

	/*
	 * Donne le temps en millisecondes
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/*
	 * Initialise la vue du jeu
	 */
	public void init(){
		this.running=false;
		game.updateTime(getTime());
		this.renderLoop();
	}

	/*
	 * Procède à la lecture des inputs
	 */
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
					if(actions.get(player)==Command.GO_FORWARD)
						movingShips.add(player);
					game.executeCommand(actions.get(player), player);
				}
			}
		}
	}

	/*
	 * Méthode de récupération des scores
	 */
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

	/*
	 * Méthode utilisée à la fin d'une partie pour enrregistrer les scores
	 */
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
						prop2.setProperty("single"+j+"Score", ""+((float)game.getShip(Settings.PLAYER1).getScore()));
						joueur1Ok = true;
					}
					else if((float)game.getShip(Settings.PLAYER1).getScore() <= scoresSingle[j-1] && !joueur1Ok){
						prop2.setProperty("single"+(j)+"Name", nomsSingle[j-1]);
						prop2.setProperty("single"+(j)+"Score", ""+scoresSingle[j-1]);
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
						prop2.setProperty("multi"+j+"Score", ""+((float)game.getShip(Settings.PLAYER1).getScore()));
						joueur1Ok = true;
					}
					else if((float)game.getShip(Settings.PLAYER1).getScore() <= scoresMulti[j-1] && !joueur1Ok){
						prop2.setProperty("multi"+(j)+"Name", nomsMulti[j-1]);
						prop2.setProperty("multi"+(j)+"Score", ""+scoresMulti[j-1]);
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
						prop2.setProperty("multi"+j+"Score", ""+((float)game.getShip(Settings.PLAYER2).getScore()));
						joueur2Ok = true;
					}
					else if((float)game.getShip(Settings.PLAYER2).getScore() <= scoresMulti[j-1] && !joueur2Ok){
						prop2.setProperty("multi"+(j)+"Name", nomsMulti[j-1]);
						prop2.setProperty("multi"+(j)+"Score", ""+scoresMulti[j-1]);
					}
					if(joueur2Ok && (j+1)<11){
						prop2.setProperty("multi"+(j+1)+"Name", nomsMulti[j-1]);
						prop2.setProperty("multi"+(j+1)+"Score", ""+scoresMulti[j-1]);
					}
				}
			}

			if(!joueur1Ok && !joueur2Ok){
				//Do not update
			}
			else	
				prop2.store(new FileOutputStream("config/scores.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Méthode qui ajoute un joueur au jeu (vaisseau)
	 */
	public void joinGame(String playerName){
		game.addPlayer(playerName);
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config/controls.properties"));

			if(playerName.equals(Settings.PLAYER1)){
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("forwardP1")), Command.GO_FORWARD);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnLeftP1")), Command.TURN_LEFT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnRightP1")), Command.TURN_RIGHT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("fireP1")), Command.FIRE);
			}
			else if(playerName.equals(Settings.PLAYER2)){
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("forwardP2")), Command.GO_FORWARD);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnLeftP2")), Command.TURN_LEFT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("turnRightP2")), Command.TURN_RIGHT);
				controls.bind(game.getShip(playerName), KeyToLwjgl.translateKeyCode(prop.getProperty("fireP2")), Command.FIRE);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Renvoie le contrôlleur
	 */
	public Game getGame() {
		return game;
	}

}
