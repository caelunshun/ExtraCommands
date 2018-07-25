package net.twilightdevelopment.plugin.extracommands.commands;

import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.ExtraCommand;
import net.twilightdevelopment.plugin.extracommands.ExtraCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class KickAll extends ExtraCommandExecutor {

  public KickAll(JavaPlugin plugin) {
    super(plugin, "kickall");
  }

  @Override
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    String reason;
    if (args.length == 0) {
      reason =
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.default-kickall"));
    } else reason = arrayToString(args);

    for (Player p : Bukkit.getOnlinePlayers()) {
      if (dodgeCheck(p, sender)) p.kickPlayer(reason);
    }
    return true;
  }

  private String arrayToString(String[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      if (i > 0) {
        sb.append(" ");
      }
      String item = array[i];
      sb.append(item);
    }
    return sb.toString();
  }

  @Override
  public List<String> parseTabComplete(CommandSender sender, String[] args) {
    return Collections.singletonList("[-reason-]");
  }
}
