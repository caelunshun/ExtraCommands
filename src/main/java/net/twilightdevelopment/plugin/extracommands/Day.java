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

public class Day extends ExtraCommandExecutor {

  public Day(JavaPlugin plugin) {
    super(plugin, "day");
  }

  @Override
  protected boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    World target = null;
    if (sender instanceof Player) {
      target = ((Player) sender).getWorld();
    } else target = Bukkit.getWorld("world");

    target.setTime(1000);
    return true;
  }

  private void sendResultMessage(CommandSender sender) {
    sender.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&',
            PlaceholderUtil.applyPlaceholders(
                plugin.getConfig().getString("messages.day-complete"), Collections.emptyMap())));
  }
}
