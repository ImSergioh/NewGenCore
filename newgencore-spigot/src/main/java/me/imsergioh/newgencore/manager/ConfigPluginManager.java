package me.imsergioh.newgencore.manager;

import lombok.Getter;
import me.imsergioh.newgencore.instance.command.PluginCommand;
import me.imsergioh.newgencore.instance.config.PluginLocalConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigPluginManager extends PluginManager {

    @Getter
    protected final PluginLocalConfig config;

    public ConfigPluginManager(JavaPlugin plugin, PluginLocalConfig config) {
        super(plugin);
        this.config = config;
        registerConfigDefaults();
        config.save();
    }

    public boolean isEnabled(String path) {
        return config.isBoolean(path);
    }

    public void registerConfigDefaults(){}

}
