package com.lucatm11.IpLimiter.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.IpLimiter.IpLimiter;

public class IpLimiterCommand implements CommandExecutor {
    private final IpLimiter ipLimiter;

    public IpLimiterCommand(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("iplimiter.help")) {
            sender.sendMessage(ipLimiter.noPermission);
            return true;
        }

        sender.sendMessage(ipLimiter.help);

        return true;
    }
    
}
