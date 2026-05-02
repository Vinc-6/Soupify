package vinc.sechs.soupify_core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Manager.MessageManager;
import vinc.sechs.soupify_core.Utils.Rank_Utils;

import java.util.Arrays;
import java.util.UUID;

public class RCommand implements CommandExecutor {

    private final MessageManager messageManager;

    public RCommand(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUsage: /r <message>");
            return true;
        }

        UUID targetUUID = messageManager.getLastMessage(player.getUniqueId());

        if (targetUUID == null) {
            player.sendMessage("§cDu hast niemandem zuletzt geschrieben!");
            return true;
        }

        Player target = Bukkit.getPlayer(targetUUID);

        if (target == null) {
            player.sendMessage("§cDer Spieler ist nicht online!");
            return true;
        }

        String message = String.join(" ", args);

        Rank_Utils rankUtils = new Rank_Utils();

        String colourPlayer = rankUtils.getColour(rankUtils.getRanked(player));
        String colourTarget = rankUtils.getColour(rankUtils.getRanked(target));

        String messageTarget = "§e[MSG] §7to " + colourTarget + target.getName() + "§7: §f" + message;
        String messageSender = "§e[MSG] §7from " + colourPlayer + player.getName() + "§7: §f" + message;

        player.sendMessage(messageTarget);
        target.sendMessage(messageSender);

        target.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);

        messageManager.setLastMessage(player.getUniqueId(), target.getUniqueId());
        return true;
    }

}
