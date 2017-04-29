package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class IP implements CommandExecutor {
	

private final JavaPlugin plugin;
	
	public IP(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("commands.ip")) {
			if (args.length == 0 && sender instanceof Player) {
				Player player = (Player) sender;
				
				String address = player.getAddress().getHostString();
				player.sendMessage(ChatColor.AQUA + "Your IP address is " + ChatColor.DARK_AQUA
						+ address + ".");
				
				return true;
			}
			else if (args.length == 1) 
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.ip")) {
				
			
					Player victim = Bukkit.getServer().getPlayer(args[0]);
					if (victim != null) {
						String address = victim.getAddress().getHostName();
						sender.sendMessage(ChatColor.AQUA + "Player " + victim.getName()
						+ "'s IP address is " + ChatColor.DARK_AQUA + address + ".");
					}
					else {
						String victimName = args[0];
						sender.sendMessage("ERROR: Player " + victimName + " not found.");
					}
				
				
			}
				else 
					sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
				
		}
		return true;
	}
}
