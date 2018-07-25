package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class Night extends ExtraCommandExecutor {

  public Night(JavaPlugin plugin) {
    super(plugin, "night");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (plugin.getConfig().getBoolean("commands.night")) {
      if (sender.hasPermission("extracommands.night") && sender instanceof Player) {
        Player player = (Player) sender;

        World world = player.getWorld();
        world.setFullTime(13000);
        sendResultMessage(sender);
      } else if (sender instanceof ConsoleCommandSender) {
        World world = Bukkit.getWorld("world");
        world.setFullTime(13000);
        sendResultMessage(sender);
      } else
        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }

  @Override
  protected List<String> parseTabComplete(CommandSender sender, String[] args) {
    return super.parseTabComplete(sender, args);
  }

  private void sendResultMessage(CommandSender sender) {
    sender.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&',
            PlaceholderUtil.applyPlaceholders(
                plugin.getConfig().getString("messages.night-complete"), Collections.emptyMap())));
  }
}
