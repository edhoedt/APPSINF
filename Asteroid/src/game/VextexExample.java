/*
 * 
 * Classe de test main pour lancer l'entité de test Rock
 * ainsi que tester les vecteurs, collisions, ...
 * 
 * A supprimer après essai
 * 
 */

package game;

import java.util.ArrayList;

import game.util.Polygon;
import game.util.VertexDrawer;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
 
public class VextexExample {
	
	private int[] polyXArray = {-13,14,-13,-5,-13};
	private int[] polyYArray = {-15,0,15,0,-15};
	private int i = 0;
	private int j = 0;
	int thickness = 1;
	Polygon ship = new Polygon(polyXArray, polyYArray);
	private int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
	private int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};
	Polygon rock = new Polygon(sPolyXArray, sPolyYArray);
	public static ArrayList<RockTest> rocks = new ArrayList<RockTest>();
	
	
    public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(800,600));
	    Display.setVSyncEnabled(true);
	    Display.create();
	    for(int i = 0; i < 10; i++){
			
			int randomStartXPos = (int) (Math.random() * (800 - 40) + 1);
			int randomStartYPos = (int) (Math.random() * (600 - 40) + 1);
			
			rocks.add(new RockTest(RockTest.getpolyXArray(randomStartXPos), RockTest.getpolyYArray(randomStartYPos), 13, 26, 31, randomStartXPos, randomStartYPos));
			
			RockTest.rocks = rocks;
			
		}
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
 
        VertexDrawer.initGL(800,600);
 
	while (!Display.isCloseRequested()) {
		i = i+j+1;
		VertexDrawer.clear();
		
		VertexDrawer.setColor(1.0f, 1.0f, 1.0f);
	    
		VertexDrawer.drawPolygonTo(ship, thickness, i, 300);
		if(i > 800)
		{
			i = 0;
			j++;
		}
 
		for(RockTest rock : rocks){
			
			rock.move(); 
			
			VertexDrawer.drawPolygon(rock, thickness);
			
		}
		
	    Display.update();
	}
	
	Display.destroy();
    }
    
    public static void main(String[] argv) {
        VextexExample quadExample = new VextexExample();
        quadExample.start();
    }
}