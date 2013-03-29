package game.view;

import game.Field;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameState extends BasicGameState {
	
	private int time;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		time = 6000;
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		g.drawString("Time : " + time/1000, gc.getWidth()/2 - 30, 50);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		gc.setFullscreen(Field.FULLSCREEN);
		if(time > 0)
			time -= delta;
		if(gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
			gc.setFullscreen(false);
    		game.enterState(Field.MENU, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}
	
	// Permet de reset le state
	public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
		time = 6000;
	}

	public int getID() {
		return Field.GAME;
	}

}
