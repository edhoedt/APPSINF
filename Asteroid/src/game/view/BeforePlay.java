package game.view;

import java.awt.Font;

import game.Field;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class BeforePlay extends BasicGameState implements ComponentListener{

	private TextField time;
	private MouseOverArea timed;
	private MouseOverArea easy;
	private MouseOverArea hard;
	private MouseOverArea play;
	private StateBasedGame game; 
    
    public void init(GameContainer gc, StateBasedGame game) throws SlickException 
    {
		this.game = game;
    	
		Image timedImage = new Image("res/time.gif");
		timed = new MouseOverArea(gc,timedImage, gc.getWidth()/4 - timedImage.getWidth(), gc.getHeight()/6);
		
		time = new TextField(gc, new TrueTypeFont(new Font("Arial", Font.ITALIC, 48), true), (3*gc.getWidth()/4) - 200, gc.getHeight()/6, 200, 48);
		time.setText("40:00");
		time.setBorderColor(null);
		
		Image easyImage = new Image("res/easy.gif");
		easy = new MouseOverArea(gc,easyImage, gc.getWidth()/4 - easyImage.getWidth()/2, gc.getHeight()/2,this);
		easy.setMouseDownColor(new Color(0f, 1f, 0f, 1f));
		easy.setNormalColor(new Color(0f, 1f, 0f, 1f));
		easy.setMouseOverColor(new Color(0f, 1f, 0f, 1f));
		
		Image hardImage = new Image("res/hard.gif");
		hard = new MouseOverArea(gc,hardImage, 2*gc.getWidth()/4 - hardImage.getWidth()/2, gc.getHeight()/2,this);
		hard.setMouseDownColor(new Color(1f, 0f, 0f, 1f));
		hard.setNormalColor(new Color(1f, 0f, 0f, 1f));
		hard.setMouseOverColor(new Color(1f, 0f, 0f, 1f));
		
		Image playImage = new Image("res/play.gif");
		play = new MouseOverArea(gc, playImage, 3*gc.getWidth()/4 - playImage.getWidth()/2, gc.getHeight() - 100, this);
		play.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		play.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
    }
    
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException 
    {
    	// Retour Menu
    	if(gc.getInput().isKeyDown(Input.KEY_ESCAPE))
    		game.enterState(Field.MENU, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
    
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException 
    {
    	time.render(gc, g);
    	timed.render(gc, g);
    	easy.render(gc, g);
    	hard.render(gc, g);
    	play.render(gc, g);
    }
    
    public int getID() 
	{
		return Field.BEFOREPLAY;
	}

	public void componentActivated(AbstractComponent source) {
		if(source == easy){
			easy.setMouseDownColor(new Color(0f, 1f, 0f, 1f));
			easy.setNormalColor(new Color(0f, 1f, 0f, 1f));
			easy.setMouseOverColor(new Color(0f, 1f, 0f, 1f));
			hard.setMouseDownColor(new Color(1f, 0f, 0f, 1f));
			hard.setNormalColor(new Color(1f, 0f, 0f, 1f));
			hard.setMouseOverColor(new Color(1f, 0f, 0f, 1f));
		}
		
		if(source == hard){
			hard.setMouseDownColor(new Color(0f, 1f, 0f, 1f));
			hard.setNormalColor(new Color(0f, 1f, 0f, 1f));
			hard.setMouseOverColor(new Color(0f, 1f, 0f, 1f));
			easy.setMouseDownColor(new Color(1f, 0f, 0f, 1f));
			easy.setNormalColor(new Color(1f, 0f, 0f, 1f));
			easy.setMouseOverColor(new Color(1f, 0f, 0f, 1f));
		}
		
		if(source == play)
			game.enterState(Field.GAME, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	}
}
