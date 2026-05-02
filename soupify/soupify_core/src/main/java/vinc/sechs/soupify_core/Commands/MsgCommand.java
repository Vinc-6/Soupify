package vinc.sechs.soupify_core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Manager.MessageManager;
import vinc.sechs.soupify_core.Utils.Rank_Utils;

import java.util.Arrays;

public class MsgCommand implements CommandExecutor {

    private final MessageManager messageManager;

    public MsgCommand(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /msg <player> <message>");
            return true;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("§cPlayer not found!");
            return true;
        }

        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        Rank_Utils rankUtils = new Rank_Utils();

        String colourPlayer = rankUtils.getColour(rankUtils.getRanked(player));
        String colourTarget = rankUtils.getColour(rankUtils.getRanked(target));

        String messageTarget = "§e[MSG] §7to " + colourTarget + target.getName() + "§7: §f" + message;
        String messageSender = "§e[MSG] §7from " + colourPlayer + player.getName() + "§7: §f" + message;

        player.sendMessage(messageTarget);
        target.sendMessage(messageSender);

        // 💡 speichern für /r
        messageManager.setLastMessage(player.getUniqueId(), target.getUniqueId());

        return true;
    }
}
