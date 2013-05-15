package game.view;

import game.Settings;
import game.util.ConfigMaker;
import game.util.CoolButton;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/*
 * Classe de l'interface graphique : Menu
 */
public class MenuView extends JFrame implements ActionListener {
	
	// DECLARATION DES VARIABLES
	private GameState view;
	
	private Image background;
	
	private AudioClip currentSound;
	
	private Options options;
	private Commands commandsView;
	
	private JPanel panel;
	private JPanel exitOptions;
	
	private CoolButton singleplayer;
	private CoolButton multiplayer;
	private CoolButton scores;
	private CoolButton optionsButton;
	private CoolButton exit;
	
	/*
	 * Constructeur
	 */
	public MenuView(){
		super("Asteroids");
		
		ConfigMaker.getProperties(); // Remplit la classe Settings
		
		// MISE EN PLACE DU BACKGROUND
		background = Toolkit.getDefaultToolkit().getImage("res/background.jpg");
		try
		{
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(background,0);
			mt.waitForAll();
		}
		catch(Exception e){e.printStackTrace();}
		this.setContentPane(new ContentPane(background));
		
		// MISE EN PLACE DE LA MUSIQUE
        URL path = getClass().getResource("Eight_Bit_Robot_Dance.wav");
		try{
			currentSound = Applet.newAudioClip(path);
			if(Settings.VOLUME){
				currentSound.loop();
			}
		}catch(Exception e){e.printStackTrace();}
		
		this.setVisible(true); // Visible car première fenêtre à être affichée atm
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// INITIALISATION DES AUTRES VUES
		commandsView = new Commands();
		options = new Options(this, commandsView);
		
		// PANELS
		panel = new JPanel(new GridLayout(10,1));
		panel.setOpaque(false);
		exitOptions = new JPanel(new GridLayout(1,3));
		exitOptions.setOpaque(false);
		
		// BOUTONS VERTS
		singleplayer = new CoolButton("SINGLEPLAYER");
		multiplayer = new CoolButton("MULTIPLAYER");
		scores = new CoolButton("SCORES");
		optionsButton = new CoolButton("OPTIONS");
		exit = new CoolButton("EXIT");
		
		// AJOUT DES COMPOSANTS
		this.add(panel);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(singleplayer);
		panel.add(multiplayer);
		panel.add(scores);
		exitOptions.add(exit);
		exitOptions.add(new JLabel(""));
		exitOptions.add(optionsButton);
		panel.add(new JLabel(""));
		panel.add(exitOptions);
		
		// AJOUT DES LISTENERS
		singleplayer.addActionListener(this);
		multiplayer.addActionListener(this);
		scores.addActionListener(this);
		exit.addActionListener(this);
		optionsButton.addActionListener(this);
		
		pack();
		
		// PROPRIETES DE LA FENETRE
		this.setResizable(false);
		this.setSize(800, 600); // Taille de l'image de fond
		singleplayer.setPreferredSize(new Dimension(100, 50)); // Taille des boutons s'accorde car pack
		this.setLocationRelativeTo(null);
		
		// INITIALISATION DE VUE DU JEU
		view = new GameState(this);
		view.init();
	}
	
	/*
	 * Joue la musique en fonction de la classe Settings
	 */
	public void setVolume(boolean volume){
		Settings.VOLUME = volume;
		if(volume){
			currentSound.loop();
		}
		else{
			currentSound.stop();
		}
	}

