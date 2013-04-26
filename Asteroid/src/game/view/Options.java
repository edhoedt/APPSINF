package game.view;

import game.Field;
import game.Settings;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options extends JFrame implements ActionListener{
	
	private MenuView menu;
	
	private Commands commandsView;
	
	private JPanel panel;
	private JPanel panelCommands;
	private JPanel panelVSync;
	private JPanel panelSound;
	private JPanel panelLang1;
	private JPanel panelLang2;
	private JPanel panelFS;
	private JPanel panelFPS;
	private JPanel panelPlayer1;
	private JPanel panelPlayer2;
	private JPanel panelReturn;
	
	private JButton commands;
	private JButton fr;
	private JButton en;
	private JButton returnMenu;
	
	private JLabel vSync;
	private JLabel sound;
	private JLabel language;
	private JLabel fullScreen;
	private JLabel fps;
	private JLabel player1;
	private JLabel player2;
	
	private JCheckBox checkVSync;
	private JCheckBox checkFS;
	private JCheckBox checkFPS;
	private JCheckBox volume;
	
	private JTextField textPlayer1;
	private JTextField textPlayer2;
	
	public Options(MenuView menu, Commands commandsView){
		super("Options");
		this.menu = menu;
		this.commandsView = commandsView;
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
		volume = new JCheckBox();
		volume.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		volume.setSelected(Settings.VOLUME);
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
		volume.addActionListener(this);
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
		this.setResizable(false);
		this.setLocationRelativeTo(null);
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
	
	public boolean getVolume(){
		return volume.isSelected();
	}
	
	public void actionPerformed(ActionEvent e) {
		JComponent b = (JComponent)e.getSource();
		
		if(b == returnMenu){
			this.setVisible(false);
		}
		
		if(b == commands){
			commandsView.setLocationRelativeTo(null);
			commandsView.setVisible(true);
		}
		
		if(b == fr){
			fr.setBackground(Color.GREEN);
			en.setBackground(Color.RED);
		}
		
		if(b == en){
			fr.setBackground(Color.RED);
			en.setBackground(Color.GREEN);
		}
		
		if(b == volume){ 
			menu.setVolume(volume.isSelected());
		}
	}
}
