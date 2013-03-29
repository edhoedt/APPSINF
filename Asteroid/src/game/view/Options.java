package game.view;

import game.Field;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Options extends JFrame implements ActionListener {
	
	double width;
	double height;
	int levelVolume;
	
	JPanel panel;
	JPanel panelCommands;
	JPanel panelVSync;
	JPanel panelSound;
	JPanel panelLang1;
	JPanel panelLang2;
	JPanel panelFS;
	JPanel panelFPS;
	JPanel panelPlayer1;
	JPanel panelPlayer2;
	JPanel panelReturn;
	
	JButton commands;
	JButton fr;
	JButton en;
	JButton returnMenu;
	
	JLabel vSync;
	JLabel sound;
	JLabel language;
	JLabel fullScreen;
	JLabel fps;
	JLabel player1;
	JLabel player2;
	
	JCheckBox checkVSync;
	JCheckBox checkFS;
	JCheckBox checkFPS;
	
	JSlider volume;
	
	JTextField textPlayer1;
	JTextField textPlayer2;
	
	public Options(double width, double height, int levelVolume){
		super("Options");
		this.width = width;
		this.height = height;
		this.levelVolume = levelVolume;
		this.setVisible(false);
		// Panels
		panel = new JPanel(new GridLayout(9,1));
		panelCommands = new JPanel(new GridLayout(1,3));
		panelVSync = new JPanel(new GridLayout(1,2));
		panelSound = new JPanel(new GridLayout(1,2));
		panelLang1 = new JPanel(new GridLayout(1,2));
		panelLang2 = new JPanel(new GridLayout(1,2));
		panelFS = new JPanel(new GridLayout(1,2));
		panelFPS = new JPanel(new GridLayout(1,2));
		panelPlayer1 = new JPanel(new GridLayout(1,2));
		panelPlayer2 = new JPanel(new GridLayout(1,2));
		panelReturn = new JPanel(new GridLayout(1,3));
		// COMMANDS
		commands = new JButton("COMMANDS");
		// VSYNC
		vSync = new JLabel(" Vertical Synchronisation");
		checkVSync = new JCheckBox();
		checkVSync.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkVSync.setSelected(Field.VSYNC);
		// SOUND
		sound = new JLabel(" Sound");
		volume = new JSlider();
		// LANGUAGE
		language = new JLabel(" Language");
		fr = new JButton("FR");
		en = new JButton("EN");
		fr.setBackground(Color.GREEN);
		en.setBackground(Color.RED);
		// FULLSCREEN
		fullScreen = new JLabel(" Fullscreen");
		checkFS = new JCheckBox();
		checkFS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkFS.setSelected(Field.FULLSCREEN);
		// FPS
		fps = new JLabel(" Show fps");
		checkFPS = new JCheckBox();
		checkFPS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkFPS.setSelected(Field.SHOWFPS);
		// PLAYERS
		player1 = new JLabel(" Player 1");
		player2 = new JLabel(" Player 2");
		textPlayer1 = new JTextField("Hubert");
		textPlayer2 = new JTextField("Jean-Charles");
		// RETURN
		returnMenu = new JButton("Return");
		// Ajout des composants
		panelVSync.add(vSync);
		panelVSync.add(checkVSync);
		panel.add(panelVSync);
		panelSound.add(sound);
		panelSound.add(volume);
		panel.add(panelSound);
		panelLang2.add(fr);
		panelLang2.add(en);
		panelLang1.add(language);
		panelLang1.add(panelLang2);
		panel.add(panelLang1); 
		panelFS.add(fullScreen);
		panelFS.add(checkFS);
		panel.add(panelFS);
		panelFPS.add(fps);
		panelFPS.add(checkFPS);
		panel.add(panelFPS);
		panelPlayer1.add(player1);
		panelPlayer1.add(textPlayer1);
		panelPlayer2.add(player2);
		panelPlayer2.add(textPlayer2);
		panel.add(panelPlayer1);
		panel.add(panelPlayer2);
		panelCommands.add(new JLabel(""));
		panelCommands.add(commands);
		panelCommands.add(new JLabel(""));
		panel.add(panelCommands);
		panelReturn.add(new JLabel(""));
		panelReturn.add(returnMenu);
		panelReturn.add(new JLabel(""));
		panel.add(panelReturn);
		this.add(panel);
		commands.addActionListener(this);
		fr.addActionListener(this);
		en.addActionListener(this);
		returnMenu.addActionListener(this);
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		{
			if(info.getName().equals("Nimbus"))
			{
				try {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		pack();
		this.setLocation((int)(width/2 - this.getSize().getWidth()/2), (int)(height/2 - this.getSize().getHeight()/2));
		this.setResizable(false);
	}

	public boolean isFullscreen(){
		return checkFS.isSelected();
	}
	
	public boolean isVSync(){
		return checkVSync.isSelected();
	}
	
	public boolean isShowFPS(){
		return checkFPS.isSelected();
	}
	
	public float getVolume(){
		return (float)volume.getValue();
	}
	
	public void setLocationCenter(){
		this.setLocation((int)(width/2 - this.getSize().getWidth()/2), (int)(height/2 - this.getSize().getHeight()/2));
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
		if(b == returnMenu){
			this.setVisible(false);
		}
		
		if(b == commands) System.out.println("hello");
		
		if(b == fr){
			fr.setBackground(Color.GREEN);
			en.setBackground(Color.RED);
		}
		
		if(b == en){
			fr.setBackground(Color.RED);
			en.setBackground(Color.GREEN);
		}
	}
}
