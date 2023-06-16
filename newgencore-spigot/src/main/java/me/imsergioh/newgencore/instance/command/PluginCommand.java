package me.imsergioh.newgencore.instance.command;

import lombok.Getter;
import me.imsergioh.newgencore.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Annotation;
import java.util.Objects;

public class PluginCommand implements CommandExecutor {

    @Getter
    private String name;
    @Getter
    private CommandInfo commandInfo;

    public PluginCommand() {
        for (Annotation annotation : getClass().getAnnotations()) {
            if (annotation instanceof CommandInfo) {
                commandInfo = (CommandInfo) annotation;
                break;
            }
        }
        if (commandInfo == null) {
            System.out.println("Error at register/create instance of command class with name of:" +
                    getClass().getName() + " (must have CommandInfo annotation)");
            return;
        }
        name = commandInfo.name();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean withPermission = !Objects.equals(commandInfo.permission(), "");

        if (sender instanceof Player player) {
            if (commandInfo.onlyForConsole()) {
                sender.sendMessage(onlyForConsoleMessage());
                return true;
            }

            if (withPermission && player.hasPermission(commandInfo.permission())) {
                executePermissionPlayer(player, label, args);
                return true;
            }
            executePlayer(player, label, args);
            return true;
        }
        executeConsole(sender, label, args);

        return false;
    }

    public void executeConsole(CommandSender consoleSender, String label, String[] args) {}

    public void executePlayer(Player player, String label, String[] args) {}

    public void executePermissionPlayer(Player player, String label, String[] args) {}

    public void sendNoPermissionMessage(CommandSender sender) {
        sender.sendMessage(onlyForConsoleMessage());
    }

    public String onlyForConsoleMessage() {
        return ChatUtil.color("&cThis command is only for the console");
    }

}
