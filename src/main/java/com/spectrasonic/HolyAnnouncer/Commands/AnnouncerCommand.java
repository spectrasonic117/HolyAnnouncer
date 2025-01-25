package com.spectrasonic.HolyAnnouncer.Commands;

import com.spectrasonic.HolyAnnouncer.Main;
import com.spectrasonic.HolyAnnouncer.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AnnouncerCommand implements CommandExecutor, TabCompleter {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("holyannouncer.reload")) {
                completions.add("reload");
            }
            if (sender.hasPermission("holyannouncer.now")) {
                completions.add("now");
            }
            completions.add("version");
        }

        return completions;
    }
}
