package net.twilightdevelopment.plugin.extracommands;

import com.google.common.collect.ImmutableSet;
import me.caelunshun.shun.UpdateChecker;
import net.twilightdevelopment.plugin.extracommands.commands.*;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.regex.Pattern;

public class ExtraCommands extends JavaPlugin {

  public static ExtraCommands instance;
  private static final long UPDATER_INTERVAL = 20 * 60 * 60;
  private static final Set<String> cmdNames;

  static {
    cmdNames =
        ImmutableSet.of(
            "kickall",
            "tpall",
            "setspawn",
            "ip",
            "hideplayers",
            "showplayers",
            "killall",
            "day",
            "night",
            "clearall",
            "giveall");
  }

  @Override
  public void onEnable() {
    instance = this;
    MetricsLite metrics = new MetricsLite(this);
    saveDefaultConfig();

    upgradeConfig();

    if (getConfig().getBoolean("update-checker"))
      new UpdateChecker(this, "35102");

    getCommand("kickall").setExecutor(new KickAll(this));
    getCommand("tpall").setExecutor(new TPAll(this));
    getCommand("setspawn").setExecutor(new SetSpawn(this));
    getCommand("ip").setExecutor(new IP(this));
    getCommand("hideplayers").setExecutor(new HidePlayers(this));
    getCommand("showplayers").setExecutor(new ShowPlayers(this));
    getCommand("killall").setExecutor(new KillAll(this));
    getCommand("day").setExecutor(new Day(this));
    getCommand("night").setExecutor(new Night(this));
    getCommand("clearall").setExecutor(new ClearAll(this));
    getCommand("givall").setExecutor(new GiveAll(this));

    PluginCommand baseCmd = getCommand("extracommands");
    ExtraCommandsCommand baseCmdImpl = new ExtraCommandsCommand(this);
    baseCmd.setExecutor(baseCmdImpl);
    baseCmd.setTabCompleter(baseCmdImpl);
  }

  @Override
  public void onDisable() {
    saveDefaultConfig();
  }

  public static ExtraCommands getInstance() {
    return instance;
  }

  private void upgradeConfig() {
    // Upgrade configuration to add options in newer versions to older configs
    if (!getConfig().contains("affect-command-issuer", true)) {
      for (String cmdName : cmdNames) {
        if (!cmdName.matches(".*all")) continue;
        String path = "affect-command-issuer." + cmdName;
        getConfig().createSection(path);
        getConfig().set(path, true);
      }
    }

    Pattern allPattern = Pattern.compile("[a-zA-z]*all");
    for (String cmd : cmdNames) {
      if (allPattern.matcher(cmd).matches() && !getConfig().contains("messages." + cmd, true)) {
        getConfig().createSection("messages." + cmd);
        getConfig().set("messages." + cmd, getConfig().get("messages." + cmd + "-message"));
      }
    }
    if (!getConfig().contains("messages.default-kickall", true)) {
      getConfig().set("messages.default-kickall", getConfig().getDefaults().get("messages.default-kickall"));
    }
    for (String cmd : cmdNames) {
      if (!getConfig().contains("messages." + cmd + "-complete", true)) {
        getConfig().createSection("messages." + cmd + "-complete");
        getConfig().set("messages." + cmd + "-complete", getConfig().getDefaults().get("messages." + cmd + "-complete"));
      }
    }

    for (String cmd : cmdNames) {
      String usage = "messages." + cmd + "-usage";
      if (!getConfig().contains(usage, true)) {
        getConfig().createSection(usage);
        getConfig().set(usage, getConfig().getDefaults().get(usage));
      }
    }

    if (!getConfig().contains("messages.command-disabled", true)) {
      getConfig().createSection("messages.command-disabled");
      getConfig().set("messages.command-disabled", getConfig().get("messages.command-disabled-message"));
    }
    configUpgradeDefault("messages.no-permission");
    configUpgradeDefault("messages.must-be-a-player");
    configUpgradeDefault("messages.player-not-found");
    configUpgradeDefault("messages.world-not-found");
    configUpgradeDefault("messages.item-type-not-found");
    configUpgradeDefault("messages.plugin-reloaded");

    saveConfig();
  }

  private void configUpgradeDefault(String path) {
    if (!getConfig().contains(path, true)) {
      getConfig().createSection(path);
      getConfig().set(path, getConfig().getDefaults().get(path));
    }
  }
}
