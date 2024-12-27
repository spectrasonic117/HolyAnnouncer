package com.spectrasonic.HolyAnnouncer;

import com.spectrasonic.HolyAnnouncer.Commands.AnnouncerCommand;
import com.spectrasonic.HolyAnnouncer.Config.ConfigManager;
import com.spectrasonic.HolyAnnouncer.Messages.MessageManager;
import com.spectrasonic.HolyAnnouncer.Tasks.AnnouncementTask;
import com.spectrasonic.HolyAnnouncer.Utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private ConfigManager configManager;
    private MessageManager messageManager;
    private AnnouncementTask announcementTask;

    @Override
    public void onEnable() {
        // Initialize managers
        this.configManager = new ConfigManager(this);
        this.messageManager = new MessageManager(this);
        this.announcementTask = new AnnouncementTask(this);

        // Start announcement task
        this.announcementTask.startTask();

        registerCommands();
        MessageUtils.sendStartupMessage(this);
    }

    public void registerCommands() {
        getCommand("holyannouncer").setExecutor(new AnnouncerCommand(this));
    }

    @Override
    public void onDisable() {
        if (messageManager != null) {
            messageManager.close();
        }

        if (announcementTask != null) {
            announcementTask.stopTask();
        }
        MessageUtils.sendShutdownMessage(this);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public AnnouncementTask getAnnouncementTask() {
        return announcementTask;
    }
}