package me.imsergioh.newgencore.command;

import me.imsergioh.newgencore.builder.MessageBuilder;
import me.imsergioh.newgencore.instance.command.CommandInfo;
import me.imsergioh.newgencore.instance.command.PluginCommand;
import me.imsergioh.newgencore.manager.LobbyManager;
import me.imsergioh.newgencore.util.ChatUtil;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "setspawn", permission = "newgencore.admin")
public class setSpawnCMD extends PluginCommand {

    private final LobbyManager lobbyManager;

    public setSpawnCMD(LobbyManager lobbyManager) {
        this.lobbyManager = lobbyManager;
    }

    @Override
    public void executeConsole(CommandSender consoleSender, String label, String[] args) {
        consoleSender.sendMessage("You need to be a player administrator in-game to set the spawn. Nice try");
    }

    @Override
    public void executePermissionPlayer(Player player, String label, String[] args) {
        lobbyManager.setSpawn(player.getLocation());
        ChatUtil.send(player,
                new MessageBuilder(
                        "&aSpawn set!")
                        .color("CCCCFF"));
    }

    @Override
    public void executePlayer(Player player, String label, String[] args) {
        sendNoPermissionMessage(player);
    }

    @Override
    public String onlyForConsoleMessage() {
        return ChatUtil.color("&cYou are not permitted to set the spawn!");
    }
}

