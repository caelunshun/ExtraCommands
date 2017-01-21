package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class KickAll implements CommandExecutor {

	private final JavaPlugin plugin;
	
	public KickAll(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kickall")
				&& plugin.getConfig().getBoolean("commands.kickall")
				&& args.length == 0) {
			if (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.kickall")) {
			for (Player p: Bukkit.getOnlinePlayers()) {
				if (!p.hasPermission("extracommands.dodgekickall")) {
				p.kickPlayer(plugin.getConfig().getString("messages.default-kickall-message"));
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Done!");
			return true;
		}
			else {
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("kickall")
			&& plugin.getConfig().getBoolean("commands.kickall")
			&& args.length >= 1) {
			if (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.kickall")) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPermission("extracommands.dodgekickall")) {
					String message = arrayToString(args);
					p.kickPlayer(message);
			}
			}
			sender.sendMessage(ChatColor.GREEN + "Done!");
			
			
		}
			else {
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			}
		}
		return true;
	}
	
	
	private String arrayToString(String[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(" ");
			}
			String item = array[i];
			sb.append(item);
		}
		return sb.toString();
		
		
	}

}
