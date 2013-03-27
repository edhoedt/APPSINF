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

public class Main extends StateBasedGame
{	
    private SplashState splash;
    private MenuState menu;
    private OptionsState options;
    private BeforePlay beforePlay;
    private GameState gameState;
    
    public Main() {super("Asteroïd");}

    public void initStatesList(GameContainer container) throws SlickException 
    {
    	splash = new SplashState();
        menu = new MenuState();
        options = new OptionsState();
        beforePlay = new BeforePlay();
        gameState = new GameState();
        addState(splash);
        addState(menu);
        addState(options);
        addState(beforePlay);
        addState(gameState);
    }
   
    public static void main(String[] args) throws SlickException
    {
    	AppGameContainer container = new AppGameContainer(new Main());
    	container.setDisplayMode(800, 600, false);
    	container.setFullscreen(Field.FULLSCREEN);
    	container.setVSync(Field.VSYNC);
    	container.setShowFPS(Field.SHOWFPS);
        container.start();
    }

}