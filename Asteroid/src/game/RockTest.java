/*
 * 
 * Classe de test entité Rock
 * 
 * A supprimer après essai
 * 
 */

package game;

import java.util.ArrayList;

import game.util.Polygon;

public class RockTest extends Polygon {

	public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
	public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};
	
	int xDirection = 1;
	int yDirection = 1;
	int uLeftXPos, uLeftYPos;
	
	public static ArrayList<RockTest> rocks = new ArrayList<RockTest>();
	
	public RockTest(int[] xpoints, int[] ypoints, int npoints, int width,
			int height, int randomStartXPos, int randomStartYPos) {
		super(xpoints, ypoints);
		
		this.xDirection = (int) (Math.random() * 4 + 1);
		this.yDirection = (int) (Math.random() * 4 + 1);
		
		this.uLeftXPos = randomStartXPos;
		this.uLeftYPos = randomStartYPos;
	}

	public void move(){
			
			for(RockTest rock : rocks){
				
				if(rock != this && rock.intersects(this)){
	
					int tempXDirection = this.xDirection;
					int tempYDirection = this.yDirection;
					
					this.xDirection = rock.xDirection;
					this.yDirection = rock.yDirection;
					
					rock.xDirection = tempXDirection;
					rock.yDirection = tempYDirection;
					
				}
			}
			
			int uLeftXPos = super.getXpoint(0); 
			
			int uLeftYPos = super.getYpoint(0);
			
			if (uLeftXPos < 0 || (uLeftXPos + 25) > 800) xDirection = -xDirection; 
			
			if (uLeftYPos < 0 || (uLeftYPos + 50) > 600) yDirection = -yDirection;
			
			for (int i = 0; i < super.getXpoints().length; i++){
				super.setXpoint(i, xDirection+super.getXpoint(i));
				super.setYpoint(i, yDirection+super.getYpoint(i));
			}
			
		}
	
public static int[] getpolyXArray(int randomStartXPos){
		
		// Clones the array so that the original shape isn't changed for the asteroid
		
		int[] tempPolyXArray = (int[])sPolyXArray.clone();
		
		for (int i = 0; i < tempPolyXArray.length; i++){
			
			tempPolyXArray[i] += randomStartXPos;
			
		}
		
		return tempPolyXArray;
		
	}
	
	// public method available for creating Polygon y point arrays
	
	public static int[] getpolyYArray(int randomStartYPos){
		
		// Clones the array so that the original shape isn't changed for the asteroid
		
		int[] tempPolyYArray = (int[])sPolyYArray.clone();
		
		for (int i = 0; i < tempPolyYArray.length; i++){
			
			tempPolyYArray[i] += randomStartYPos;
			
		}
		
		return tempPolyYArray;
		
	}
	
}
