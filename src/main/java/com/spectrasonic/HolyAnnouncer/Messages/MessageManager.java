package com.spectrasonic.HolyAnnouncer.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.spectrasonic.HolyAnnouncer.Main;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public class MessageManager {
    private final Main plugin;

    public MessageManager(Main plugin) {
        this.plugin = plugin;
    }

    public void broadcastAnnouncement() {
        List<String> messages = plugin.getConfigManager().getAnnounceMessages();
        if (messages == null || messages.isEmpty()) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            for (String line : messages) {
                String coloredMessage = ChatColor.translateAlternateColorCodes('&', line);
                TextComponent message = new TextComponent(coloredMessage);
                player.spigot().sendMessage(message);
            }
        }
    }

    public void sendMessage(Player player, String message) {
        String coloredMessage = ChatColor.translateAlternateColorCodes('&', message);
        TextComponent textComponent = new TextComponent(coloredMessage);
        player.spigot().sendMessage(textComponent);
    }
}