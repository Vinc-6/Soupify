package vinc.sechs.soupify_lobby.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_lobby.LocationData;
import vinc.sechs.soupify_lobby.helper.ConfigHelper;

public class SetSpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        try {
                Player player = (Player) commandSender;
                Location location = player.getLocation();
                LocationData locationData = new LocationData("world",location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                ConfigHelper configHelper = new ConfigHelper();
                configHelper.setLocation("spawn",locationData);
                return true;
            }
            catch (Exception ex)
            {
                Player player = (Player) commandSender;
                player.sendMessage(ex.toString());
                return false;
            }
    }
}
