package game.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import game.Field;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuState extends BasicGameState implements ComponentListener
{
	private static Image background;
	private MouseOverArea singlePlayer;
	private MouseOverArea multiPlayer;
	private MouseOverArea exit;
	private MouseOverArea options;
	private GameContainer gc;
	private StateBasedGame game;
    private Options option;
    private Music music;
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException 
	{
		this.game = game;
		this.gc = gc;
		
		background = new Image("res/loadingbg.png");
		music = new Music("res/Glory.wav");
		music.play();
		
		Image singlePlayerImage = new Image("res/singlePlayer.gif");
		singlePlayer = new MouseOverArea(gc,singlePlayerImage, gc.getWidth()/2 - singlePlayerImage.getWidth()/2, gc.getHeight()/4,this);
		singlePlayer.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		singlePlayer.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
		
		Image multiPlayerImage = new Image("res/multiplayer.gif");
		multiPlayer = new MouseOverArea(gc,multiPlayerImage, gc.getWidth()/2 - multiPlayerImage.getWidth()/2, gc.getHeight()/4 + 100 ,this);
		multiPlayer.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		multiPlayer.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
		
		Image exitImage = new Image("res/exit.gif");
		exit = new MouseOverArea(gc, exitImage, gc.getWidth()/4 - exitImage.getWidth()/2, gc.getHeight() - 100,this);
		exit.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		exit.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
		
		Image optionsImage = new Image("res/options.gif");
		options = new MouseOverArea(gc, optionsImage, 3*gc.getWidth()/4 - optionsImage.getWidth()/2, gc.getHeight() - 100, this);
		options.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		options.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        option = new Options(screenSize.getWidth(),screenSize.getHeight(), (int)music.getVolume());
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException 
	{
		Field.FULLSCREEN = option.isFullscreen();
		gc.setVSync(option.isVSync());
		gc.setShowFPS(option.isShowFPS());
		music.setVolume(option.getVolume());
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException 
	{
		background.draw(0, 0);
		singlePlayer.render(gc, g);
		multiPlayer.render(gc, g);
		exit.render(gc, g);
		options.render(gc, g);
	}


	public int getID() 
	{
		return Field.MENU;
	}
	
	public void componentActivated(AbstractComponent source) 
	{
		if (source == singlePlayer) 
		{
			game.enterState(Field.BEFOREPLAY, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (source == multiPlayer) 
		{
			game.enterState(Field.BEFOREPLAY, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (source == exit)
		{
			gc.exit();
		}
		if (source == options) 
		{
			option.setLocationCenter();
			option.setVisible(true);
		}
	}
}