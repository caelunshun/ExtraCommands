package net.twilightdevelopment.plugin.extracommands.commands;

import com.google.common.collect.ImmutableMap;
import net.twilightdevelopment.plugin.extracommands.ArrayUtil;
import net.twilightdevelopment.plugin.extracommands.ExtraCommand;
import net.twilightdevelopment.plugin.extracommands.ExtraCommandExecutor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
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
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    double x;
    double y;
    double z;
    World world = Bukkit.getWorld("world");
    if (args.length < 3) {
      sendUsage(sender);
      return false;
    }

    try {
      x = Double.valueOf(args[0]);
      y = Double.valueOf(args[1]);
      z = Double.valueOf(args[2]);
    } catch (NumberFormatException e) {
      sendUsage(sender);
      return false;
    }

    if (args.length >= 4) {
      world = Bukkit.getWorld(args[3]);
      if (world == null) {
        sender.sendMessage(
            PlaceholderUtil.applyPlaceholders(
                plugin.getConfig().getString("messages.world-not-found"),
                ImmutableMap.of("world", args[3])));
        return false;
      }
    }

    Location location = new Location(world, x, y, z);
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (dodgeCheck(p, sender)) {
        p.teleport(location);
        sendActionedMessage(p);
      }
    }

    return true;
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
