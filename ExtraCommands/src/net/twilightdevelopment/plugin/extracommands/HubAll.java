package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.twilightdevelopment.plugin.autohub.API;

public class HubAll implements CommandExecutor {

	private static JavaPlugin plugin;
	
	public HubAll(JavaPlugin plugin) {
		HubAll.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("commands.huball")) {
			if (sender.hasPermission("extracommands.huball") || sender instanceof ConsoleCommandSender) {
				if (TPAllToHub()) {
					sender.sendMessage(ChatColor.GREEN + "Teleported all players to the hub!");
				}
				else {
					sender.sendMessage(ChatColor.RED + "Error: Hub not set or AutoHub is not installed.");
				}
				
			
			
			}
			else {
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			}
			}
			return true;
		}
		
	
	

	private static boolean TPAllToHub() {
		if (Bukkit.getServer().getPluginManager().getPlugin("AutoHub") != null) {
			boolean done = false;
		String version = Bukkit.getServer().getPluginManager().getPlugin("AutoHub").getDescription().getVersion();
		if (version != "1.0" && version != "1.0.1" && version != "1.0.2") {
			
		int people = 0;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.hasPermission("extracommands.dodgehuball")) {
		done = API.tpToHub(p);
		p.sendMessage(plugin.getConfig().getString("messages.huball-message"));
		}
			else {
				people++;
			}
			if (people == Bukkit.getOnlinePlayers().size()) {
				return true;
			}
		}
		
	}
		return done;
	}
	return false;
	}
	
	

	
	
}