package me.caelunshun.extracommands.commands;

import me.caelunshun.extracommands.ExtraCommand;
import me.caelunshun.extracommands.ExtraCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Night extends ExtraCommandExecutor {

  public Night(JavaPlugin plugin) {
    super(plugin, "night");
  }

  @Override
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    World target;
    if (sender instanceof Player) {
      target = ((Player) sender).getWorld();
    } else target = Bukkit.getWorld("world");

    target.setTime(13000);
    return true;
  }

  @Override
  protected List<String> parseTabComplete(CommandSender sender, String[] args) {
    return super.parseTabComplete(sender, args);
  }
}
