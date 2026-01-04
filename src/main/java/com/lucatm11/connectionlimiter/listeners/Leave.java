package com.lucatm11.connectionlimiter.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class Leave implements Listener {
    private final ConnectionLimiter plugin;

    public Leave(ConnectionLimiter plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        String ip = event.getPlayer().getAddress().getHostName();

        plugin.connection.removeConnection(ip);
    }
}
