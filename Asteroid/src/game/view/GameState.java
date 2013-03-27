package game.view;

import game.Field;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {
	
	private int time;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		time = 60000;
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Time : " + time/1000, gc.getWidth()/2 - 30, 50);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		time -= delta;
		if(gc.getInput().isKeyDown(Input.KEY_ESCAPE))
    		game.enterState(Field.MENU);
	}

	public int getID() {
		return Field.GAME;
	}

}
