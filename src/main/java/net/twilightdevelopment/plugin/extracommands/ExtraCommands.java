package net.twilightdevelopment.plugin.extracommands;

import net.twilightdevelopment.plugin.extracommands.autoupdater.ExtraCommandsUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtraCommands extends JavaPlugin {

  public static ExtraCommands instance;
  private static final long UPDATER_INTERVAL = 20 * 60 * 60;

  public void onEnable() {
    instance = this;
    saveDefaultConfig();

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

  public void onDisable() {
    saveDefaultConfig();
  }

  public static ExtraCommands getInstance() {
    return instance;
  }
}
