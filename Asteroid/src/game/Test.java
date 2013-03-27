package game;

import game.model.Sprite;
import game.util.TextureLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import static org.lwjgl.opengl.GL11.*;

public class Test {
	/*TextureLoader textureLoader;
	Sprite sprite;
	Image img;*/
	
	public enum State {
		GAME, MAIN_MENU;
	}
	
	private static State state = State.GAME;
	
	public void start() throws SlickException {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setTitle("Asteroid");
			Display.create();
			glEnable(GL_TEXTURE_2D);
			glDisable(GL_DEPTH_TEST);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();

			glOrtho(0, 800, 600, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glViewport(0, 0, 800, 600);

			//img = new Image("res/Mac_Banner_Article.jpg");
			//textureLoader = new TextureLoader();
			//sprite = new Sprite("test.png", textureLoader);
			
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL here
		
		while (!Display.isCloseRequested()) {
			
			checkInput();
			glClear(GL_COLOR_BUFFER_BIT);
			// render OpenGL here
			//sprite.draw(0,0);
			//img.draw(0,0);
			render();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private void render(){
		switch (state) {
		case GAME:
			glColor3f(0.0f, 1.0f, 0.0f);
			glRectf(0, 0, 800, 600);
			break;
		case MAIN_MENU:
			glColor3f(0.0f, 0.0f, 1.0f);
			glRectf(0, 0, 800, 600);
			break;
		}
	}
	
	private void checkInput(){
		while (Keyboard.next()) {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				if(state == State.MAIN_MENU)
					state = State.GAME;
				else if(state == State.GAME)
					state = State.MAIN_MENU;
			}
		}
	}
	
	public static void main(String[] argv) throws SlickException {
		Test displayExample = new Test();
		displayExample.start();
	}
}