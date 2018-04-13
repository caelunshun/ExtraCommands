package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class ClearAll extends ExtraCommandExecutor {

	public ClearAll(JavaPlugin plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("extracommands.clearall") || sender instanceof ConsoleCommandSender) {
			if (plugin.getConfig().getBoolean("commands.clearall")) {
			if (args.length == 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (!p.hasPermission("extracommands.dodgeclearall")) {
					p.getInventory().clear();
					p.updateInventory();
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.clearall-message")));
					}
					
				}
				sender.sendMessage(ChatColor.GREEN + "Done!");
			}
			else return false;
		}
		}
		else 
			sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
		
		return true;
	}

}
