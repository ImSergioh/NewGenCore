package me.imsergioh.newgencore;

import lombok.Getter;
import lombok.Setter;
import me.imsergioh.newgencore.backend.MongoDBConnection;
import me.imsergioh.newgencore.backend.RedisConnection;
import me.imsergioh.newgencore.backend.MySQLConnection;
import me.imsergioh.newgencore.command.helloWorldCMD;
import me.imsergioh.newgencore.holder.ConfigHolder;
import me.imsergioh.newgencore.manager.LobbyManager;
import me.imsergioh.newgencore.manager.PluginCommandManager;
import me.imsergioh.newgencore.util.ExceptionsUtil;
import me.imsergioh.newgencore.util.FilesUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class NewGenCore extends JavaPlugin {

    private static NewGenCore plugin;

    // BACKEND CONNECTIONS:
    @Setter
    @Getter
    private static RedisConnection redisConnection;
    @Setter
    @Getter
    private static MongoDBConnection mongoDBConnection;
    @Setter
    @Getter
    private static MySQLConnection mySQLConnection;

    // MANAGERS:
    @Getter
    private static LobbyManager lobbyManager;

    @Override
    public void onEnable() {
        plugin = this;
        FilesUtil.createDirectories(getDataFolder());
        ConfigHolder.registerConfigs();
        PluginCommandManager.registerDefaults();
        registerConfigManagers();
        registerCommands();
    }

    private void registerConfigManagers() {
        try {
            lobbyManager = (LobbyManager) ConfigHolder.createPluginConfigManagerOfConfig(plugin, "managers.lobby.enabled", LobbyManager.class);
        } catch (Exception e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

    private static void registerCommands() {
        PluginCommandManager.registerCommands(
                new helloWorldCMD()
        );
    }

    public static NewGenCore getPlugin() {
        return plugin;
    }
}
