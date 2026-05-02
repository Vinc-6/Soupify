package vinc.sechs.soupify_core.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Utils.Server_Utils;

public class HubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (player != null) {
            Server_Utils Server_Utils = new Server_Utils();
            Server_Utils.sendPlayerToServer(player, "lobby");
        }

        return true;
    }
}
