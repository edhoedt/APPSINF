package game.view;

import game.Field;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionsState extends BasicGameState {

    private StateBasedGame game;
	private GameContainer gc;
    
    public void init(GameContainer gc, StateBasedGame game) throws SlickException 
    {
    	this.game = game;
		this.gc = gc;
    }
    
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException 
    {
    	// Retour Menu
    	if(gc.getInput().isKeyDown(Input.KEY_ESCAPE))
    		game.enterState(Field.MENU);
    }
    
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException 
    {
    }
    
    public int getID() 
	{
		return Field.OPTIONS;
	}
}