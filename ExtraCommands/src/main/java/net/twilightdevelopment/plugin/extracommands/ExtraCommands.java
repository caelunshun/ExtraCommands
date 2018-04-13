package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.plugin.java.JavaPlugin;

import net.twilightdevelopment.plugin.extracommands.autoupdater.UpdaterMain;

public class ExtraCommands extends JavaPlugin {

	public static ExtraCommands instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		fillConfig();

		try {
			if (getConfig().getBoolean("update-checker"))
				new Thread(new UpdaterMain(this)).start();

		} catch (Exception e) {
		}

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
		getCommand("clearall").setExecutor(new ClearAll(this));
		getCommand("giveall").setExecutor(new GiveAll(this));
	}

	public void onDisable() {
		saveDefaultConfig();
		fillConfig();
	}

	public void fillConfig() {

		if (!getConfig().isSet("commands.huball"))
			getConfig().addDefault("commands.huball", true);
		if (!getConfig().isSet("commands.killall"))
			getConfig().addDefault("commands.killall", true);
		if (!getConfig().isSet("commands.kickall"))
			getConfig().addDefault("commands.kickall", true);
		if (!getConfig().isSet("commands.ip"))
			getConfig().addDefault("commands.ip", true);
		if (!getConfig().isSet("commands.night"))
			getConfig().addDefault("commands.night", true);
		if (!getConfig().isSet("commands.day"))
			getConfig().addDefault("commands.day", true);
		if (!getConfig().isSet("commands.showplayers"))
			getConfig().addDefault("commands.showplayers", true);
		if (!getConfig().isSet("commands.hideplayers"))
			getConfig().addDefault("commands.hideplayers", true);
		;
		if (!getConfig().isSet("commands.setspawn"))
			getConfig().addDefault("commands.setspawn", true);
		if (!getConfig().isSet("commands.clearall"))
			getConfig().addDefault("commands.clearall", true);
		if (!getConfig().isSet("commands.giveall"))
			getConfig().addDefault("commands.giveall", true);
		if (!getConfig().isSet("update-checker"))
			getConfig().addDefault("update-checker", true);
		if (!getConfig().isSet("messages.command-disabled-message"))
			getConfig().addDefault("messages.command-disabled-message", "This command is disabled.");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public static ExtraCommands getInstance() {
		return instance;
	}
}
