package com.spectrasonic.HolyAnnouncer.Messages;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.spectrasonic.HolyAnnouncer.Main;

import java.util.List;

public class MessageManager {
    private final Main plugin;
    private final MiniMessage miniMessage;
    private final BukkitAudiences adventure;

    public MessageManager(Main plugin) {
        this.plugin = plugin;
        this.miniMessage = MiniMessage.miniMessage();
        this.adventure = BukkitAudiences.create(plugin);
    }

    public void broadcastAnnouncement() {
        List<String> messages = plugin.getConfigManager().getAnnounceMessages();
        if (messages == null || messages.isEmpty()) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            for (String line : messages) {
                Component message = miniMessage.deserialize(line);
                adventure.player(player).sendMessage(message);
            }
        }
    }

    public void sendMessage(Player player, String message) {
        Component parsedMessage = miniMessage.deserialize(message);
        adventure.player(player).sendMessage(parsedMessage);
    }

    public void close() {
        if (adventure != null) {
            adventure.close();
        }
    }
}