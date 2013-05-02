package game.util;

import game.Settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigMaker {
	public static void setProperties(int width, int height, boolean volume, String player1, String player2){
		Properties prop = new Properties();
		 
    	try {
    		//set the properties value
    		prop.setProperty("width", width+"");
    		prop.setProperty("height", height+"");
    		prop.setProperty("volume", volume+"");
    		prop.setProperty("player1", player1);
    		prop.setProperty("player2", player2);
 
    		//save properties to project root folder
    		prop.store(new FileOutputStream("config/config.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	
	public static void getProperties(){
		Properties prop = new Properties();
		 
    	try {
            //load a properties file
    		prop.load(new FileInputStream("config/config.properties"));
 
            //get the property value and print it out
    		Settings.WIDTH = Integer.parseInt(prop.getProperty("width"));
    		Settings.HEIGHT = Integer.parseInt(prop.getProperty("height"));
    		Settings.VOLUME = Boolean.parseBoolean(prop.getProperty("volume"));
    		Settings.PLAYER1 = prop.getProperty("player1");
    		Settings.PLAYER2 = prop.getProperty("player2");
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
}
