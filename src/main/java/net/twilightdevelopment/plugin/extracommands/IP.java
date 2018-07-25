package net.twilightdevelopment.plugin.extracommands;

import com.google.common.collect.ImmutableMap;
import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IP extends ExtraCommandExecutor {

  public IP(JavaPlugin plugin) {
    super(plugin, "ip");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (plugin.getConfig().getBoolean("commands.ip")) {
      if (args.length == 0 && sender instanceof Player) {
        Player player = (Player) sender;

        String address = player.getAddress().getHostString();
        player.sendMessage(
            ChatColor.AQUA + "Your IP address is " + ChatColor.DARK_AQUA + address + ".");

        return true;
      } else if (args.length == 1)
        if (sender instanceof ConsoleCommandSender || sender.hasPermission("extracommands.ip")) {

          Player victim = Bukkit.getServer().getPlayer(args[0]);
          if (victim != null) {
            String address = victim.getAddress().getHostName();
            sender.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    PlaceholderUtil.applyPlaceholders(
                        plugin.getConfig().getString("messages.ip-complete"),
                        ImmutableMap.of("player", victim.getName(), "ip", address))));
          } else {
            String victimName = args[0];
            sender.sendMessage(ChatColor.RED + "Player " + victimName + " not found.");
          }

        } else
          sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }

  @Override
  public List<String> parseTabComplete(CommandSender sender, String[] args) {
    if (args.length == 1) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(
          args[0], ArrayUtil.applyModification(Bukkit.getOnlinePlayers(), Player::getName), result);
      Collections.sort(result);
      return result;
    }
    return super.parseTabComplete(sender, args);
  }
}
