package game.view;

import game.Settings;
import game.util.ConfigMaker;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Options extends JFrame implements ActionListener{

	private MenuView menu;

	private Commands commandsView;

	private JPanel panel;
	private JPanel resolutionsPanel;
	private JPanel panelCommands;
	private JPanel panelSound;
	private JPanel panelPlayer1;
	private JPanel panelPlayer2;
	private JPanel panelReturn;

	private JButton commands;
	private JButton returnMenu;
	private JButton apply;

	private JSpinner resolutions;

	private JLabel resLabel;
	private JLabel sound;
	private JLabel player1;
	private JLabel player2;

	private JCheckBox volume;

	private JTextField textPlayer1;
	private JTextField textPlayer2;
	
	private String lastResolution;

	public Options(MenuView menu, Commands commandsView){
		super("Options");
		this.menu = menu;
		this.commandsView = commandsView;
		this.setVisible(false);
		// Panels
		panel = new JPanel(new GridLayout(7,1));
		resolutionsPanel = new JPanel(new GridLayout(1,2));
		panelCommands = new JPanel(new GridLayout(1,3));
		panelSound = new JPanel(new GridLayout(1,2));
		panelPlayer1 = new JPanel(new GridLayout(1,2));
		panelPlayer2 = new JPanel(new GridLayout(1,2));
		panelReturn = new JPanel(new GridLayout(1,5));
		// SPINNER
		String[] resString1 = {"800x600","1280x800", "2000x4000"};
		String[] resString2 = {"1280x800","800x600", "2000x4000"};
		String[] resString3 = {"2000x4000","800x600","1280x800"};
		SpinnerListModel resSpinnerListModel = new SpinnerListModel(resString1);;
		if(Settings.WIDTH == 2000 && Settings.HEIGHT == 4000){
			resSpinnerListModel = new SpinnerListModel(resString3);
		}
		else if(Settings.WIDTH == 1280 && Settings.HEIGHT == 800){
			resSpinnerListModel = new SpinnerListModel(resString2);
		}
		resolutions = new JSpinner(resSpinnerListModel);
		((JSpinner.DefaultEditor) resolutions.getEditor()).getTextField().setEditable(false);
		resLabel = new JLabel(" Résolutions");
		lastResolution = (String) resolutions.getValue();
		// COMMANDS
		commands = new JButton("COMMANDS");
		// SOUND
		sound = new JLabel(" Sound");
		volume = new JCheckBox();
		volume.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		volume.setSelected(Settings.VOLUME);
		// PLAYERS
		player1 = new JLabel(" Player 1");
		player1.setForeground(new Color(1.0f, 0.0f, 0.0f));
		player2 = new JLabel(" Player 2");
		player2.setForeground(new Color(0.2f, 1.0f, 1.0f));
		textPlayer1 = new JTextField(Settings.PLAYER1);
		textPlayer2 = new JTextField(Settings.PLAYER2);
		// RETURN
		apply = new JButton("Apply");
		returnMenu = new JButton("Return");
		// Ajout des composants
		resolutionsPanel.add(resLabel);
		resolutionsPanel.add(resolutions);
		panel.add(resolutionsPanel);
		panelSound.add(sound);
		panelSound.add(volume);
		panel.add(panelSound);
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
		panelReturn.add(apply);
		panelReturn.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(panelReturn);
		this.add(panel);
		volume.addActionListener(this);
		commands.addActionListener(this);
		returnMenu.addActionListener(this);
		apply.addActionListener(this);
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

	public boolean getVolume(){
		return volume.isSelected();
	}
	
	private void setPlayers(){
		Settings.PLAYER1 = textPlayer1.getText();
		Settings.PLAYER2 = textPlayer2.getText();
	}

	public void actionPerformed(ActionEvent e) {
		JComponent b = (JComponent)e.getSource();

		if(b == returnMenu){
			this.setVisible(false);
		}

		if(b == apply){
			if("1280x800".equals(resolutions.getValue())){
				if(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width >= 1280 && java.awt.Toolkit.getDefaultToolkit().getScreenSize().height >= 800){
					ConfigMaker.setProperties(1280,800,getVolume(),textPlayer1.getText(),textPlayer2.getText());
					setPlayers();
					this.setVisible(false);
					if(!lastResolution.equals("1280x800")){
						JOptionPane.showMessageDialog(null, "Restart the game to see the changes resolutions", "Information", JOptionPane.INFORMATION_MESSAGE);
						lastResolution = "1280x800";
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Your screen doesn't support the "+resolutions.getValue()+" resolution", "Resolution Error", JOptionPane.WARNING_MESSAGE);
			}
			else if("2000x4000".equals(resolutions.getValue())){
				if(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width >= 2000 && java.awt.Toolkit.getDefaultToolkit().getScreenSize().height >= 4000){
					ConfigMaker.setProperties(2000,4000,getVolume(),textPlayer1.getText(),textPlayer2.getText());
					setPlayers();
					this.setVisible(false);
					if(!lastResolution.equals("2000x4000")){
						JOptionPane.showMessageDialog(null, "Restart the game to see the changes resolutions", "Information", JOptionPane.INFORMATION_MESSAGE);
						lastResolution = "2000x4000";
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Your screen doesn't support the "+resolutions.getValue()+" resolution", "Resolution Error", JOptionPane.WARNING_MESSAGE);
			}
			else{
				ConfigMaker.setProperties(800,600,getVolume(),textPlayer1.getText(),textPlayer2.getText());
				setPlayers();
				this.setVisible(false);
				if(!lastResolution.equals("800x600")){
					JOptionPane.showMessageDialog(null, "Restart the game to see the changes resolutions", "Information", JOptionPane.INFORMATION_MESSAGE);
					lastResolution = "800x600";
				}
			}
		}

		if(b == commands){
			commandsView.setLocationRelativeTo(null);
			commandsView.setVisible(true);
		}

		if(b == volume){ 
			menu.setVolume(volume.isSelected());
		}
	}
}
