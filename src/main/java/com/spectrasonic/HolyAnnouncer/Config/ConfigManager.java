package com.spectrasonic.HolyAnnouncer.Config;

import org.bukkit.configuration.file.FileConfiguration;
import com.spectrasonic.HolyAnnouncer.Main;

import java.util.List;

public class ConfigManager {
    private final Main plugin;
    private FileConfiguration config;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public int getAnnounceTimeMinutes() {
        return config.getInt("announce_time_minutes", 15);
    }

    public List<String> getAnnounceMessages() {
        return config.getStringList("announce_message");
    }
}