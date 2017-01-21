package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class KillAll implements CommandExecutor {

	private final JavaPlugin plugin;
	
	public KillAll(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("commands.killall")) {
			if (sender.hasPermission("extracommands.killall") || sender instanceof ConsoleCommandSender) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPermission("extracommands.dodgekillall")) {
					p.setHealth(0);
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Done!");
		}
			
		}
		
		return true;
}
	
	
	
	
}
