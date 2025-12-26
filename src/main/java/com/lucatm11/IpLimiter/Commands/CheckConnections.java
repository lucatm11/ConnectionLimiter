package com.lucatm11.IpLimiter.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lucatm11.IpLimiter.IpLimiter;

public class CheckConnections implements CommandExecutor{
    private final IpLimiter ipLimiter;

    public CheckConnections(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("iplimiter.checkconnections")) {
            sender.sendMessage(ipLimiter.noPermission);
            return true;
        }

        if(args.length == 1) {
            sender.sendMessage(ipLimiter.help);
            return true;
        }

        if(args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(ipLimiter.offlinePlayer);
                return true;
            }

            String ip = target.getAddress().getHostName();
            Integer connections = ipLimiter.playersByIP.get(ip);

            sender.sendMessage(ipLimiter.checkConnections.replace("{player}", target.getName()).replace("{ip}", ip).replace("{connections}", Integer.toString(connections)));
            return true;
        }

        return true;
    }
}
