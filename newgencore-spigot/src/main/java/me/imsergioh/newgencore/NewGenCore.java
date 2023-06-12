package me.imsergioh.newgencore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NewGenCore extends JavaPlugin {

    private static NewGenCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public static NewGenCore getPlugin() {
        return plugin;
    }
}
