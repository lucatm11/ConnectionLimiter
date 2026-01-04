package com.lucatm11.connectionlimiter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class Reload implements CommandExecutor {
    private final ConnectionLimiter plugin;

    public Reload(ConnectionLimiter plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("connectionlimiter.reload")) {
            sender.sendMessage(plugin.messages.noPermission);
            return true;
        }

        plugin.reloadConfig();
        plugin.loadConfiguration();

        sender.sendMessage(plugin.messages.pluginReloaded);

        return true;
    }
}
