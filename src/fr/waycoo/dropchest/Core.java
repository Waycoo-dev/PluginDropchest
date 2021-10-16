package fr.waycoo.dropchest;

import fr.waycoo.dropchest.commands.CommandDropchest;
import fr.waycoo.dropchest.config.Config;
import fr.waycoo.dropchest.listener.ChestRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin{
	private Config config;
	
	@Override
	public void onEnable() {
		registerCommands();
		registerEvents();
		this.config = new Config(this,"config");
	}
	
	private void registerCommands() {
		new CommandDropchest(this, "dropchest");
	}
	
	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new ChestRunnable(this), this);
	}
	
    	public Config getConfiguration() { 
    		return config;
    	}
}
