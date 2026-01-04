package com.lucatm11.connectionlimiter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class CommandRouter implements CommandExecutor {
    private final Help help;
    private final CheckConnections checkConnections;
    private final Reload reload;

    public CommandRouter(ConnectionLimiter plugin) {
        this.help = new Help(plugin);
        this.checkConnections = new CheckConnections(plugin);
        this.reload = new Reload(plugin);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args.length > 2) {
            return help.onCommand(sender, command, label, args);
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("checkconnections")) {
            return help.onCommand(sender, command, label, args);
        }

        if (args.length > 1 && args[0].equalsIgnoreCase("reload")) {
            return help.onCommand(sender, command, label, args);
        }

        switch (args[0].toLowerCase()) {
            case "checkconnections":
                return checkConnections.onCommand(sender, command, label, args);
            case "reload":
                return reload.onCommand(sender, command, label, args);
            default:
                return help.onCommand(sender, command, label, args);
        }
    }
}
