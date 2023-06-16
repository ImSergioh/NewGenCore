package me.imsergioh.newgencore.instance.config;

import lombok.Getter;
import me.imsergioh.newgencore.util.ExceptionsUtil;
import me.imsergioh.newgencore.util.FilesUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PluginLocalConfig implements IPluginConfig {

    @Getter
    private final File directory;
    @Getter
    public final File file;

    @Getter
    private FileConfiguration config;

    public PluginLocalConfig(File file) {
        this.file = file;
        directory = file.getParentFile();
        FilesUtil.createFileAndParents(file);
        load();
    }

    @Override
    public PluginLocalConfig save() {
        try {
            config.save(file);
        } catch (IOException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
        return this;
    }

    @Override
    public void load() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void set(String path, Object value) {
        config.set(path, value);
    }

    @Override
    public PluginLocalConfig addDefault(String path, Object value) {
        if (config.contains(path)) return this;
        set(path, value);
        return this;
    }

    @Override
    public Object get(String path) {
        return config.get(path);
    }

    @Override
    public String getString(String path) {
        return config.getString(path);
    }

    @Override
    public boolean contains(String path) {
        return config.contains(path);
    }

    @Override
    public boolean isBoolean(String path) {
        return config.getBoolean(path);
    }

    @Override
    public int getInt(String path) {
        return config.getInt(path);
    }

    @Override
    public double getDouble(String path) {
        return config.getDouble(path);
    }

    @Override
    public long getLong(String path) {
        return config.getLong(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }
}
