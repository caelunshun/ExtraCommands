package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TPAll extends ExtraCommandExecutor {

  public TPAll(JavaPlugin plugin) {
    super(plugin, "tpall");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("tpall")
        && plugin.getConfig().getBoolean("commands.tpall")) {
      if (sender.hasPermission("extracommands.tpall") || sender instanceof ConsoleCommandSender) {
        if (args.length == 3) {
          double x = 0;
          double y = 0;
          double z = 0;
          boolean failed = false;
          try {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            z = Double.parseDouble(args[2]);
          } catch (NumberFormatException e) {
            failed = true;
          }
          if (failed == false) {
            teleportAll(new Location(Bukkit.getWorld("world"), x, y, z));
            sender.sendMessage(ChatColor.GREEN + "Done!");
          }

        } else if (args.length == 4) {
          double x = 0;
          double y = 0;
          double z = 0;
          String w = args[3];
          boolean failed = false;
          try {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            z = Double.parseDouble(args[2]);
          } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Usage: /tpall <x> <y> <z> [world]");
            failed = true;
          }
          if (!failed) {
            World world = Bukkit.getWorld(w);
            if (world == null) {
              sender.sendMessage(
                  String.format(ChatColor.RED + "Error: World %s does not exist.", w));
            }

            teleportAll(new Location(Bukkit.getWorld(w), x, y, z));
            sender.sendMessage(ChatColor.GREEN + "Done!");
          } else
            sender.sendMessage(
                ChatColor.RED + "Incorrect usage of command. Usage: /tpall <x> <y> <z>");
        }

      } else
        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }

  public void teleportAll(Location loc) {
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (!p.hasPermission("extracommands.dodgetpall")) {
        p.teleport(loc);
        p.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&', plugin.getConfig().getString("messages.tpall-message")));
      }
    }
  }

  @Override
  protected List<String> parseTabComplete(CommandSender sender, String[] args) {
    if (sender instanceof Player) {
      // Suggest player's current location
      Player player = (Player) sender;
      switch (args.length) {
        case 1:
          return Collections.singletonList(player.getLocation().getX() + "");
        case 2:
          return Collections.singletonList(player.getLocation().getBlockY() + "");
        case 3:
          return Collections.singletonList(player.getLocation().getZ() + "");
        default:
          break;
      }
    }
    if (args.length == 4) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(
          args[3], ArrayUtil.applyModification(Bukkit.getWorlds(), World::getName), result);
      Collections.sort(result);
      return result;
    }

    return super.parseTabComplete(sender, args);
  }
}
