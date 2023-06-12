package me.imsergioh.newgencore.instance.config;

import java.util.List;

public interface IPluginConfig {

    IPluginConfig save();
    void load();
    void set(String path, Object value);

    IPluginConfig addDefault(String path, Object value);

    Object get(String path);
    String getString(String path);
    boolean contains(String path);
    boolean isBoolean(String path);
    int getInt(String path);
    double getDouble(String path);
    long getLong(String path);

    List<String> getStringList(String path);
}
