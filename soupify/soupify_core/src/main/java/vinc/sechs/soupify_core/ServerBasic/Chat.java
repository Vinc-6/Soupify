package vinc.sechs.soupify_core.ServerBasic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import vinc.sechs.soupify_core.Utils.Rank_Utils;
import vinc.sechs.soupify_core.Utils.Settings_Utils;

public class Chat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        Rank_Utils rankUtils = new Rank_Utils();
        Rank_Utils.Rank rank = rankUtils.getRanked(player);

        Settings_Utils settingsUtils = new Settings_Utils();
        String prefix = settingsUtils.GetChatColor(player);

        String format = "";

        // %s = Spielername, %s = Nachricht
        if (rank.getValue() == 0) {
            format = "§7%s: §f%s";
        }
        else {
            String tag = rankUtils.getTag(rank);
            String colour = rankUtils.getColour(rank);

            if (prefix == null)
            {
                prefix = colour;
            }

            String playername = player.getName();
            String message = event.getMessage();


            format = colour + "["+ tag + "] " + prefix + playername + "§7: §f" + message ;
        }

        event.setFormat(format);

    }
}
