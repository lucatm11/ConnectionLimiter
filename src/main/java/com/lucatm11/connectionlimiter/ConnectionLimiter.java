package com.lucatm11.connectionlimiter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucatm11.connectionlimiter.bstats.Metrics;
import com.lucatm11.connectionlimiter.commands.CommandRouter;
import com.lucatm11.connectionlimiter.listeners.Join;
import com.lucatm11.connectionlimiter.listeners.Leave;
import com.lucatm11.connectionlimiter.utils.Configuration;
import com.lucatm11.connectionlimiter.utils.Connection;

public class ConnectionLimiter extends JavaPlugin {
  public Connection connection;
  public Configuration.Messages messages;
  public Configuration.Config config;

  public void onEnable() {
    saveDefaultConfig();
    loadConfiguration();

    @SuppressWarnings("unused")
    Metrics metrics = new Metrics(this, 28657);

    if (config.kickAllPlayersOnServerReload) {
      if (!Bukkit.getOnlinePlayers().isEmpty()) {
        for (Player player : Bukkit.getOnlinePlayers()) {
          player.kickPlayer(messages.serverReloadKick);
        }
      }
    }

    connection = new Connection();
    connection.initializeMap();

    getServer().getPluginManager().registerEvents(new Join(this), this);
    getServer().getPluginManager().registerEvents(new Leave(this), this);
    getCommand("connectionlimiter").setExecutor(new CommandRouter(this));
  }

  public void loadConfiguration() {
    Configuration configuration = new Configuration();
    config = configuration.new Config(getConfig());
    messages = configuration.new Messages(getConfig());
  }
}
