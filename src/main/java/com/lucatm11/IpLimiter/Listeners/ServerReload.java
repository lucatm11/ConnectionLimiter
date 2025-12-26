package com.lucatm11.IpLimiter.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lucatm11.IpLimiter.IpLimiter;

public class ServerReload {
    private final IpLimiter ipLimiter;

    public ServerReload(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    public void onReload() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ipLimiter.serverReloadKick);
        }
    }
}
