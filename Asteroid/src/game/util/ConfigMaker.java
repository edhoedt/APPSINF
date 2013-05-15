package game.util;

import game.Settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * Classe configurant la classe de champs Settings
 */
public class ConfigMaker {

	/*
	 * Enrregistre width, height, volume, player1 et player2 dans un
	 * fichier config.properties
	 */
	public static void setProperties(int width, int height, boolean volume, String player1, String player2){
		Properties prop = new Properties();

		try {
			//set les valeurs de config
			prop.setProperty("width", width+"");
			prop.setProperty("height", height+"");
			prop.setProperty("volume", volume+"");
			prop.setProperty("player1", player1);
			prop.setProperty("player2", player2);

			//sauve les valeurs dans config
			prop.store(new FileOutputStream("config/config.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Charge les propriétés de config.properties dans Settings
	 */
	public static void getProperties(){
		Properties prop = new Properties();

		try {
			//charge les propriétés de config
			prop.load(new FileInputStream("config/config.properties"));

			//envoie les propriétés dans Settings
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
