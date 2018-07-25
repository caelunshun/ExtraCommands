package net.twilightdevelopment.plugin.extracommands;

import net.twilightdevelopment.plugin.autohub.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HubAll extends net.twilightdevelopment.plugin.extracommands.ExtraCommandExecutor {

  public HubAll(JavaPlugin plugin) {
    super(plugin, "huball");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (plugin.getConfig().getBoolean("commands.huball")) {
      if (sender.hasPermission("extracommands.huball") || sender instanceof ConsoleCommandSender) {
        if (tpAllToHub(sender)) {
          sender.sendMessage(ChatColor.GREEN + "Teleported all players to the hub!");
        } else
          sender.sendMessage(ChatColor.RED + "Error: Hub not set or AutoHub is not installed.");
      } else
        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }

  private boolean tpAllToHub(CommandSender sender) {
    if (Bukkit.getServer().getPluginManager().getPlugin("AutoHub") != null) {
      boolean done = false;
      String version =
          Bukkit.getServer().getPluginManager().getPlugin("AutoHub").getDescription().getVersion();
      if (!version.equals("1.0") && !version.equals("1.0.1") && !version.equals("1.0.2")) {

        int people = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
          if (!p.hasPermission("extracommands.dodgehuball")
              && (plugin.getConfig().getBoolean("affect-command-issuer.huball") || !p.equals(sender))) {
            done = API.tpToHub(p);
            p.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&', plugin.getConfig().getString("messages.huball-message")));
          } else people++;

          if (people == Bukkit.getOnlinePlayers().size()) {
            return true;
          }
        }
      }
      return done;
    }
    return false;
  }
}
