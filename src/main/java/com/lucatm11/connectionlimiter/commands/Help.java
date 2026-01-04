package com.lucatm11.connectionlimiter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class Help implements CommandExecutor {
    private final ConnectionLimiter plugin;

    public Help(ConnectionLimiter plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("connectionlimiter.help")) {
            sender.sendMessage(plugin.messages.noPermission);
            return true;
        }

        sender.sendMessage(plugin.messages.help);

        return true;
    }

}
