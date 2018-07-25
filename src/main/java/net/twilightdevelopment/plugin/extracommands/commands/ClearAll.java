package net.twilightdevelopment.plugin.extracommands.commands;

import net.twilightdevelopment.plugin.extracommands.ExtraCommand;
import net.twilightdevelopment.plugin.extracommands.ExtraCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearAll extends ExtraCommandExecutor {

  public ClearAll(JavaPlugin plugin) {
    super(plugin, "clearall");
  }

  @Override
  protected boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (dodgeCheck(p, sender)) {
        p.getInventory().clear();
        sendActionedMessage(p);
      }
    }
    return true;
  }
}
