package game;

import game.util.ControlsStore;
import game.view.GameState;

import org.lwjgl.input.Keyboard;

public class Main {

	/**
	 * @param args
	 */
	
	//for testing purpose
	public static void main(String[] args){
		GameState view =new GameState();
		view.joinGame("Pedro");
		view.init();
	}

}
