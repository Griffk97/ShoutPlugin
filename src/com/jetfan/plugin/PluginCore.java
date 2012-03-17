package com.jetfan.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends JavaPlugin{
	
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	if(cmd.getName().equalsIgnoreCase("shout")){ // If the player typed /basic then do the following...
		if(sender instanceof Player){
			if (args.length == 1){
						List<Player> listen = getListeners((Player) sender);
						for (int i = 0; i<listen.size();i++){
							Player player = listen.get(i);
							if(player.isOnline()){
								player.sendMessage(args[0]);
							}
						}	
			}	
		} //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
		return true;
	}
	return false; 
}
		

	

Logger log;
	 
public void onEnable(){
		log = this.getLogger();
		log.info("Your plugin has been enabled!");
	}
 
public void onDisable(){
	log.info("Your plugin has been disabled.");
}







public List<Player> getListeners(Player origin) {
    List<Player> list = new ArrayList<Player>();
    Location sLoc = origin.getLocation();
    String sWorld = sLoc.getWorld().getName();
    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
        
        if (player != null) {
                Location pLoc = player.getLocation();
                if (sWorld.equals(pLoc.getWorld().getName())) {
                    int dx = sLoc.getBlockX() - pLoc.getBlockX();
                    int dz = sLoc.getBlockZ() - pLoc.getBlockZ();
                    dx = dx * dx;
                    dz = dz * dz;
                    int d = (int) Math.sqrt(dx + dz);

                    if (d <= 100) {
                        list.add(player);
                    }
                }
        }
    }
 
      
    return list;
}


}
