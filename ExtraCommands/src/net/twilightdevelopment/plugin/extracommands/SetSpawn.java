package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class SetSpawn implements CommandExecutor {

	private final JavaPlugin plugin;
	
	public SetSpawn(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setspawn") && plugin.getConfig().getBoolean("commands.setspawn")) {
			if (sender instanceof Player) {
				if (sender.hasPermission("extracommands.setspawn")) {
				Player player = (Player) sender;
				World w = player.getWorld();
				
				w.setSpawnLocation((int)player.getLocation().getX(), (int)player.getLocation().getY(), (int)player.getLocation().getZ());
				player.sendMessage(ChatColor.GREEN + "Spawn set.");
			}
			}
			else {
				sender.sendMessage("You must be a player to execute this command.");
			}
			return true;
		}
		return false;
	}

}
