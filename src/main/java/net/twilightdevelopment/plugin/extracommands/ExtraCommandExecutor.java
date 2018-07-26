package net.twilightdevelopment.plugin.extracommands;

import com.google.common.collect.ImmutableMap;
import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class ExtraCommandExecutor implements CommandExecutor {
  protected final JavaPlugin plugin;
  protected final String cmdName;

  public ExtraCommandExecutor(JavaPlugin plugin, String cmdName) {
    Validate.notNull(plugin);
    Validate.notNull(cmdName);
    this.plugin = plugin;
    this.cmdName = cmdName;
    plugin
        .getCommand(cmdName)
        .setTabCompleter((sender, cmd, label, args) -> parseTabComplete(sender, args));
  }

  public ExtraCommandExecutor(JavaPlugin plugin) {
    Validate.notNull(plugin);
    this.plugin = plugin;
    cmdName = null;
  }

  public ExtraCommandExecutor(JavaPlugin plugin, String cmdName, TabCompleter tabCompleter) {
    this(plugin, cmdName);
    plugin.getCommand(cmdName).setTabCompleter(tabCompleter);
  }

  private final boolean isEnabled() {
    return plugin.getConfig().getBoolean("commands." + cmdName);
  }

  /**
   * Checks that a command sender has the permission to execute the command represented by this
   * ExtraCommandExecutor. If the sender was a {@link ConsoleCommandSender}, the method will return
   * true as if it were a player with the permission.
   *
   * <p>A permission is checked using the format extracommands.commandName.
   *
   * @param sender The {@link CommandSender} whose permission will be checked.
   * @return True if the sender is a {@link ConsoleCommandSender} or it has the required permission
   *     associated with {@code cmdName}
   */
  private final boolean checkPerms(CommandSender sender) {
    return sender instanceof ConsoleCommandSender
        || sender.hasPermission("extracommands." + cmdName);
  }

  private final void sendDisabledMessage(CommandSender sender) {
    String msg =
        ChatColor.translateAlternateColorCodes(
            '&', plugin.getConfig().getString("messages.command-disabled-message"));
    if (!msg.isEmpty()) sender.sendMessage(msg);
  }

  private final void sendNoPermissionMessage(CommandSender sender) {
    String msg =
        ChatColor.translateAlternateColorCodes(
            '&', plugin.getConfig().getString("messages.no-permission"));
    if (!msg.isEmpty()) sender.sendMessage(msg);
  }

  private final boolean preconditionCheck(CommandSender sender) {
    Validate.notNull(sender);
    if (!isEnabled()) {
      sendDisabledMessage(sender);
      return false;
    }
    if (!checkPerms(sender)) {
      sendNoPermissionMessage(sender);
      return false;
    }

    return true;
  }

  protected List<String> parseTabComplete(final CommandSender sender, final String[] args) {
    return Collections.emptyList();
  }

  private void sendSuccess(CommandSender sender, Map<String, String> placeholders) {
    String msg =
        ChatColor.translateAlternateColorCodes(
            '&',
            PlaceholderUtil.applyPlaceholders(
                plugin.getConfig().getString("messages." + cmdName + "-complete"), placeholders));
    if (!msg.isEmpty()) sender.sendMessage(msg);
  }

  private void sendSuccess(CommandSender sender) {
    sendSuccess(sender, ImmutableMap.of());
  }

  protected final boolean dodgeCheck(Player player, CommandSender sender) {
    if (player.hasPermission("extracommands.dodge" + cmdName)) return false;
    return plugin.getConfig().getBoolean("affect-command-issuer.giveall") || !player.equals(sender);
  }

  /**
   * Sends an action message (e.g. "You have been teleported") to the specified player.
   *
   * @param player The player to send the message to
   */
  protected final void sendActionedMessage(Player player) {
    String msg =
        ChatColor.translateAlternateColorCodes(
            '&', plugin.getConfig().getString("messages." + cmdName));
    if (!msg.isEmpty()) player.sendMessage(msg);
  }

  protected final void sendUsage(CommandSender sender) {
    sender.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&', plugin.getConfig().getString("messages." + cmdName + "-usage")));
  }

  protected final boolean checkPlayer(CommandSender sender) {
    return sender instanceof Player;
  }

  protected final void sendPlayerRequired(CommandSender sender) {
    String msg =
        plugin
            .getConfig()
            .getString(ChatColor.translateAlternateColorCodes('&', "messages.must-be-player"));
    if (!msg.isEmpty()) sender.sendMessage(msg);
  }

  protected abstract boolean execute(ExtraCommand cmd, CommandSender sender, String[] args);

  @Override
  public final boolean onCommand(
      CommandSender sender, Command command, String label, String[] args) {
    if (!preconditionCheck(sender)) return true;

    ExtraCommand cmd = new ExtraCommand();

    if (execute(cmd, sender, args)) sendSuccess(sender, cmd.getPlaceholders());

    return true;
  }
}