	/*
	 * Lie les actions aux listeners
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
		// QUITTE L'APPLICATION
		if(b == exit){ 
			this.dispose();
			System.exit(0);
		}
		
		// MENU DES OPTIONS
		if(b == optionsButton){
			options.setLocationRelativeTo(null);
			options.setVisible(true);
		}
		
		// LANCE LE JEU EN SOLO
		if(b == singleplayer){
			beforeStart(false);
		}
		
		// LANCE LE JEU EN MULTIJOUEUR
		if(b == multiplayer){
			beforeStart(true);
		}
		
		// AFFICHE LES SCORES
		if(b == scores){
			showScores();
		}
	}
	
	/*
	 * Méthode d'affichage des scores dans un JOptionPane
	 */
	public void showScores() {
		
		// COMPOSANTS DE LA FENETRE
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel headerPanel = new JPanel(new GridLayout(2,3));
		JPanel scorePanel = new JPanel(new GridLayout(10,5));
		headerPanel.add(new JLabel("Singleplayer"));
		headerPanel.add(new JLabel(""));
		headerPanel.add(new JLabel("Multiplayer"));
		headerPanel.add(new JLabel(""));
		headerPanel.add(new JLabel(""));
		headerPanel.add(new JLabel(""));
		
		// CHARGEMENT DES SCORES A PARTIR DU FICHIER
		Properties prop = new Properties();
		 
    	try {
            //load a properties file
    		prop.load(new FileInputStream("config/scores.properties"));
 
            //get the property value and print it out
    		for(int j = 1 ; j < 11 ; j++){
    			scorePanel.add(new JLabel(prop.getProperty("single"+j+"Name")+" : "));
	    		scorePanel.add(new JLabel(prop.getProperty("single"+j+"Score")));
	    		scorePanel.add(new JLabel(""));
	    		scorePanel.add(new JLabel(prop.getProperty("multi"+j+"Name")+" : "));
	    		scorePanel.add(new JLabel(prop.getProperty("multi"+j+"Score")));
    		}
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	
    	mainPanel.add(headerPanel);
    	mainPanel.add(scorePanel);
		JOptionPane.showMessageDialog(null, mainPanel,"Scores", JOptionPane.PLAIN_MESSAGE);
	}

	/*
	 * Lance un message au joueur pour lui demander les options d'avant-partie
	 */
	private void beforeStart(boolean isMultiplayer){
		
		// COMPOSANTS DE LA FENETRE
		SpinnerNumberModel sModel = new SpinnerNumberModel(5, 1, 20, 1);
		JSpinner spinner = new JSpinner(sModel);
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		JPanel panelOptions = new JPanel(new GridLayout(3,2));
		JSlider difficulty = new JSlider();
		difficulty.setMaximum(3);
		Dictionary<Integer, JLabel> dict = new Hashtable<Integer, JLabel>();
		dict.put(0, new JLabel("Pussy"));
		dict.put(1, new JLabel(" Ok "));
		dict.put(2, new JLabel("Man mode"));
		dict.put(3, new JLabel("Insane"));
		difficulty.setLabelTable(dict);
		difficulty.setMinorTickSpacing(1);
		difficulty.setMajorTickSpacing(4);
		difficulty.setValue(1);
		difficulty.setPaintLabels(true);
		difficulty.setPaintTicks(true);
		difficulty.setSnapToTicks(true);
		JCheckBox superMode = new JCheckBox();
		superMode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panelOptions.add(new JLabel(" Game time (min) "));
		panelOptions.add(spinner);
		panelOptions.add(new JLabel(" Difficulty "));
		panelOptions.add(difficulty);
		panelOptions.add(new JLabel(" SuperMode"));
		panelOptions.add(superMode);
		
		// RECUPERATION DES OPTIONS
		int option = JOptionPane.showOptionDialog(null, panelOptions, " Enter game options ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (option == JOptionPane.CANCEL_OPTION)
		{
		    // user hit cancel -> simply return
		}
		else if (option == JOptionPane.OK_OPTION)
		{
			Settings.TIMEOUT = (long)(Integer)spinner.getValue();
			Settings.DIFFICULTY = difficulty.getValue();
			Settings.SUPERMODE = superMode.isSelected();
			view.clear();
			view.joinGame(Settings.PLAYER1);
			if(isMultiplayer)
				view.joinGame(Settings.PLAYER2);
			view.setRunning();
			view.setLocationRelativeTo(null);
			view.setVisible(true);
			this.setVisible(false);
		}
	}

	/*
	 * Classe du background
	 */
	private class ContentPane extends JPanel
	{
		private Image image;
		public ContentPane(Image leFond){super();image=leFond;}
		public void paintComponent(Graphics g){g.drawImage(image,0,0,null);}
	}
}


