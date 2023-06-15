package me.imsergioh.newgencore.instance.config;

import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.backend.RedisConnection;
import me.imsergioh.newgencore.holder.ConfigHolder;

import java.io.File;

public class RedisConfig extends PluginLocalConfig {

    private static final NewGenCore plugin = NewGenCore.getPlugin();

    public RedisConfig() {
        super(new File(plugin.getDataFolder(), "redis.yml"));

        addDefault("hostname", "localhost");
        addDefault("port", 6379);
        addDefault("usePassword", false);
        addDefault("password", "DefaultRedisPassword");
        save();

        String password = isBoolean("usePassword") ? getString("password") : null;

        NewGenCore.setRedisConnection(
                new RedisConnection(
                        getString("hostname"),
                        getInt("port"), password));
    }
}
