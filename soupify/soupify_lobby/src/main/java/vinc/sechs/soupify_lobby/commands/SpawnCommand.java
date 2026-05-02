package vinc.sechs.soupify_lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_lobby.LocationData;
import vinc.sechs.soupify_lobby.helper.ConfigHelper;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Utils.tpToSpawn(player);

        return true;
    }
}
