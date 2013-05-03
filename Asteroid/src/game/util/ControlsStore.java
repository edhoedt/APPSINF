package game.util;

import java.util.HashMap;
import java.util.Set;

import game.Command;
import game.model.entity.Spaceship;

public class ControlsStore {
	
	private static ControlsStore store;
	private HashMap<Integer, HashMap<Spaceship,Command>> controls;

	
	private ControlsStore(){
		controls= new HashMap<Integer, HashMap<Spaceship,Command>>();
	}
	
	public static ControlsStore getInstance(){
		if(store==null)
			store=new ControlsStore();
		return store;
	}
	
	public void clear(){
		if(controls != null){
			controls.clear();
		}
	}
	
	public void clearPlayer(Spaceship player){
		controls.remove(player);
	}
	
	public void bind(Spaceship player, int key, Command action){
		if(controls.containsKey(key))
			controls.get(key).put(player, action);
		else{
			HashMap<Spaceship, Command> buff = new HashMap<Spaceship, Command>();
			buff.put(player, action);
			controls.put(key, buff);
		}
	}
	
	public Set<Integer> getKeys(){
		return this.controls.keySet();
	}
	
	public HashMap<Spaceship, Command> getAction(int key){
		return this.controls.get(key);
	}

}