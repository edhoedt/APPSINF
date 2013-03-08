package game.model;

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

	//TODO
	public void bind(){
		
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
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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
}
