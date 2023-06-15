package me.imsergioh.newgencore.instance.config;

import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.backend.SQLConnection;

import java.io.File;

public class SQLConfig extends PluginLocalConfig {

    private static final NewGenCore plugin = NewGenCore.getPlugin();

    public SQLConfig() {
        super(new File(plugin.getDataFolder(), "sql.yml"));

        addDefault("hostname", "localhost");
        addDefault("port", 3306);
        addDefault("database", "NewGenCore");
        addDefault("username", "root");
        addDefault("password", "SQLP4ssw0rd#xDDDD");
        save();
        NewGenCore.setSqlConnection(
                new SQLConnection(
                        getString("hostname"),
                        getInt("port"),
                        getString("database"),
                        getString("username"),
                        getString("password")));
    }
}
