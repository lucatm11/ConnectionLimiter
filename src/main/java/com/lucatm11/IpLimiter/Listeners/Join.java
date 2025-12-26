package com.lucatm11.IpLimiter.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import com.lucatm11.IpLimiter.IpLimiter;

public class Join implements Listener {
    private final IpLimiter ipLimiter;

    public Join(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    @EventHandler
    public void JoinEvent(AsyncPlayerPreLoginEvent event) {
        String ip = event.getAddress().getHostName();

        if(ipLimiter.playersByIP.get(ip) == null) {
            ipLimiter.playersByIP.put(ip, 0);
        }

        Integer connections = ipLimiter.playersByIP.get(ip);
        ipLimiter.playersByIP.put(ip, connections + 1);

        if(connections > ipLimiter.maxIpsAllowed) {
            event.disallow(Result.KICK_OTHER, ipLimiter.tooManyConnections);
        }
    }
}
