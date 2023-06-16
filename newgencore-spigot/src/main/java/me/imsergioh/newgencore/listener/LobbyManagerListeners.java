package me.imsergioh.newgencore.listener;

import me.imsergioh.newgencore.manager.LobbyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyManagerListeners implements Listener {

    private final LobbyManager lobbyManager;

    public LobbyManagerListeners(LobbyManager lobbyManager) {
        this.lobbyManager = lobbyManager;
    }

    @EventHandler
    public void teleportAtJoin(PlayerJoinEvent event) {
         if (!lobbyManager.isEnabled(LobbyManager.CONFIG_TELEPORT_AT_JOIN_PATH)) return;
         if (!lobbyManager.isSpawnSet()) return;
        Player player = event.getPlayer();
        player.teleport(lobbyManager.getSpawn());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelDamage(EntityDamageEvent event) {
        if (!lobbyManager.isEnabled(LobbyManager.CONFIG_CANCEL_DAMAGE_PATH)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void healFood(FoodLevelChangeEvent event) {
        if (!lobbyManager.isEnabled(LobbyManager.CONFIG_INFINITE_FOOD_PATH)) return;
        event.setFoodLevel(20);
    }
}
