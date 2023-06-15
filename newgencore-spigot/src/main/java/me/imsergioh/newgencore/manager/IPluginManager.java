package me.imsergioh.newgencore.manager;

import me.imsergioh.newgencore.instance.command.PluginCommand;
import org.bukkit.event.Listener;

public interface IPluginManager {

    void registerListeners(Listener... listeners);
    void registerCommands(PluginCommand... commands);



}
