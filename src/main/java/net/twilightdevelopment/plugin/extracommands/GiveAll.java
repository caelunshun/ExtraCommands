package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class GiveAll extends ExtraCommandExecutor {
	
	public GiveAll(JavaPlugin plugin) {
		super(plugin, "giveall");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!preconditionCheck(sender))
			return true;
		
		int amount = 1;
		Material type = null;
		if (args.length == 0 || args.length > 2) {
			sender.sendMessage(ChatColor.RED + "Usage: /giveall <type> [amount]");
			return true;
		}
		if (args.length == 1) {
			type = Material.matchMaterial(args[0]);
			if (type == null) {
				sender.sendMessage(String.format(ChatColor.RED + "Item type %s not found!", args[0]));
				return true;
			}
		}
		else if (args.length == 2) {
			type = Material.matchMaterial(args[0]);
			if (type == null) {
				sender.sendMessage(String.format(ChatColor.RED + "Item type %s not found!", args[0]));
				return true;
			}
			
			try {
				amount = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				sender.sendMessage(ChatColor.RED + "Usage: /giveall <type> [amount]");
				return true;
			}
		}
		ItemStack toGive = new ItemStack(type, amount);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.hasPermission("extracommands.dodgegiveall")) {
				p.getInventory().addItem(toGive);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.giveall-message")));
			}
		}
		
		sender.sendMessage(ChatColor.GREEN + "Done!");
		
		return true;
	}
}

