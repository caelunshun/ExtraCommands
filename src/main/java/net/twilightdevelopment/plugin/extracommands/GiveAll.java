package net.twilightdevelopment.plugin.extracommands;

import com.google.common.collect.ImmutableList;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiveAll extends ExtraCommandExecutor {

  private static final List<String> MATERIAL_NAMES;

  static {
    List<String> buffer = new ArrayList<>(Material.values().length);
    for (Material mat : Material.values()) {
      buffer.add(mat.name());
    }
    MATERIAL_NAMES = ImmutableList.copyOf(buffer);
  }

  public GiveAll(JavaPlugin plugin) {
    super(plugin, "giveall");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!preconditionCheck(sender)) return true;

    int amount = 1;
    Material type = null;
    if (args.length == 0 || args.length > 2) {
      sender.sendMessage(ChatColor.RED + "Usage: /giveall <type> [amount]");
      return true;
    }
    if (args.length == 1) {
      type = Material.matchMaterial(args[0]);
      if (type == null) {
        sender.sendMessage(String.format(ChatColor.RED + "Item type %s not found!", args[0]));
        return true;
      }
    } else if (args.length == 2) {
      type = Material.matchMaterial(args[0]);
      if (type == null) {
        sender.sendMessage(String.format(ChatColor.RED + "Item type %s not found!", args[0]));
        return true;
      }

      try {
        amount = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        sender.sendMessage(ChatColor.RED + "Usage: /giveall <type> [amount]");
        return true;
      }
    }
    ItemStack toGive = new ItemStack(type, amount);
    String message =
        ChatColor.translateAlternateColorCodes(
            '&', plugin.getConfig().getString("messages.giveall-message"));
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (!p.hasPermission("extracommands.dodgegiveall")) {
        p.getInventory().addItem(toGive);
        p.sendMessage(message);
      }
    }

    sender.sendMessage(ChatColor.GREEN + "Done!");

    return true;
  }

  @Override
  public List<String> parseTabComplete(CommandSender sender, String[] args) {
    if (args.length == 1) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(args[0], MATERIAL_NAMES, result);
      Collections.sort(result);
      return result;
    }
    if (args.length == 2) {
      if (args[1].isEmpty()) {
        // Suggest "1" as default value for amount
        return Collections.singletonList("1");
      }
    }

    return Collections.emptyList();
  }
}
