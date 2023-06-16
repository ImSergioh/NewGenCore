package me.imsergioh.newgencore.listener;

import me.imsergioh.newgencore.instance.PluginScoreboard;
import me.imsergioh.newgencore.manager.ScoreboardsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardsManagerListeners implements Listener {

    private final ScoreboardsManager scoreboardsManager;

    public ScoreboardsManagerListeners(ScoreboardsManager scoreboardsManager) {
        this.scoreboardsManager = scoreboardsManager;
    }

    @EventHandler
    public void setAtJoin(PlayerJoinEvent event) {
         if (!scoreboardsManager.isEnabled(ScoreboardsManager.CONFIG_ON_JOIN_SET_DEFAULT_SCOREBOARD_PATH)) return;
        PluginScoreboard scoreboard = scoreboardsManager.getDefaultScoreboard();
        scoreboard.registerPlayer(event.getPlayer());
    }
}
