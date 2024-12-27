package com.spectrasonic.HolyAnnouncer.Tasks;

import org.bukkit.scheduler.BukkitTask;
import com.spectrasonic.HolyAnnouncer.Main;

public class AnnouncementTask {
    private final Main plugin;
    private BukkitTask task;

    public AnnouncementTask(Main plugin) {
        this.plugin = plugin;
    }

    public void startTask() {
        stopTask();
        int minutes = plugin.getConfigManager().getAnnounceTimeMinutes();
        long ticks = minutes * 1200L; // Convert minutes to ticks

        task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(
                plugin,
                () -> plugin.getMessageManager().broadcastAnnouncement(),
                ticks,
                ticks
        );
    }

    public void stopTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}