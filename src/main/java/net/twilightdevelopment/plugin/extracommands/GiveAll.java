package net.twilightdevelopment.plugin.extracommands;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
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
  public boolean execute(ExtraCommand cmd, CommandSender sender, String[] args) {
    int amount = 1;
    Material type = null;
    if (args.length == 0) {
      sendUsage(sender);
      return false;
    }
    type = Material.matchMaterial(args[0]);
    if (type == null) {
      sender.sendMessage(String.format(ChatColor.RED + "Item type %s not found!", args[0]));
      return false;
    }
    if (args.length == 2) {
      try {
        amount = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        sendUsage(sender);
        return false;
      }
    }

    ItemStack toGive = new ItemStack(type, amount);
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (dodgeCheck(p, sender)) {
        p.getInventory().addItem(toGive);
        sendActionedMessage(p);
      }
    }

    cmd.placeholder("item", type.name().toLowerCase());
    cmd.placeholder("amount", amount + "");
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
