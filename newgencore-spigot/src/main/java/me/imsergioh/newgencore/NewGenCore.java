package me.imsergioh.newgencore;

import me.imsergioh.newgencore.listener.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NewGenCore extends JavaPlugin {

    private static NewGenCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    public static NewGenCore getPlugin() {
        return plugin;
    }
}
