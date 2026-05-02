package vinc.sechs.soupify_lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_lobby.LocationData;
import vinc.sechs.soupify_lobby.helper.ConfigHelper;

public class Utils {

    public static void tpToSpawn(Player player) {

        ConfigHelper configHelper = new ConfigHelper();
        LocationData locationData = configHelper.getLocation("spawn");
        World world = Bukkit.getWorld("world"); // Name der Welt
        double x = locationData.x;
        double y = locationData.y;
        double z = locationData.z;
        float yaw = locationData.yaw;   // Richtung horizontal
        float pitch = locationData.pitch; // Richtung vertikal

        Location loc = new Location(world, x, y, z, yaw, pitch);

        player.teleport(loc);
    }
}
