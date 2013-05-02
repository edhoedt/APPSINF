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
import java.net.URL;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

/*
 * Classe de l'interface graphique : Menu
 */
public class MenuView extends JFrame implements ActionListener {
	
	private GameState view;
	
	private Image background;
	
	private AudioClip currentSound;
	
	private Options options;
	private Commands commandsView;
	
	private JPanel panel;
	private JPanel exitOptions;
	
	private CoolButton singleplayer;
	private CoolButton multiplayer;
	private CoolButton optionsButton;
	private CoolButton exit;
	
	public MenuView(){
		super("Asteroids");
		
		ConfigMaker.getProperties();
		
		background = Toolkit.getDefaultToolkit().getImage("res/background.jpg");
		try
		{
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(background,0);
			mt.waitForAll();
		}
		catch(Exception e){e.printStackTrace();}
		this.setContentPane(new ContentPane(background));
		
        URL path = getClass().getResource("Eight_Bit_Robot_Dance.wav");
		try{
			currentSound = Applet.newAudioClip(path);
			if(Settings.VOLUME){
				currentSound.loop();
			}
		}catch(Exception e){e.printStackTrace();}
		
		this.setVisible(true); // Visible car première fenêtre à être affichée atm
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		commandsView = new Commands();
		options = new Options(this, commandsView);
		
		panel = new JPanel(new GridLayout(9,1));
		panel.setOpaque(false);
		exitOptions = new JPanel(new GridLayout(1,3));
		exitOptions.setOpaque(false);
		singleplayer = new CoolButton("SINGLEPLAYER");
		multiplayer = new CoolButton("MULTIPLAYER");
		optionsButton = new CoolButton("OPTIONS");
		exit = new CoolButton("EXIT");
		
		this.add(panel);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(singleplayer);
		panel.add(multiplayer);
		exitOptions.add(exit);
		exitOptions.add(new JLabel(""));
		exitOptions.add(optionsButton);
		panel.add(new JLabel(""));
		panel.add(exitOptions);
		singleplayer.addActionListener(this);
		multiplayer.addActionListener(this);
		exit.addActionListener(this);
		optionsButton.addActionListener(this);
		pack();
		this.setSize(800, 600);
		singleplayer.setPreferredSize(new Dimension(100, 50));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		view = new GameState(this);
		view.joinGame(Settings.PLAYER1);
		view.init();
	}
	
	public void setVolume(boolean volume){
		Settings.VOLUME = volume;
		if(volume){
			currentSound.loop();
		}
		else{
			currentSound.stop();
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
		if(b == exit){ 
			this.dispose();
			System.exit(0);
		}
		if(b == optionsButton){
			options.setLocationRelativeTo(null);
			options.setVisible(true);
		}
		if(b == singleplayer){
			view.setRunning();
			view.setLocationRelativeTo(null);
			view.setVisible(true);
			this.setVisible(false);
		}
		if(b == multiplayer){
			
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


