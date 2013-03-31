package game.util;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

import org.lwjgl.opengl.GL11;

public class VertexDrawer {

	/*
	 * Initialise OpenGL
	 * width : Largeur de la fenêtre
	 * height : Hauteur de la fenêtre
	 */
	public static void initGL(int width, int height){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/*
	 * Nettoie l'écran et le buffer de profondeur
	 */
	public static void clear(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	/*
	 * Change la couleur du vecteur
	 */
	public static void setColor(float red, float green, float blue){
		GL11.glColor3f(red,green,blue);
	}
	
	/*
	 * Dessine un polygone à partir d'un tableau de points X et 
	 * un tableau de points Y. Thickness correspond à l'épaisseur des vecteurs.
	 */
	public static void drawPolygonFromArray(int[] polyXArray, int[] polyYArray, int thickness){
		for(int i = 0 ; i < polyXArray.length-1 ; i++){
	    	if(i < polyXArray.length-1){
	    		drawLine(polyXArray[i],polyYArray[i],polyXArray[i+1],polyYArray[i+1],thickness,thickness);
	    	}
	    	else{
	    		drawLine(polyXArray[i],polyYArray[i],polyXArray[i-i],polyYArray[i-i],thickness,thickness);
	    	}
	    }
	}
	
	/*
	 * Dessine un polygone à partir d'un Polygon
	 * avec une épaisseur de thickness
	 */
	public static void drawPolygon(Polygon polygon, int thickness){
		for(int i = 0 ; i < polygon.getNpoints()-1 ; i++){
	    	if(i < polygon.getNpoints()-1){
	    		drawLine(polygon.getXpoint(i),polygon.getYpoint(i),polygon.getXpoint(i+1),polygon.getYpoint(i+1),thickness,thickness);
	    	}
	    	else{
	    		drawLine(polygon.getXpoint(i),polygon.getYpoint(i),polygon.getXpoint(i-i),polygon.getYpoint(i-i),thickness,thickness);
	    	}
	    }
	}
	
	/*
	 * Dessine un polygone à partir d'un Polygon
	 * avec une épaisseur de thickness
	 * à une position (x,y)
	 */
	public static void drawPolygonTo(Polygon polygon, int thickness, int x, int y){
		for(int i = 0 ; i < polygon.getNpoints()-1 ; i++){
	    	if(i < polygon.getNpoints()-1){
	    		drawLine(x+polygon.getXpoint(i),y+polygon.getYpoint(i),x+polygon.getXpoint(i+1),
	    				y+polygon.getYpoint(i+1),thickness,thickness);
	    	}
	    	else{
	    		drawLine(x+polygon.getXpoint(i),y+polygon.getYpoint(i),x+polygon.getXpoint(i-i),
	    				y+polygon.getYpoint(i-i),thickness,thickness);
	    	}
	    }
	}
	
	/*
	 * Dessine une ligne entre (x1,y1) - (x2,y2) avec une épaisseur
	 * de t1 au début et t2 à la fin de la ligne
	 */
	public static void drawLine(float x1, float y1, float x2, float y2, float t1, float t2)
	 {
	     float angle = (float) Math.atan2(y2 - y1, x2 - x1);
	     float t2sina1 = (float) (t1 / 2 * Math.sin(angle));
	     float t2cosa1 = (float) (t1 / 2 * Math.cos(angle));
	     float t2sina2 = (float) (t2 / 2 * Math.sin(angle));
	     float t2cosa2 = (float) (t2 / 2 * Math.cos(angle));
	
	     glBegin(GL_TRIANGLES);
	     GL11.glVertex2f(x1 + t2sina1, y1 - t2cosa1);
	     GL11.glVertex2f(x2 + t2sina2, y2 - t2cosa2);
	     GL11.glVertex2f(x2 - t2sina2, y2 + t2cosa2);
	     GL11.glVertex2f(x2 - t2sina2, y2 + t2cosa2);
	     GL11.glVertex2f(x1 - t2sina1, y1 + t2cosa1);
	     GL11.glVertex2f(x1 + t2sina1, y1 - t2cosa1);
	     glEnd();
	 }
}
