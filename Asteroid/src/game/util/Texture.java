package game.util;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	private int target;
	private int textureID;
	private int height;
	private int width;
	private int texHeight;
	private int texWidth;
	private float heightRatio;
	private float widthRatio;
	
	public Texture(int target, int textureID) {
		this.target = target;
		this.textureID = textureID;
	}

	// Attache le contexte de la bibliothèque graphique à la texture
	public void bind(){
		glBindTexture(target, textureID);
	}
	
	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getTextureID() {
		return textureID;
	}

	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		setHeight();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		setWidth();
	}

	public int getTexHeight() {
		return texHeight;
	}

	public void setTexHeight(int texHeight) {
		this.texHeight = texHeight;
	}

	public int getTexWidth() {
		return texWidth;
	}

	public void setTexWidth(int texWidth) {
		this.texWidth = texWidth;
	}

	public float getHeightRatio() {
		return heightRatio;
	}

	public void setHeightRatio(float heightRatio) {
		this.heightRatio = heightRatio;
	}

	public float getWidthRatio() {
		return widthRatio;
	}

	public void setWidthRatio(float widthRatio) {
		this.widthRatio = widthRatio;
	}
	
	// Modifie la hauteur de la texture et le ratio.
	private void setHeight(){
		if(texHeight != 0){
			heightRatio = ((float) height) / texHeight;
		}
	}
	
	// Modifie la largeur de la texture et le ratio.
		private void setWidth(){
			if(texWidth != 0){
				widthRatio = ((float) width) / texWidth;
			}
		}
}
