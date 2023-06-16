package me.imsergioh.newgencore.command;

import me.imsergioh.newgencore.instance.command.CommandInfo;
import me.imsergioh.newgencore.instance.command.PluginCommand;
import me.imsergioh.newgencore.manager.LobbyManager;
import me.imsergioh.newgencore.util.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "spawn")
public class spawnCMD extends PluginCommand {

    private final LobbyManager lobbyManager;

    public spawnCMD(LobbyManager lobbyManager) {
        this.lobbyManager = lobbyManager;
    }

    @Override
    public void executePlayer(Player player, String label, String[] args) {
        if (!lobbyManager.isSpawnSet()) {
            player.sendMessage(ChatUtil.color("&cNo spawn set"));
            return;
        }
        lobbyManager.teleport(player);
    }
}

