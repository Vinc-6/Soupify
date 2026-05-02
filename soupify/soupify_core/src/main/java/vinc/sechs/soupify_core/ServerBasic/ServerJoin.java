package vinc.sechs.soupify_core.ServerBasic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import vinc.sechs.soupify_core.Manager.ScoreboardManager;
import vinc.sechs.soupify_core.Utils.Player_Utils;
import vinc.sechs.soupify_core.Utils.Rank_Utils;

public class ServerJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Player_Utils playerUtils = new Player_Utils();
        playerUtils.loadOrCreatePlayer(player);
        playerUtils.loadOrCreatePlayerSettings(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}