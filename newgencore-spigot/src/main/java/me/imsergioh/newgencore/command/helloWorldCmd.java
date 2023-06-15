package me.imsergioh.newgencore.command;

import me.imsergioh.newgencore.instance.command.CommandInfo;
import me.imsergioh.newgencore.instance.command.PluginCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "helloworld", permission = "newgencore.admin")
public class helloWorldCmd extends PluginCommand {

    @Override
    public void executeConsole(CommandSender consoleSender, String label, String[] args) {
        consoleSender.sendMessage("Hello world console!");
    }

    @Override
    public void executePermissionPlayer(Player player, String label, String[] args) {
        player.sendMessage("Hello world admin!");
    }

    @Override
    public void executePlayer(Player player, String label, String[] args) {
        player.sendMessage("Hello world player!");
    }
}

