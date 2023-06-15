package me.imsergioh.newgencore;

import lombok.Getter;
import lombok.Setter;
import me.imsergioh.newgencore.backend.MongoDBConnection;
import me.imsergioh.newgencore.backend.RedisConnection;
import me.imsergioh.newgencore.backend.MySQLConnection;
import me.imsergioh.newgencore.command.helloWorldCmd;
import me.imsergioh.newgencore.holder.ConfigHolder;
import me.imsergioh.newgencore.manager.PluginCommandManager;
import me.imsergioh.newgencore.util.FilesUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class NewGenCore extends JavaPlugin {

    private static NewGenCore plugin;

    @Setter
    @Getter
    private static RedisConnection redisConnection;
    @Setter
    @Getter
    private static MongoDBConnection mongoDBConnection;
    @Setter
    @Getter
    private static MySQLConnection mySQLConnection;

    @Override
    public void onEnable() {
        plugin = this;
        FilesUtil.createDirectories(getDataFolder());
        ConfigHolder.registerConfigs();
        PluginCommandManager.registerDefaults();
        registerCommands();
    }

    private static void registerCommands() {
        PluginCommandManager.registerCommands(
                new helloWorldCmd()
        );
    }

    public static NewGenCore getPlugin() {
        return plugin;
    }
}
