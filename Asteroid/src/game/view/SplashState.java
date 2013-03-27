package game.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//TODO Enlever l'import
import game.Field;
import game.util.DataHelper;
import game.util.DrawHelper;
import game.util.Resolution;

public class SplashState extends BasicGameState
{
	private GameState gameState;
	private int titleY;
	private int loadingSpinRot;
	private float menuTimer;
	private boolean debug = true;
	private boolean loadOnce;
	private Resolution resolution;
	private Resolution monitorResolution;
	private static Image splashSlick;
	private static Image splashTrunks;
	private static Image title;
	private static Image loading;
	private static Image loadingSpin;
	private static Image loadingBg;

  public void init(GameContainer gc, StateBasedGame game) throws SlickException
  {
    loadOnce();
  }

  public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
  {
    Input input = gc.getInput();
    incrementTimers(delta);

    if (isGameState(GameState.splashscreen)) {
      if ((input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed (Input.KEY_ESCAPE)) || (this.menuTimer >= 4000.0F)) {
        changeGameState(GameState.initialLoad);
      }

    }
    else if (isGameState(GameState.initialLoad)) {
      if ((input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed (Input.KEY_ESCAPE))) this.titleY = DataHelper.getCenterY() - 50;
      if (this.titleY > DataHelper.getCenterY() - 50) { this.titleY = (int)(this.titleY - 0.1D * delta);
      } else {
        this.loadingSpinRot += 1;
        loadingSpin.setRotation(this.loadingSpinRot);
        // Boucle pour voir le loader
        //if(loadingSpin.getRotation() >= (float)359){
        	if (!this.loadOnce) {
        		this.loadOnce = true;
        		initGame(gc, game);
        	//}
        }
      }
    }
  }

  public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
  {
    if (isGameState(GameState.splashscreen)) {
      renderSplash(gc, g);
    }
    else if (isGameState(GameState.initialLoad))
      renderInitialLoad(gc, g);
  }
  
  public int getID() 
	{
		return Field.SPLASH;
	}

  public void incrementTimers(int delta)
  {
    this.menuTimer += delta;
  }

  public void initGame(GameContainer gc, StateBasedGame game) {
    System.out.println("Initializing Game...");
    game.enterState(Field.MENU);
  }

  public void renderSplash(GameContainer gc, Graphics g) {
    float alpha = 1.0F;

    if (this.menuTimer < 2000.0F)
    {
      if (this.menuTimer < 500.0F) alpha = this.menuTimer / 500.0F;
      else if (this.menuTimer < 1500.0F) alpha = 1.0F;
      else if (this.menuTimer < 2000.0F) alpha = 1.0F - (this.menuTimer - 1500.0F) / 500.0F;
      DrawHelper.drawImageCentered(splashSlick, DataHelper.getCenterX(), DataHelper.getCenterY(), alpha, 0.5F);
    }
    else if (this.menuTimer < 4000.0F) {
      if (this.menuTimer < 2500.0F) alpha = (this.menuTimer - 2000.0F) / 500.0F;
      else if (this.menuTimer < 3500.0F) alpha = 1.0F;
      else if (this.menuTimer < 4000.0F) alpha = 1.0F - (this.menuTimer - 3500.0F) / 500.0F;
      DrawHelper.drawImageCentered(splashTrunks, DataHelper.getCenterX(), DataHelper.getCenterY(), alpha, 1.0F);
    }
  }

  public void renderInitialLoad(GameContainer gc, Graphics g) {
    DrawHelper.drawImage(loadingBg, 0, 0);

    DrawHelper.drawImageCentered(title, DataHelper.getCenterX(), this.titleY - title.getHeight() / 2);
    if (this.titleY <= DataHelper.getCenterY() - 50) {
      DrawHelper.drawImage(loading, 10, DataHelper.getHeight() - 10 - loading.getHeight());
      DrawHelper.drawImageCentered(loadingSpin, 20 + loading.getWidth() + loadingSpin.getWidth() / 2, DataHelper.getHeight() - 10 - loading.getHeight() / 2);
    }
  }

  public boolean isGameState(GameState state) {
    return this.gameState.equals(state);
  }

  public void changeGameState(GameState newState)
  {
    if (this.debug) System.out.println("Old State: " + getGameState() + " New State: " + newState);

    if (isGameState(GameState.splashscreen)) {
      splashSlick = null;
      splashTrunks = null;
      this.menuTimer = 0.0F;
    }

    if (newState.equals(GameState.initialLoad)) {
      this.menuTimer = 0.0F;
      this.titleY = (DataHelper.getHeight() + title.getHeight() / 2);
      this.loadingSpinRot = 0;
    }

    setGameState(newState);
  }

  public void setGameState(GameState state) {
    this.gameState = state;
  }

  public GameState getGameState() {
    return this.gameState;
  }

  public void loadOnce() throws SlickException {
    splashSlick = new Image("res/edouard.png");
    splashTrunks = new Image("res/ludovic.png");
    title = new Image("res/splashTitle.png");
    loading = new Image("res/loading.png");
    loadingSpin = new Image("res/loadingspin.png");
    loadingBg = new Image("res/loadingbg.png");
    this.monitorResolution = DataHelper.getMonitorResolution();
    this.resolution = Resolution.getMappedResolution(this.monitorResolution);
    DataHelper.setWidth(this.resolution.getWidth());
    DataHelper.setHeight(this.resolution.getHeight());

    this.menuTimer = 0.0F;

    this.loadOnce = false;

    setGameState(GameState.splashscreen);
  }

  private static enum GameState
  {
    splashscreen, initialLoad;
  }
}