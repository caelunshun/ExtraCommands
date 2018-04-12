package net.twilightdevelopment.plugin.extracommands;

import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public abstract class ExtraCommandExecutor implements CommandExecutor {
	protected final JavaPlugin plugin;
	protected final String cmdName;
	public ExtraCommandExecutor(JavaPlugin plugin, String cmdName) {
		Validate.notNull(plugin);
		Validate.notNull(cmdName);
		this.plugin = plugin;
		this.cmdName = cmdName;
	}
	
	public ExtraCommandExecutor(JavaPlugin plugin) {
		Validate.notNull(plugin);
		this.plugin = plugin;
		cmdName = null;
	}
	
	@Override
	public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);
	
	protected boolean isEnabled() {
		return plugin.getConfig().getBoolean("commands." + cmdName);
	}
	
	/**
	 * Checks that a command sender has the permission
	 * to execute the command represented by this ExtraCommandExecutor. If the sender was a 
	 * {@link ConsoleCommandSender}, the method will return
	 * true as if it were a player with the permission.
	 * 
	 * A permission is checked using the format extracommands.commandName.
	 * @param sender The {@link CommandSender} whose permission will be checked.
	 * @return True if the sender is a {@link ConsoleCommandSender} or it
	 * has the required permission associated with {@code cmdName}
	 */
	protected boolean checkPerms(CommandSender sender) {
		return sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands." + cmdName);
	}
	
	protected void sendDisabledMessage(CommandSender sender) {
		sender.sendMessage(plugin.getConfig().getString("messages.command-disabled-message"));
	}
	
	protected void sendNoPermissionMessage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
	}
	
	protected boolean preconditionCheck(CommandSender sender) {
		Validate.notNull(sender);
		if (!isEnabled()) {
			sendDisabledMessage(sender);
			return false;
		}
		if (!checkPerms(sender)) {
			sendNoPermissionMessage(sender);
			return false;
		}
		
		return true;
	}
}
