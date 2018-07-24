package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Day extends ExtraCommandExecutor {

  public Day(JavaPlugin plugin) {
    super(plugin, "day");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (plugin.getConfig().getBoolean("commands.day")) {
      if (sender.hasPermission("extracommands.day") && sender instanceof Player) {
        Player player = (Player) sender;

        World world = player.getWorld();
        world.setFullTime(1000);
        player.sendMessage(ChatColor.GREEN + "Time set to day!");
      } else if (sender instanceof ConsoleCommandSender) {
        World world = Bukkit.getWorld("world");
        world.setFullTime(1000);
        sender.sendMessage(ChatColor.GREEN + "Time set to day!");
      } else if (!sender.hasPermission("extracommands.day"))
        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }
}
