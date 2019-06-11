package me.caelunshun.extracommands.commands;

import com.google.common.collect.ImmutableMap;
import me.caelunshun.extracommands.placeholder.PlaceholderUtil;
import net.md_5.bungee.api.ChatColor;
import me.caelunshun.extracommands.ArrayUtil;
import me.caelunshun.extracommands.ExtraCommand;
import me.caelunshun.extracommands.ExtraCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IP extends ExtraCommandExecutor {

  public IP(JavaPlugin plugin) {
    super(plugin, "ip");
  }

  @Override
  protected boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    if (args.length == 0) {
      sendUsage(sender);
      return false;
    }

    Player target = Bukkit.getPlayer(args[0]);
    if (target == null) {
      sender.sendMessage(
          PlaceholderUtil.applyPlaceholders(
              ChatColor.translateAlternateColorCodes(
                  '&', plugin.getConfig().getString("messages.player-not-found")),
              ImmutableMap.of("player", args[0])));
      return false;
    }

    cmd.placeholder("player", target.getName());
    cmd.placeholder("ip", target.getAddress().getHostName());
    return true;
  }

  @Override
  public List<String> parseTabComplete(CommandSender sender, String[] args) {
    if (args.length == 1) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(
          args[0], ArrayUtil.applyModification(Bukkit.getOnlinePlayers(), Player::getName), result);
      Collections.sort(result);
      return result;
    }
    return super.parseTabComplete(sender, args);
  }
}
