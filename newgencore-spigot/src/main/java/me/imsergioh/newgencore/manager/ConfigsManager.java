package me.imsergioh.newgencore.manager;

import me.imsergioh.newgencore.instance.config.PluginLocalConfig;

import java.io.File;
import java.util.AbstractMap;
import java.util.Map;

public class ConfigsManager {

    @SafeVarargs
    public static PluginLocalConfig createLocalConfig(File file, Map.Entry<String, Object>... defaults) {
        PluginLocalConfig config = new PluginLocalConfig(file);

        // ADD DEFAULT FOR EACH DEFAULTS ENTRIES
        for (Map.Entry<String, Object> entry : defaults) {
            config.addDefault(entry.getKey(), entry.getValue());
        }

        config.save();
        return config;
    }

    @SafeVarargs
    public static PluginLocalConfig createLocalConfigIfEnabled(
            PluginLocalConfig config, String path,
            File file, Map.Entry<String, Object>... defaults) {
        if (!config.isBoolean(path)) return null;
        return createLocalConfig(file, defaults);
    }

    public static Map.Entry<String, Object> configDefault(String path, Object value) {
        return new AbstractMap.SimpleEntry<>(path, value);
    }

}
