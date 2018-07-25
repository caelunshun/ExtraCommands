package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HidePlayers extends ExtraCommandExecutor {

  public HidePlayers(JavaPlugin plugin) {
    super(plugin, "hideplayers");
  }

  @Override
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    if (!checkPlayer(sender))
      sendPlayerRequired(sender);
    for (Player p : Bukkit.getOnlinePlayers()) {
      ((Player) sender).hidePlayer(plugin, p);
    }
    return true;
  }
}
