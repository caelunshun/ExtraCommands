package net.twilightdevelopment.plugin.extracommands.commands;

import net.twilightdevelopment.plugin.autohub.API;
import net.twilightdevelopment.plugin.autohub.AutoHub;
import net.twilightdevelopment.plugin.extracommands.ExtraCommand;
import net.twilightdevelopment.plugin.extracommands.ExtraCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HubAll extends ExtraCommandExecutor {

  public HubAll(JavaPlugin plugin) {
    super(plugin, "huball");
  }

  @Override
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    if (!tpAllToHub(sender)) {
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.autohub-required")));
    }
    return true;
  }

  private boolean tpAllToHub(CommandSender sender) {
    if (Bukkit.getPluginManager().getPlugin("AutoHub") == null
            || !AutoHub.isHubSet) return false;
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (dodgeCheck(p, sender)) {
        p.teleport(API.getHubLocation());
        sendActionedMessage(p);
      }
    }
    return true;
  }
}
