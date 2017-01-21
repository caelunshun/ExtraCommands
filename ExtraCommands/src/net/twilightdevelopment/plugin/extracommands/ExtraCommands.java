package net.twilightdevelopment.plugin.extracommands;


import org.bukkit.plugin.java.JavaPlugin;

public class ExtraCommands extends JavaPlugin {

	
	
	public void onEnable() {
		saveDefaultConfig();	
		fillConfig();
		
		getCommand("huball").setExecutor(new HubAll(this));
		getCommand("kickall").setExecutor(new KickAll(this));
		getCommand("tpall").setExecutor(new TPAll(this));
		getCommand("setspawn").setExecutor(new SetSpawn(this));
		getCommand("ip").setExecutor(new IP(this));
		getCommand("hideplayers").setExecutor(new HidePlayers(this));
		getCommand("showplayers").setExecutor(new ShowPlayers(this));
		getCommand("killall").setExecutor(new KillAll(this));
		getCommand("day").setExecutor(new Day(this));
		getCommand("night").setExecutor(new Night(this));
	}
	
	public void onDisable() {
		saveDefaultConfig();
		fillConfig();
	}
	
	
	public void fillConfig() {
		
		if (!getConfig().isSet("commands.huball")) getConfig().addDefault("commands.huball", true);
		if (!getConfig().isSet("commands.killall")) getConfig().addDefault("commands.killall", true);
		if (!getConfig().isSet("commands.kickall")) getConfig().addDefault("commands.kickall", true);
		if (!getConfig().isSet("commands.ip")) getConfig().addDefault("commands.ip", true);
		if (!getConfig().isSet("commands.night")) getConfig().addDefault("commands.night", true);
		if (!getConfig().isSet("commands.day")) getConfig().addDefault("commands.day", true);
		if (!getConfig().isSet("commands.showplayers")) getConfig().addDefault("commands.showplayers", true);
		if (!getConfig().isSet("commands.hideplayers")) getConfig().addDefault("commands.hideplayers", true);;
		if (!getConfig().isSet("commands.setspawn")) getConfig().addDefault("commands.setspawn", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	}
	
	
	
	
	
	

