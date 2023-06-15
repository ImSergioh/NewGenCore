package me.imsergioh.newgencore;

import lombok.Getter;
import lombok.Setter;
import me.imsergioh.newgencore.backend.MongoDBConnection;
import me.imsergioh.newgencore.backend.RedisConnection;
import me.imsergioh.newgencore.backend.SQLConnection;
import me.imsergioh.newgencore.command.helloWorldCmd;
import me.imsergioh.newgencore.holder.ConfigHolder;
import me.imsergioh.newgencore.manager.PluginCommandManager;
import me.imsergioh.newgencore.util.FilesUtil;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

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
    private static SQLConnection sqlConnection;

    @Override
    public void onEnable() {
        plugin = this;
        FilesUtil.createDirectories(getDataFolder());
        ConfigHolder.registerConfigs();
        PluginCommandManager.registerDefaults();
        registerCommands();

        if (sqlConnection != null) {
            try {
                Statement statement = sqlConnection.getConnection().createStatement();
                if (statement.executeUpdate("CREATE DATABASE IF NOT EXISTS NewGenCore") == 1) {
                    System.out.println("DEBUG SETUP NEWGENCORE DATABASE HAS BEEN CREATED!");
                } else {
                    System.out.println("DEBUG NEWGENCORE DATABASE IS ALREADY CREATED!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
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
