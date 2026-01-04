package com.lucatm11.connectionlimiter.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class Join implements Listener {
    private final ConnectionLimiter plugin;

    public Join(ConnectionLimiter plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        String ip = event.getAddress().getHostName();

        plugin.connection.initializeConnection(ip);

        if (plugin.connection.getConnections(ip) >= plugin.config.maxConnectionsAllowedPerIp) {
            event.disallow(Result.KICK_OTHER, plugin.messages.tooManyConnections);
        } else {
            plugin.connection.addConnection(ip);
        }
    }
}
