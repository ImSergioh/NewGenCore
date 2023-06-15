package me.imsergioh.newgencore.manager;

import me.imsergioh.newgencore.instance.command.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginManager implements IPluginManager {

    private final JavaPlugin plugin;

    public PluginManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    @Override
    public void registerCommands(PluginCommand... commands) {
        PluginCommandManager.registerCommands(commands);
    }
}
