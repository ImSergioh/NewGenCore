package me.imsergioh.newgencore.listener;

import me.imsergioh.newgencore.manager.PluginCommandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class PluginCommandManagerListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void tabComplete(TabCompleteEvent event) {
        System.out.println("DEBUG TABCOMPLETEEVENT:" + event.getCompletions() + " from " + event.getSender().getName());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void tabCompletePlayer(PlayerChatTabCompleteEvent event) {
        System.out.println("DEBUG PLAYERTAB:" + event.getTabCompletions() + " from " + event.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerCommand(PlayerCommandPreprocessEvent event) {
        String label = event.getMessage().replaceFirst("/", "");
        PluginCommandManager.executeCommand(event.getPlayer(), label);
        event.setCancelled(PluginCommandManager.cancelPreProcessCommand(label));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void serverCommand(ServerCommandEvent event) {
        String label = event.getCommand().replaceFirst("/", "");
        PluginCommandManager.executeCommand(event.getSender(), label);
        event.setCancelled(PluginCommandManager.cancelPreProcessCommand(label));
    }
}
