package game.util;

public class Vector2D {
	public float x;
	public float y;
	
	/**Creates a 2Dimensionnal vector
	 * v(x,y)
	 * */
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**Adds otherVector to thios vector*/
	public void addVector(Vector2D otherVector){
		this.x+=otherVector.x;
		this.y+=otherVector.y;
	}
	
	/**Returns the length of this vector*/
	public float getLength(){
		return (float) Math.sqrt(x*x+y*y);
	}
	
	/**Lengthens (or shortens if negative) this vector of 'length' units */
	public void addToVector(float length){
		float k=(x*x)/(y*y);
		if(length>=0){
			x+=Math.sqrt((length*length)/(1+1/k));
			y+=Math.sqrt((length*length)/(k+1));
		}else{
			x-=Math.sqrt((length*length)/(1+1/k));
			y-=Math.sqrt((length*length)/(k+1));
		}		
	}
}
