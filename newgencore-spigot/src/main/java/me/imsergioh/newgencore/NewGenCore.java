package me.imsergioh.newgencore;

import lombok.Getter;
import lombok.Setter;
import me.imsergioh.newgencore.backend.MongoDBConnection;
import me.imsergioh.newgencore.backend.RedisConnection;
import me.imsergioh.newgencore.backend.MySQLConnection;
import me.imsergioh.newgencore.command.helloWorldCmd;
import me.imsergioh.newgencore.holder.ConfigHolder;
import me.imsergioh.newgencore.instance.data.LocalData;
import me.imsergioh.newgencore.instance.data.MySQLStorage;
import me.imsergioh.newgencore.manager.PluginCommandManager;
import me.imsergioh.newgencore.util.ExceptionsUtil;
import me.imsergioh.newgencore.util.FilesUtil;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
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
    private static MySQLConnection mySQLConnection;

    @Override
    public void onEnable() {
        plugin = this;
        FilesUtil.createDirectories(getDataFolder());
        ConfigHolder.registerConfigs();
        PluginCommandManager.registerDefaults();
        registerCommands();

        try {
            sqlDataTest();
        } catch (SQLException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

    private void sqlDataTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        mySQLConnection.getConnection()
                .createStatement().executeUpdate(
                        "CREATE TABLE IF NOT EXISTS testdata (uuid varchar(255), data varchar(255))"
                );


        MySQLStorage storage = new MySQLStorage(
                "SELECT data FROM testdata WHERE uuid = ?",
                "INSERT INTO testdata (uuid, data) VALUES (?, ?)",
                "DELETE FROM testdata WHERE uuid = ?");
        LocalData data = storage.load("5583b04b-ff4e-42dc-b7c7-5e9d3db046d8");
        System.out.println(data);
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
