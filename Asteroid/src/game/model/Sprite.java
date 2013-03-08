package game.model;

import java.io.IOException;

import game.model.entity.Entity;
import game.util.Texture;
import game.util.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
	private Entity entity;
	private Texture texture;
	private int width;
	private int height;
	
	public Sprite(Entity entity, TextureLoader loader, String ref){
		try{
			this.entity = entity;
			texture = loader.getTexture(ref);
			width = texture.getWidth();
			height = texture.getHeight();
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// Valeur de retour en pixels
	public int getWidth() {
		return texture.getWidth();
	}

	// Valeur de retour en pixels
	public int getHeigth() {
		return texture.getHeight();
	}
	
	// Dessine le sprite à la localisation précisée
	public void draw(){
		int x = entity.getX();
		int y = entity.getY();
		
		// Stocke la matrice actuelle
		glPushMatrix();
		
		// Lien vers la texture appropriée de ce sprite
		texture.bind();
		
		// Translation sur la bonne localisation
		glTranslatef(x, y, 0);
		
		// Dessine un carré de texture qui correspond au sprite
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0,0);
			glVertex2f(0,0);
			
			glTexCoord2f(0, texture.getTexHeight()); // TODO Vérifier si pas getHeight au lieu de getTexHeight
			glVertex2f(0, height);
			
			glTexCoord2f(texture.getTexWidth(), texture.getTexHeight()); // TODO Vérifier si pas getHeight au lieu de getTexHeight
			glVertex2f(width, height);
			
			glTexCoord2f(texture.getTexWidth(), 0); // TODO Vérifier si pas getWidth au lieu de getTexWidth
			glVertex2f(width, 0);
		}
		glEnd();
		
		// Restore le modèle de vue de la matrice
		glPopMatrix();
	}
	
	//TODO
	public void rotate(){
		
	}
}
