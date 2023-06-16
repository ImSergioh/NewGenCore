package me.imsergioh.newgencore.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class ConfigUtil {

    private static final char LOCATION_SEPARATOR = ' ';

    public static String locationToString(Location location) {
        return Objects.requireNonNull(location.getWorld()).getName() + LOCATION_SEPARATOR +
                location.getX() + LOCATION_SEPARATOR +
                location.getY() + LOCATION_SEPARATOR +
                location.getZ() + LOCATION_SEPARATOR +
                location.getYaw() + LOCATION_SEPARATOR +
                location.getPitch();

    }

    public static Location locationStringToLocation(String stringLocation) {
        String[] args = stringLocation.split(String.valueOf(LOCATION_SEPARATOR));
        double x, y, z;
        float yaw, pitch;
        World world = Bukkit.getWorld(args[0]);
        x = Double.parseDouble(args[1]);
        y = Double.parseDouble(args[2]);
        z = Double.parseDouble(args[3]);
        yaw = Float.parseFloat(args[4]);
        pitch = Float.parseFloat(args[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }
}
