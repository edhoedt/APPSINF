package game.util;

import java.util.HashMap;
import java.util.Set;

import game.Command;
import game.model.entity.Spaceship;

/*
 * Classe permettant de faire correspondre les joueurs, les touches et les actions(game.Command)
 * cette classe est implémentée comme un singleton pour éviter d'avoir des références vers des joueurs qui
 * n'existent plus. TOUTES les action sont répertoriées ici.
 */
public class ControlsStore {
	
	private static ControlsStore store;
	private HashMap<Integer, HashMap<Spaceship,Command>> controls;

	//contructeur privé
	private ControlsStore(){
		controls= new HashMap<Integer, HashMap<Spaceship,Command>>();
	}
	
	//renvoie une instance unique de ControlStore
	public static ControlsStore getInstance(){
		if(store==null)
			store=new ControlsStore();
		return store;
	}
	
	//vide toute les données du store
	public void clear(){
		if(controls != null){
			controls.clear();
		}
	}
	
	//vide toutes les données liées à un joueur
	public void clearPlayer(Spaceship player){
		controls.remove(player);
	}
	
	//lie la touche key(lwjgl reference) à l'action action pour le vaisseau player
	public void bind(Spaceship player, int key, Command action){
		if(controls.containsKey(key))
			controls.get(key).put(player, action);
		else{
			HashMap<Spaceship, Command> buff = new HashMap<Spaceship, Command>();
			buff.put(player, action);
			controls.put(key, buff);
		}
	}
	
	//renvoie la liste des touches utilisées par TOUS les joueurs
	public Set<Integer> getKeys(){
		return this.controls.keySet();
	}
	
	//renvoie les paires spaceship/action liées à la une touche
	public HashMap<Spaceship, Command> getAction(int key){
		return this.controls.get(key);
	}

}