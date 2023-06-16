
package me.imsergioh.newgencore.manager;

import lombok.Getter;
import me.imsergioh.newgencore.command.setSpawnCMD;
import me.imsergioh.newgencore.command.spawnCMD;
import me.imsergioh.newgencore.instance.config.PluginLocalConfig;
import me.imsergioh.newgencore.listener.LobbyManagerListeners;
import me.imsergioh.newgencore.util.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LobbyManager extends ConfigPluginManager {

    private final static String CONFIG_SPAWN_PATH = "spawnLocation";

    private final static String CONFIG_SPAWN_COMMAND_ENABLED_PATH = "spawnCommandEnabled";
    public final static String CONFIG_TELEPORT_AT_JOIN_PATH = "teleportAtJoin";
    public final static String CONFIG_CANCEL_DAMAGE_PATH = "cancelDamage";
    public final static String CONFIG_INFINITE_FOOD_PATH = "infiniteFood";
    public final static String CONFIG_REMOVE_JOIN_MESSAGE = "removeJoinMessage";
    public final static String CONFIG_REMOVE_QUIT_MESSAGE = "removeQuitMessage";

    @Getter
    private Location spawn;

    public LobbyManager(JavaPlugin plugin) {
        super(plugin, new PluginLocalConfig(
                new File(plugin.getDataFolder(), "lobby.yml")));
        loadSpawn();
        registerListeners(new LobbyManagerListeners(this));
        registerCommands(
                new setSpawnCMD(this));

        if (isEnabled(CONFIG_SPAWN_COMMAND_ENABLED_PATH)) {
            registerCommands(new spawnCMD(this));
        }
    }

    public void teleport(Entity entity) {
        entity.teleport(spawn);
    }

    @Override
    public void registerConfigDefaults() {
        config
                .addDefault(CONFIG_SPAWN_COMMAND_ENABLED_PATH, false)
                .addDefault(CONFIG_TELEPORT_AT_JOIN_PATH, true)
                .addDefault(CONFIG_CANCEL_DAMAGE_PATH, true)
                .addDefault(CONFIG_INFINITE_FOOD_PATH, true)
                .addDefault(CONFIG_REMOVE_JOIN_MESSAGE, true)
                .addDefault(CONFIG_REMOVE_QUIT_MESSAGE, true);
    }

    public boolean isSpawnSet() {
        return spawn != null;
    }

    public void setSpawn(Location location) {
        spawn = location;
        config.set(CONFIG_SPAWN_PATH, ConfigUtil.locationToString(location));
        config.save();
    }

    private void loadSpawn() {
        if (!config.contains(CONFIG_SPAWN_PATH)) return;
        spawn = ConfigUtil.locationStringToLocation(config.getString(CONFIG_SPAWN_PATH));
    }

}
