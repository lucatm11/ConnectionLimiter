package com.lucatm11.IpLimiter.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.IpLimiter.IpLimiter;

public class CommandRouter implements CommandExecutor {
    private final IpLimiter ipLimiter;
    private final IpLimiterCommand ipLimiterCommand;
    private final CheckConnections checkConnections;
    private final Reload reload;

    public CommandRouter(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
        this.ipLimiterCommand = new IpLimiterCommand(ipLimiter);
        this.checkConnections = new CheckConnections(ipLimiter);
        this.reload = new Reload(ipLimiter);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 || args.length > 2) {
            return ipLimiterCommand.onCommand(sender, command, label, args);
        } else if(args.length >= 1 && args[0].equalsIgnoreCase("checkconnections")) {
            return checkConnections.onCommand(sender, command, label, args);
        } else if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            return reload.onCommand(sender, command, label, args);
        }
        return true;
    }
}
