package com.lucatm11.connectionlimiter.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class CheckConnections implements CommandExecutor {
    private final ConnectionLimiter plugin;

    public CheckConnections(ConnectionLimiter plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("connectionlimiter.checkconnections")) {
            sender.sendMessage(plugin.messages.noPermission);
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            sender.sendMessage(plugin.messages.offlinePlayer);
            return true;
        }

        String ip = target.getAddress().getHostName();
        Integer connections = plugin.connection.getConnections(ip);

        sender.sendMessage(plugin.messages.checkConnections.replace("{player}", target.getName())
                .replace("{ip}", ip).replace("{connections}", Integer.toString(connections)));

        return true;
    }
}
