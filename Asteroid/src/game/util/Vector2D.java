package game.util;

/*
 * vecteur en coord. polaires 
 * R=longueur positive
 * T=angle [0, 2PI[
 */

public class Vector2D {
	private float theta=0; //[0,2Pi[
	private float r=0;//[0,->
	
	/**Creates a 2Dimensionnal vector
	 * v(x,y)
	 * */
	public Vector2D(float theta, float radius) {
		this.setR(radius);
		this.setT(theta);
	}
	
	//sets theta, s'assure que theta € [0,2PI[
	public void setT(float theta){
		while(theta<0){
			theta+=2*Math.PI;
		}
		while(theta>=2*Math.PI){
			theta-=2*Math.PI;
		}
		this.theta=theta;
	}
	//sets R, s'assure que R €[0->
	public void setR(float radius){
		if(radius<0)
			this.setT(this.getT()+(float)Math.PI);
		this.r=Math.abs(radius);
	}
	
	//retorune l'angle
	public float getT(){return theta;}
	//retourne la longueur du vecteur
	public float getR(){return r;}
	
	/**cartesian operations*/
	private static Vector2D v2DFromCartesian(float x, float y){
		return new Vector2D((float) Math.atan2(y, x), (float) Math.sqrt(x*x+y*y));
	}
	public float getX(){
		return (float) (this.getR()*Math.cos(this.getT()));
	}
	public float getY(){
		return (float) (this.getR()*Math.sin(this.getT()));
	}
	
	/**Adds otherVector to this vector*/
	public void add(Vector2D otherVector){
		Vector2D buff = getSum(this, otherVector);
		this.setR(buff.getR());
		this.setT(buff.getT());
	}
	public static Vector2D getSum(Vector2D a, Vector2D b){
		return v2DFromCartesian(a.getX()+b.getX(),a.getY()+b.getY());
	}
	
	/**Multiply*/
	public void scale(float coef){
		this.setR(this.getR()*coef);
	}
	public static Vector2D toScale(Vector2D v, float coef){
		return new Vector2D (v.getT(), v.getR()*coef);
	}
	
	/**Lengthens (or shortens if negative) this vector of 'length' units */
	public void lengthen(float length){
		this.setR(this.getR()+length);
	}
	/*rotates this of theta rad (relative angle)*/
	public void rotate(float theta){
		this.setT(this.getT()+theta);
	}
}