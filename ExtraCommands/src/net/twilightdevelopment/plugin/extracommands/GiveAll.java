package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class GiveAll implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(ExtraCommands.getInstance().getConfig().getBoolean("commands.giveall"))) 
		return true;
		if (args.length == 2) {
			if (sender.hasPermission("extracommands.giveall") || sender instanceof ConsoleCommandSender) {
				int amount = Integer.parseInt(args[1]);
				if (amount > 64) sender.sendMessage("Amount cannot be more than 64.");
				else {
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.sendMessage(ExtraCommands.getInstance().getConfig().getString("messages.giveall-message"));
						Bukkit.getServer().dispatchCommand(sender, "give " + p.getName() + " " + args[0] + " " + amount);
				}
					sender.sendMessage(ChatColor.GREEN + "Done!");
				}
			}
			else sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			return true;
		}
		else return false;
	}
}

