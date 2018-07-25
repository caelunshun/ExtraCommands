package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class ClearAll extends ExtraCommandExecutor {

  public ClearAll(JavaPlugin plugin) {
    super(plugin, "clearall");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender.hasPermission("extracommands.clearall") || sender instanceof ConsoleCommandSender) {
      if (plugin.getConfig().getBoolean("commands.clearall")) {
        if (args.length == 0) {
          for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.hasPermission("extracommands.dodgeclearall")
                && (plugin.getConfig().getBoolean("affect-command-issuer.clearall")
                    || !p.equals(sender))) {
              p.getInventory().clear();
              p.updateInventory();
              p.sendMessage(
                  ChatColor.translateAlternateColorCodes(
                      '&', plugin.getConfig().getString("messages.clearall-message")));
            }
          }
          sender.sendMessage(
              ChatColor.translateAlternateColorCodes(
                  '&',
                  PlaceholderUtil.applyPlaceholders(
                      plugin.getConfig().getString("messages.clearall-complete"),
                      Collections.emptyMap())));
        } else return false;
      } else
        sender.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&', plugin.getConfig().getString("messages.command-disabled-message")));
    } else
      sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");

    return true;
  }
}
