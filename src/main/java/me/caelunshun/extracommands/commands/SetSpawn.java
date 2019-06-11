package me.caelunshun.extracommands.commands;

import me.caelunshun.extracommands.ExtraCommand;
import me.caelunshun.extracommands.ExtraCommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SetSpawn extends ExtraCommandExecutor {

  public SetSpawn(JavaPlugin plugin) {
    super(plugin, "setspawn");
  }

  @Override
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    if (!checkPlayer(sender)) {
      sendPlayerRequired(sender);
      return false;
    }

    ((Player) sender).getWorld().setSpawnLocation(((Player) sender).getLocation());
    return true;
  }
}
