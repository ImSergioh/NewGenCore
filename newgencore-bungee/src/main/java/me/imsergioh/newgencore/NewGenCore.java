package me.imsergioh.newgencore;

import net.md_5.bungee.api.plugin.Plugin;

public class NewGenCore extends Plugin {

    private static NewGenCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public static NewGenCore getPlugin() {
        return plugin;
    }
}
