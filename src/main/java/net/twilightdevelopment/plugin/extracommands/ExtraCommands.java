package net.twilightdevelopment.plugin.extracommands;

import com.google.common.collect.ImmutableSet;
import net.twilightdevelopment.plugin.extracommands.autoupdater.ExtraCommandsUpdater;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class ExtraCommands extends JavaPlugin {

  public static ExtraCommands instance;
  private static final long UPDATER_INTERVAL = 20 * 60 * 60;
  private static final Set<String> cmdNames;

  static {
    cmdNames = ImmutableSet.of(
            "huball",
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
            "giveall"
    );
  }

  @Override
  public void onEnable() {
    instance = this;
    MetricsLite metrics = new MetricsLite(this);
    saveDefaultConfig();

    upgradeConfig();

    if (getConfig().getBoolean("update-checker"))
      Bukkit.getScheduler()
          .runTaskTimerAsynchronously(this, new ExtraCommandsUpdater(this), 0, UPDATER_INTERVAL);

    getCommand("huball").setExecutor(new HubAll(this));
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
    getCommand("giveall").setExecutor(new GiveAll(this));
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
        if (!cmdName.matches(".*all"))
          continue;
        String path = "affect-command-issuer." + cmdName;
        getConfig().createSection(path);
        getConfig().set(path, true);
      }
      saveConfig();
    }
  }
}
