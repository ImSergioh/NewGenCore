package me.imsergioh.newgencore;

import org.bukkit.entity.Player;

public interface IPluginScoreboard {

    String getName();
    void registerPlayer(Player player);
    void unregisterPlayer(Player player);
}
