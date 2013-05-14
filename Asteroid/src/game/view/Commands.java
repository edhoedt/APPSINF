package game.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

/*
 * Classe vue comprenant le choix des commandes (avancer, gauche, droite, tir)
 * pour le joueur 1 et le joueur 2
 */
public class Commands extends JFrame implements ActionListener {

	// DECLARATION DES VARIABLES
	private JPanel panel;
	private JPanel commandsPanel;
	private JPanel returnPanel;
	private JPanel playerPanel;

	private JLabel player1;
	private JLabel player2;
	private JLabel separator2;
	private JLabel separator3;
	private JLabel separator4;
	private JLabel separator5;

	private JButton forwardP1;
	private JButton turnLeftP1;
	private JButton turnRightP1;
	private JButton fireP1;
	private JTextField forwardTP1;
	private JTextField turnLeftTP1;
	private JTextField turnRightTP1;
	private JTextField fireTP1;

	private JButton forwardP2;
	private JButton turnLeftP2;
	private JButton turnRightP2;
	private JButton fireP2;
	private JTextField forwardTP2;
	private JTextField turnLeftTP2;
	private JTextField turnRightTP2;
	private JTextField fireTP2;

	private JButton returnButton;
	private JButton applyButton;

	/*
	 * Constructeur
	 */
	public Commands(){
		super("Commands");

		this.setVisible(false); // Sera visible si appelé par la classe parent (otpions)

		// PANELS
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		commandsPanel = new JPanel(new GridLayout(4,5));
		returnPanel = new JPanel(new GridLayout(3,4));
		playerPanel = new JPanel(new GridLayout(1,3));

		// LABELS
		player1 = new JLabel("Player 1");
		player1.setHorizontalAlignment( SwingConstants.CENTER );
		player2 = new JLabel("Player 2");
		player2.setHorizontalAlignment( SwingConstants.CENTER );
		separator2 = new JLabel("|");
		separator2.setHorizontalAlignment( SwingConstants.CENTER );
		separator3 = new JLabel("|");
		separator3.setHorizontalAlignment( SwingConstants.CENTER );
		separator4 = new JLabel("|");
		separator4.setHorizontalAlignment( SwingConstants.CENTER );
		separator5 = new JLabel("|");
		separator5.setHorizontalAlignment( SwingConstants.CENTER );

		// BOUTONS + TEXTFIELDS JOUEUR 1
		forwardP1 = new JButton("Forward");
		forwardTP1 = new JTextField();
		forwardTP1.setEditable(false);
		turnLeftP1 = new JButton("Turn Left");
		turnLeftTP1 = new JTextField();
		turnLeftTP1.setEditable(false);
		turnRightP1 = new JButton("Turn Right");
		turnRightTP1 = new JTextField();
		turnRightTP1.setEditable(false);
		fireP1 = new JButton("Fire");
		fireTP1 = new JTextField();
		fireTP1.setEditable(false);

		// BOUTONS + TEXTFIELDS JOUEUR 2
		forwardP2 = new JButton("Forward");
		forwardTP2 = new JTextField();
		forwardTP2.setEditable(false);
		turnLeftP2 = new JButton("Turn Left");
		turnLeftTP2 = new JTextField();
		turnLeftTP2.setEditable(false);
		turnRightP2 = new JButton("Turn Right");
		turnRightTP2 = new JTextField();
		turnRightTP2.setEditable(false);
		fireP2 = new JButton("Fire");
		fireTP2 = new JTextField();
		fireTP2.setEditable(false);
		this.getControls();

		// BOUTONS RETOUR ET APPLIQUER
		returnButton = new JButton("RETURN");
		applyButton = new JButton("APPLY");

		// AJOUT DES COMPOSANTS AUX PANELS
		playerPanel.add(player1);
		playerPanel.add(new JLabel(""));
		playerPanel.add(player2);
		commandsPanel.add(forwardP1);
		commandsPanel.add(forwardTP1);
		commandsPanel.add(separator2);
		commandsPanel.add(forwardP2);
		commandsPanel.add(forwardTP2);
		commandsPanel.add(turnLeftP1);
		commandsPanel.add(turnLeftTP1);
		commandsPanel.add(separator3);
		commandsPanel.add(turnLeftP2);
		commandsPanel.add(turnLeftTP2);
		commandsPanel.add(turnRightP1);
		commandsPanel.add(turnRightTP1);
		commandsPanel.add(separator4);
		commandsPanel.add(turnRightP2);
		commandsPanel.add(turnRightTP2);
		commandsPanel.add(fireP1);
		commandsPanel.add(fireTP1);
		commandsPanel.add(separator5);
		commandsPanel.add(fireP2);
		commandsPanel.add(fireTP2);
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(returnButton);
		returnPanel.add(new JLabel(""));
		returnPanel.add(applyButton);
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));
		returnPanel.add(new JLabel(""));

		panel.add(playerPanel);
		panel.add(commandsPanel);
		panel.add(returnPanel);

		// AJOUT DU PANEL PRINCIPAL A LA FENETRE
		this.add(panel);

		// LISTENER DES BOUTONS RETOUR ET APPLIQUER
		returnButton.addActionListener(this);
		applyButton.addActionListener(this);

		// LISTENER DES BOUTONS DE COMMANDES : AJOUTE LA TOUCHE AU TEXTFIELD CORRESPONDANT
		forwardP1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					forwardTP1.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		turnLeftP1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					turnLeftTP1.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		turnRightP1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					turnRightTP1.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		fireP1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					fireTP1.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		forwardP2.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					forwardTP2.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		turnLeftP2.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					turnLeftTP2.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		turnRightP2.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					turnRightTP2.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		fireP2.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					fireTP2.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});

		// CHANGEMENT DU DESIGN DE LA FENETRE POUR WINDOWS
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		{
			if(info.getName().equals("Nimbus"))
			{
				try {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		}
		pack();

		// PROPRIETES DE LA FENETRE
		playerPanel.setPreferredSize(new Dimension(10, 50));
		playerPanel.setSize( playerPanel.getPreferredSize());
		returnPanel.setPreferredSize(new Dimension(100, 50));
		this.setSize(425,270);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	/*
	 * Charge les contrôles depuis le fichier controls.properties
	 * afin de remplir les textfields correspondants
	 */
	private void getControls(){
		Properties prop = new Properties();

		try {
			//load a properties file
			prop.load(new FileInputStream("config/controls.properties"));

			//get the property value and print it out
			forwardTP1.setText(prop.getProperty("forwardP1"));
			turnLeftTP1.setText(prop.getProperty("turnLeftP1"));
			turnRightTP1.setText(prop.getProperty("turnRightP1"));
			fireTP1.setText(prop.getProperty("fireP1"));

			forwardTP2.setText(prop.getProperty("forwardP2"));
			turnLeftTP2.setText(prop.getProperty("turnLeftP2"));
			turnRightTP2.setText(prop.getProperty("turnRightP2"));
			fireTP2.setText(prop.getProperty("fireP2"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Ajoute des actions aux listeners
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();

		// ENRREGISTRE LES CONTROLES DANS LE FICHIER
		if(b == applyButton){
			Properties prop = new Properties();

			try {
				//set the properties value
				prop.setProperty("forwardP1", forwardTP1.getText());
				prop.setProperty("turnLeftP1", turnLeftTP1.getText());
				prop.setProperty("turnRightP1", turnRightTP1.getText());
				prop.setProperty("fireP1", fireTP1.getText());

				prop.setProperty("forwardP2", forwardTP2.getText());
				prop.setProperty("turnLeftP2", turnLeftTP2.getText());
				prop.setProperty("turnRightP2", turnRightTP2.getText());
				prop.setProperty("fireP2", fireTP2.getText());

				//save properties to project root folder
				prop.store(new FileOutputStream("config/controls.properties"), null);

			} catch (IOException ex) {
				ex.printStackTrace();
			}
			this.setVisible(false);
		}

		// RETOURNE DANS OPTION SANS ENRREGISTRER
		if(b == returnButton){
			this.setVisible(false);
			getControls();
		}
	}

}
