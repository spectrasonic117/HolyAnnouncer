package com.spectrasonic.HolyAnnouncer.Commands;

import com.spectrasonic.HolyAnnouncer.Main;
import com.spectrasonic.HolyAnnouncer.Utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnouncerCommand implements CommandExecutor {
    private final Main plugin;

    public AnnouncerCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return false;

        switch (args[0].toLowerCase()) {
            case "reload":
                if (!sender.hasPermission("holyannouncer.reload")) {
                    MessageUtils.sendPermissionMesssage(sender);
                    return true;
                }
                plugin.getConfigManager().loadConfig();
                plugin.getAnnouncementTask().startTask();
                MessageUtils.sendMessage(sender, "Configuration reloaded successfully!");
                return true;

            case "now":
                if (!sender.hasPermission("holyannouncer.now")) {
                    MessageUtils.sendPermissionMesssage(sender);
                    return true;
                }
                plugin.getMessageManager().broadcastAnnouncement();
                MessageUtils.sendMessage(sender, "Announcement sent successfully!");
                return true;

            case "version":
                MessageUtils.sendMessage(sender, "HolyAnnouncer &dversion: &a" + plugin.getDescription().getVersion());
                MessageUtils.sendMessage(sender, "Developed by: &c" + plugin.getDescription().getAuthors());
                return true;

            default:
                return false;
        }
    }
}