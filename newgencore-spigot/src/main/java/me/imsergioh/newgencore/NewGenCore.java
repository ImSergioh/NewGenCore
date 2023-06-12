package me.imsergioh.newgencore;

import me.imsergioh.newgencore.holder.ConfigHolder;
import me.imsergioh.newgencore.util.FilesUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class NewGenCore extends JavaPlugin {

    private static NewGenCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
        FilesUtil.createDirectories(getDataFolder());
        ConfigHolder.registerConfigs();
    }

    public static NewGenCore getPlugin() {
        return plugin;
    }
}
