package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GiveAll implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 2) {
			if (sender.hasPermission("extracommands.giveall") || sender instanceof ConsoleCommandSender) {
				int amount = Integer.parseInt(args[1]);
				if (amount > 64) sender.sendMessage("Amount cannot be more than 64.");
				else {
					for (Player p : Bukkit.getOnlinePlayers())
						Bukkit.getServer().dispatchCommand(sender, "give " + p.getName() + " " + args[0] + " " + amount);
				}
			}
			return true;
		}
		else return false;
	}
}
