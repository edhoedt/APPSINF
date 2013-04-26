package game.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/*
 * Classe utilisée pour créer les boutons des menus
 */
public class CoolButton extends JButton implements MouseListener {
	
	public CoolButton(String text) { 
		 
		super(text);
		
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setForeground(new Color(255,255,255));
		this.setFont(new Font("Jokerman", Font.BOLD, 30));
		this.setBackground(new Color(34, 139, 34));
		 
		addMouseListener(this);
	}
	
	/*
	 * Réécrit les méthodes pour les rendre inutilisables
	 */
	public void mouseClicked(MouseEvent e) { }
	 
	public void mousePressed(MouseEvent e) { }
	 
	public void mouseReleased(MouseEvent e) { }

	public void mouseEntered(MouseEvent e) { 
		 
		if(e.getSource()==this) {  this.setContentAreaFilled(true); }
		 
	}

	public void mouseExited(MouseEvent e) { 
		 
		if(e.getSource()==this) { this.setContentAreaFilled(false); }
		 
	}
}
