package me.imsergioh.newgencore.holder;

import lombok.Getter;
import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.instance.config.PluginLocalConfig;
import me.imsergioh.newgencore.manager.ConfigsManager;

import java.io.File;
import java.util.Map;

public class ConfigHolder {

    private static final NewGenCore plugin = NewGenCore.getPlugin();

    @Getter
    private static PluginLocalConfig pluginDefaultConfig;

    @Getter
    private static PluginLocalConfig sqlConfig;
    @Getter
    private static PluginLocalConfig mongodbConfig;
    @Getter
    private static PluginLocalConfig redisConfig;

    public static void registerConfigs() {
        pluginDefaultConfig = ConfigsManager.createLocalConfig(
                new File(plugin.getDataFolder(), "config.yml"),
                ConfigsManager.configDefault("backend.sql.enabled", false),
                ConfigsManager.configDefault("backend.mongodb.enabled", false),
                ConfigsManager.configDefault("backend.redis.enabled", false));

        sqlConfig = ConfigsManager.createLocalConfigIfEnabled(pluginDefaultConfig, "backend.sql.enabled",
                new File(plugin.getDataFolder(), "sql.yml"),
                ConfigsManager.configDefault("hostname", "localhost"),
                ConfigsManager.configDefault("port", 3306),
                ConfigsManager.configDefault("database", "NewGenCore"),
                ConfigsManager.configDefault("username", "root"),
                ConfigsManager.configDefault("password", "SQLP4ssw0rd#xDDDD"));

        mongodbConfig = ConfigsManager.createLocalConfigIfEnabled(pluginDefaultConfig, "backend.mongodb.enabled",
                new File(plugin.getDataFolder(), "mongodb.yml"),
                ConfigsManager.configDefault("uri", "mongodb://admin:MongoDBP4ssword#xDDDD@localhost:27017/db"));

        redisConfig = registerBackendConfig("redis.yml", "backend.redis.enabled")
                .addDefault("uri", "redis://RedisP4ssw0rd#xDDDD@admin:6379").save();
    }

    @SafeVarargs
    private static PluginLocalConfig registerBackendConfig(String name, String mainConfigPath, Map.Entry<String, Object>... defaults) {
        return ConfigsManager.createLocalConfigIfEnabled(pluginDefaultConfig, mainConfigPath,
                new File(plugin.getDataFolder(), name), defaults);
    }
}
