package me.imsergioh.newgencore.manager;

import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.instance.command.PluginCommand;
import me.imsergioh.newgencore.listener.PluginCommandManagerListeners;
import me.imsergioh.newgencore.util.PluginUtil;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class PluginCommandManager {

    private static final NewGenCore plugin = NewGenCore.getPlugin();
    private static final HashMap<String, PluginCommand> commands = new HashMap<>();

    public static void registerDefaults() {
        PluginUtil.registerListeners(plugin, new PluginCommandManagerListeners());
    }

    public static void registerCommands(PluginCommand... cmds) {
        for (PluginCommand command : cmds) {
            commands.put(command.getCommandInfo().name(), command);
        }
    }

    public static void executeCommand(CommandSender sender, String label) {
        String name = getNameOfLabel(label).toLowerCase();
        if (!commands.containsKey(name)) return;
        commands.get(name).onCommand(sender, null, label, getArgsOfLabel(label));
    }

    public static boolean cancelPreProcessCommand(String label) {
        return commands.containsKey(getNameOfLabel(label));
    }

    private static String[] getArgsOfLabel(String label) {
        String name = getNameOfLabel(label).toLowerCase();
        return label.replace(name + " ", "").split(" ");
    }

    private static String getNameOfLabel(String label) {
        return label.split(" ")[0].toLowerCase();
    }
}
