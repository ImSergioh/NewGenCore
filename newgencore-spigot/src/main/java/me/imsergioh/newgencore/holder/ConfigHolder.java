package me.imsergioh.newgencore.holder;

import lombok.Getter;
import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.instance.exception.PluginException;
import me.imsergioh.newgencore.instance.config.MongoDBConfig;
import me.imsergioh.newgencore.instance.config.PluginLocalConfig;
import me.imsergioh.newgencore.instance.config.RedisConfig;
import me.imsergioh.newgencore.instance.config.SQLConfig;
import me.imsergioh.newgencore.manager.ConfigsManager;
import me.imsergioh.newgencore.util.ExceptionsUtil;

import java.io.File;

public class ConfigHolder {

    private static final NewGenCore plugin = NewGenCore.getPlugin();

    @Getter
    private static PluginLocalConfig pluginDefaultConfig;

    @Getter
    private static SQLConfig sqlConfig;
    @Getter
    private static MongoDBConfig mongodbConfig;
    @Getter
    private static RedisConfig redisConfig;

    public static void registerConfigs() {
        pluginDefaultConfig = ConfigsManager.createLocalConfig(
                new File(plugin.getDataFolder(), "config.yml"),
                ConfigsManager.configDefault("backend.sql.enabled", false),
                ConfigsManager.configDefault("backend.mongodb.enabled", false),
                ConfigsManager.configDefault("backend.redis.enabled", false));

        try {
            sqlConfig = (SQLConfig) createInstanceOfConfig("backend.sql.enabled", SQLConfig.class);
        } catch (Exception e) {
            ExceptionsUtil.handleSimpleException(e);
        }

        try {
            mongodbConfig = (MongoDBConfig) createInstanceOfConfig("backend.mongodb.enabled", MongoDBConfig.class);
        } catch (Exception e) {
            ExceptionsUtil.handleSimpleException(e);
        }

        try {
            redisConfig = (RedisConfig) createInstanceOfConfig("backend.redis.enabled", RedisConfig.class);
        } catch (Exception e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

    private static PluginLocalConfig createInstanceOfConfig(String configPath, Class<?> instanceClass) throws Exception {
        if (!isMainConfigEnabled(configPath)) return null;
        // CHECK IF EXTENDS TO PLUGIN LOCAL CONFIG (if not throw a plugin exception)
        if (instanceClass.getSuperclass().equals(PluginLocalConfig.class)) {
            return (PluginLocalConfig) instanceClass.getConstructor().newInstance();
        }
        throw new PluginException("Error trying to register config from class");
    }

    public static PluginLocalConfig getConfigFromClass(Class<?> clazz) {
        if (clazz.isAssignableFrom(SQLConfig.class)) return sqlConfig;
        if (clazz.isAssignableFrom(MongoDBConfig.class)) return mongodbConfig;
        if (clazz.isAssignableFrom(RedisConfig.class)) return redisConfig;
        return pluginDefaultConfig;
    }

    public static boolean isMainConfigEnabled(String path) {
        return pluginDefaultConfig.isBoolean(path);
    }
}
