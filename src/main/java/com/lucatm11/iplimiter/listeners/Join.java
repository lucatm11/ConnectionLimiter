package com.lucatm11.iplimiter.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import com.lucatm11.iplimiter.IpLimiter;

public class Join implements Listener {
    private final IpLimiter ipLimiter;

    public Join(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    @EventHandler
    public void JoinEvent(AsyncPlayerPreLoginEvent event) {
        String ip = event.getAddress().getHostName();

        ipLimiter.connection.initializeConnection(ip);

        if (ipLimiter.connection.getConnections(ip) >= ipLimiter.config.maxConnectionsAllowedPerIp) {
            event.disallow(Result.KICK_OTHER, ipLimiter.messages.tooManyConnections);
        } else {
            ipLimiter.connection.addIPConnection(ip);
        }
    }
}
