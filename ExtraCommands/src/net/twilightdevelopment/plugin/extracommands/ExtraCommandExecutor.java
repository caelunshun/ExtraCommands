package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ExtraCommandExecutor implements CommandExecutor {
	protected JavaPlugin plugin;
	
	public ExtraCommandExecutor(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);
	

}
