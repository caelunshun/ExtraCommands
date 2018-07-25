package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ShowPlayers extends ExtraCommandExecutor {

  public ShowPlayers(JavaPlugin plugin) {
    super(plugin, "showplayers");
  }

  @Override
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    if (!checkPlayer(sender))
      sendPlayerRequired(sender);
    for (Player p : Bukkit.getOnlinePlayers()) {
      ((Player) sender).showPlayer(plugin, p);
    }
    return true;
  }

  @Override
  protected List<String> parseTabComplete(CommandSender sender, String[] args) {
    return super.parseTabComplete(sender, args);
  }
}
