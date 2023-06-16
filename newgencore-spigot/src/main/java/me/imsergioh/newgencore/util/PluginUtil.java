package me.imsergioh.newgencore.util;

import me.imsergioh.newgencore.NewGenCore;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtil {

    public static void registerListeners(JavaPlugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTaskLater(NewGenCore.getPlugin(), runnable, 0);
    }

}
