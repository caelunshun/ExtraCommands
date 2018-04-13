package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class KillAll extends ExtraCommandExecutor {
	
	public KillAll(JavaPlugin plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("commands.killall")) {
			if (sender.hasPermission("extracommands.killall") || sender instanceof ConsoleCommandSender) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPermission("extracommands.dodgekillall")) {
					p.setHealth(0);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.killall-message")));
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Done!");
		}
			else 
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			
			
		}
		
		return true;
}
	
	
	
	
}
