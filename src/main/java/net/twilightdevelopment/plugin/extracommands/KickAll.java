package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KickAll extends ExtraCommandExecutor {

  public KickAll(JavaPlugin plugin) {
    super(plugin);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("kickall")
        && plugin.getConfig().getBoolean("commands.kickall")
        && args.length == 0) {
      if (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.kickall")) {
        for (Player p : Bukkit.getOnlinePlayers()) {
          if (!p.hasPermission("extracommands.dodgekickall")) {
            p.kickPlayer(
                ChatColor.translateAlternateColorCodes(
                    '&', plugin.getConfig().getString("messages.default-kickall-message")));
          }
        }
        sender.sendMessage(ChatColor.GREEN + "Done!");
        return true;
      } else
        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else if (cmd.getName().equalsIgnoreCase("kickall")
        && plugin.getConfig().getBoolean("commands.kickall")
        && args.length >= 1) {
      if (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.kickall")) {
        for (Player p : Bukkit.getOnlinePlayers()) {
          if (!p.hasPermission("extracommands.dodgekickall")) {
            String message = arrayToString(args);
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', message));
          }
        }
        sender.sendMessage(ChatColor.GREEN + "Done!");

      } else
        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
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
}
