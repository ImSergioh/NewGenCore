
package me.imsergioh.newgencore.manager;

import lombok.Getter;
import me.imsergioh.newgencore.command.setSpawnCMD;
import me.imsergioh.newgencore.command.spawnCMD;
import me.imsergioh.newgencore.instance.PluginScoreboard;
import me.imsergioh.newgencore.instance.config.PluginLocalConfig;
import me.imsergioh.newgencore.listener.LobbyManagerListeners;
import me.imsergioh.newgencore.listener.ScoreboardsManagerListeners;
import me.imsergioh.newgencore.util.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class ScoreboardsManager extends ConfigPluginManager {

    private final static String CONFIG_DEFAULT_SCOREBOARD_PATH = "defaultScoreboard";

    public final static String CONFIG_ON_JOIN_SET_DEFAULT_SCOREBOARD_PATH = "onJoinSetDefaultScoreboard";

    private static final HashMap<String, PluginScoreboard> scoreboards = new HashMap<>();

    public ScoreboardsManager(JavaPlugin plugin) {
        super(plugin, new PluginLocalConfig(
                new File(plugin.getDataFolder(), "scoreboards.yml")));
        new File(getPlugin().getDataFolder() + "/scoreboards").mkdirs();
        registerListeners(new ScoreboardsManagerListeners(this));
        registerDefaultScoreboard();
        registerScoreboards();
    }

    public PluginScoreboard getDefaultScoreboard() {
        return getScoreboard(config.getString(CONFIG_DEFAULT_SCOREBOARD_PATH));
    }

    private void registerScoreboards() {
        for (File file : new File(getPlugin().getDataFolder() + "/scoreboards").listFiles()) {
            if (!file.getName().endsWith(".yml")) continue;
            registerScoreboard(file.getName().replace(".yml", ""));
        }
    }

    private void registerScoreboard(String name) {
        scoreboards.put(name,
                new PluginScoreboard(name,
                        new PluginLocalConfig(
                                new File(getPlugin().getDataFolder() + "/scoreboards", name + ".yml"))
                                .addDefault("title", "&b&lSCOREBOARD")
                                .addDefault("lines", Arrays.asList("line1", "line2", "line3"))
                                .save()));
    }

    public PluginScoreboard getScoreboard(String name) {
        return scoreboards.get(name);
    }

    private void registerDefaultScoreboard() {
        String name = config.getString(CONFIG_DEFAULT_SCOREBOARD_PATH);
        registerScoreboard(name);
    }

    @Override
    public void registerConfigDefaults() {
        config
                .addDefault(CONFIG_DEFAULT_SCOREBOARD_PATH, "main")
                .addDefault(CONFIG_ON_JOIN_SET_DEFAULT_SCOREBOARD_PATH, true);
    }
}
