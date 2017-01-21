package net.twilightdevelopment.plugin.extracommands;


import org.bukkit.plugin.java.JavaPlugin;

public class ExtraCommands extends JavaPlugin {

	
	
	public void onEnable() {
		saveDefaultConfig();	
		
		getCommand("huball").setExecutor(new HubAll(this));
		getCommand("kickall").setExecutor(new KickAll(this));
		getCommand("tpall").setExecutor(new TPAll(this));
		getCommand("setspawn").setExecutor(new SetSpawn(this));
		getCommand("ip").setExecutor(new IP(this));
		getCommand("hideplayers").setExecutor(new HidePlayers(this));
		getCommand("showplayers").setExecutor(new SeePlayers(this));
	}
	
	public void onDisable() {
		saveDefaultConfig();
	}
	
	
	
	
	}
	
	
	
	
	
	

