package game;

import game.view.BeforePlay;
import game.view.GameState;
import game.view.OptionsState;
import game.view.MenuState;
import game.view.SplashState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainTest extends StateBasedGame
{
    
    public MainTest() {
    	super("Asteroïds");
    }

    public void initStatesList(GameContainer container) throws SlickException 
    {
    	addState(new SplashState());
        addState(new MenuState());
        addState(new OptionsState());
        addState(new BeforePlay());
        addState(new GameState());
    }
   
    public static void main(String[] args) throws SlickException
    {
    	AppGameContainer container = new AppGameContainer(new MainTest());
    	container.setDisplayMode(800, 600, false);
    	container.setFullscreen(Field.FULLSCREEN);
    	container.setVSync(Field.VSYNC);
    	container.setShowFPS(Field.SHOWFPS);
        container.start();
    }

}