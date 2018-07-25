package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearAll extends ExtraCommandExecutor {

  public ClearAll(JavaPlugin plugin) {
    super(plugin, "clearall");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!preconditionCheck(sender)) return true;

    for (Player p : Bukkit.getOnlinePlayers()) {
      if (dodgeCheck(p, sender)) {
        p.getInventory().clear();
        p.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&', plugin.getConfig().getString("messages.clearall-message")));
      }
    }

    sendSuccess(sender);
    return true;
  }
}
