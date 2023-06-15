package me.imsergioh.newgencore.instance.config;

import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.backend.MongoDBConnection;

import java.io.File;

public class MongoDBConfig extends PluginLocalConfig {

    private static final NewGenCore plugin = NewGenCore.getPlugin();

    public MongoDBConfig() {
        super(new File(plugin.getDataFolder(), "mongodb.yml"));

        addDefault("uri", "mongodb://admin:MongoDBP4ssword#xDDDD@localhost:27017/db");
        save();
        NewGenCore.setMongoDBConnection(new MongoDBConnection(getString("uri")));
    }
}
