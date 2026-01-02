package com.lucatm11.IpLimiter;

import java.util.HashMap;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucatm11.IpLimiter.Commands.CommandRouter;
import com.lucatm11.IpLimiter.Listeners.Join;
import com.lucatm11.IpLimiter.Listeners.Leave;
import com.lucatm11.IpLimiter.Listeners.ServerReload;
import com.lucatm11.IpLimiter.Utils.Configuration;

public class IpLimiter extends JavaPlugin {
  private final ServerReload serverReload;

  public Configuration.Messages messages;
  public Configuration.Config config;

  public HashMap<String, Integer> playersByIP;

  public IpLimiter() {
    this.serverReload = new ServerReload(this);
  }

  public void onEnable() {
    saveDefaultConfig();
    loadConfiguration();

    Metrics metrics = new Metrics(this, 28657);

    if(config.kickAllPlayersReload) {
      if(!Bukkit.getOnlinePlayers().isEmpty()) {
        serverReload.onReload();
      }
    }

    playersByIP = new HashMap<String, Integer>();

    getServer().getPluginManager().registerEvents(new Join(this), this);
    getServer().getPluginManager().registerEvents(new Leave(this), this);
    getCommand("iplimiter").setExecutor(new CommandRouter(this));
  }

  public void loadConfiguration() {
    Configuration configuration = new Configuration();
    configuration.new Config(getConfig());
    configuration.new Messages(getConfig());
  }
}
