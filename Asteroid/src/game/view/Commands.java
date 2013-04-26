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

public class Commands extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel commandsPanel;
	private JPanel returnPanel;
	private JPanel playerPanel;
	
	private JLabel player1;
	private JLabel player2;
	private JLabel separator1;
	private JLabel separator2;
	private JLabel separator3;
	private JLabel separator4;
	private JLabel separator5;
	
	private JButton backwardP1;
	private JButton forwardP1;
	private JButton turnLeftP1;
	private JButton turnRightP1;
	private JButton fireP1;
	private JTextField backwardTP1;
	private JTextField forwardTP1;
	private JTextField turnLeftTP1;
	private JTextField turnRightTP1;
	private JTextField fireTP1;
	
	private JButton backwardP2;
	private JButton forwardP2;
	private JButton turnLeftP2;
	private JButton turnRightP2;
	private JButton fireP2;
	private JTextField backwardTP2;
	private JTextField forwardTP2;
	private JTextField turnLeftTP2;
	private JTextField turnRightTP2;
	private JTextField fireTP2;
	
	private JButton returnButton;
	private JButton applyButton;
	
	public Commands(){
		super("Commands");
		
		this.setVisible(false);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		commandsPanel = new JPanel(new GridLayout(5,5));
		returnPanel = new JPanel(new GridLayout(3,4));
		playerPanel = new JPanel(new GridLayout(1,3));
		
		player1 = new JLabel("Player 1");
		player1.setHorizontalAlignment( SwingConstants.CENTER );
		player2 = new JLabel("Player 2");
		player2.setHorizontalAlignment( SwingConstants.CENTER );
		separator1 = new JLabel("|");
		separator1.setHorizontalAlignment( SwingConstants.CENTER );
		separator2 = new JLabel("|");
		separator2.setHorizontalAlignment( SwingConstants.CENTER );
		separator3 = new JLabel("|");
		separator3.setHorizontalAlignment( SwingConstants.CENTER );
		separator4 = new JLabel("|");
		separator4.setHorizontalAlignment( SwingConstants.CENTER );
		separator5 = new JLabel("|");
		separator5.setHorizontalAlignment( SwingConstants.CENTER );
		
		backwardP1 = new JButton("Backward");
		backwardTP1 = new JTextField();
		backwardTP1.setEditable(false);
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
		
		backwardP2 = new JButton("Backward");
		backwardTP2 = new JTextField();
		backwardTP2.setEditable(false);
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

		returnButton = new JButton("RETURN");
		applyButton = new JButton("APPLY");
		
		playerPanel.add(player1);
		playerPanel.add(new JLabel(""));
		playerPanel.add(player2);
		commandsPanel.add(backwardP1);
		commandsPanel.add(backwardTP1);
		commandsPanel.add(separator1);
		commandsPanel.add(backwardP2);
		commandsPanel.add(backwardTP2);
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
		this.add(panel);
		
		returnButton.addActionListener(this);
		applyButton.addActionListener(this);
		
		backwardP1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
					backwardTP1.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        });
		forwardP1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
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
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
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
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
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
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
				fireTP1.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        });
		backwardP2.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP2.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
				backwardTP2.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        });
		forwardP2.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(fireTP1.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) ||
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
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
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
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()) ||
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
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(forwardTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(turnLeftTP2.getText()) || KeyEvent.getKeyText(e.getKeyCode()).equals(turnRightTP2.getText()) ||
						KeyEvent.getKeyText(e.getKeyCode()).equals(backwardTP1.getText()))
					JOptionPane.showMessageDialog(null, "The key '"+ KeyEvent.getKeyText(e.getKeyCode()) +"' is already used", "Binding Error", JOptionPane.WARNING_MESSAGE);
				else
				fireTP2.setText(KeyEvent.getKeyText(e.getKeyCode()));
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        });
		
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
		playerPanel.setPreferredSize(new Dimension(10, 50));
		playerPanel.setSize( playerPanel.getPreferredSize());
		returnPanel.setPreferredSize(new Dimension(100, 50));
		this.setSize(425,270);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void getControls(){
		Properties prop = new Properties();
		 
    	try {
            //load a properties file
    		prop.load(new FileInputStream("config/controls.properties"));
 
            //get the property value and print it out
    		backwardTP1.setText(prop.getProperty("backwardP1"));
    		forwardTP1.setText(prop.getProperty("forwardP1"));
    		turnLeftTP1.setText(prop.getProperty("turnLeftP1"));
    		turnRightTP1.setText(prop.getProperty("turnRightP1"));
    		fireTP1.setText(prop.getProperty("fireP1"));
    		
    		backwardTP2.setText(prop.getProperty("backwardP2"));
    		forwardTP2.setText(prop.getProperty("forwardP2"));
    		turnLeftTP2.setText(prop.getProperty("turnLeftP2"));
    		turnRightTP2.setText(prop.getProperty("turnRightP2"));
    		fireTP2.setText(prop.getProperty("fireP2"));
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
		if(b == applyButton){
			Properties prop = new Properties();
			 
	    	try {
	    		//set the properties value
	    		prop.setProperty("backwardP1", backwardTP1.getText());
	    		prop.setProperty("forwardP1", forwardTP1.getText());
	    		prop.setProperty("turnLeftP1", turnLeftTP1.getText());
	    		prop.setProperty("turnRightP1", turnRightTP1.getText());
	    		prop.setProperty("fireP1", fireTP1.getText());
	    		
	    		prop.setProperty("backwardP2", backwardTP2.getText());
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
		
		if(b == returnButton){
			this.setVisible(false);
		}
	}

}
