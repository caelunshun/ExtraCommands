package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class HidePlayers extends ExtraCommandExecutor {
	
	public HidePlayers(JavaPlugin plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("commands.hideplayers")) {
			if (sender instanceof Player) {
				if (sender.hasPermission("extracommands.hideplayers")) {
				Player player  = (Player) sender;
				for (Player p : Bukkit.getOnlinePlayers()) {
					player.hidePlayer(plugin, p);
				}
				player.sendMessage(ChatColor.GREEN + "Players hidden!");
			}
				else 
					sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
				
			
			}
			else 
				sender.sendMessage(ChatColor.RED + "This command must be executed by a player.");
			
			
		}
		
		return true;
	}
	
	
	
	
}
