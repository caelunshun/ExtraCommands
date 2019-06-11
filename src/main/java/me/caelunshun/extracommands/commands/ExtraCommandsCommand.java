package me.caelunshun.extracommands.commands;

import com.google.common.collect.ImmutableSet;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ExtraCommandsCommand implements CommandExecutor, TabCompleter {
  private static final Set<String> COMMANDS = ImmutableSet.of("info", "reload");
  private static final String INFO =
      ChatColor.GRAY
          + "--------"
          + ChatColor.AQUA
          + ChatColor.BOLD
          + "ExtraCommands"
          + ChatColor.GRAY
          + "--------\n"
          + ChatColor.AQUA
          + "Version: "
          + ChatColor.GRAY
          + "{version}";

  private final JavaPlugin plugin;

  public ExtraCommandsCommand(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if ((args.length == 0 || (args.length >= 1 && args[0].equalsIgnoreCase("info")))
        && (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.info"))) {
      sender.sendMessage(INFO.replaceAll("\\{version}", plugin.getDescription().getVersion()));
      return true;
    }

    if (args.length >= 1
        && args[0].equalsIgnoreCase("reload")
        && (sender instanceof ConsoleCommandSender
            || sender.hasPermission("extracommands.reload"))) {
      plugin.reloadConfig();
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.plugin-reloaded")));
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(
      CommandSender sender, Command command, String alias, String[] args) {
    if (args.length == 1) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(args[0], COMMANDS, result);
      Collections.sort(result);
      return result;
    }

    return Collections.emptyList();
  }
}
