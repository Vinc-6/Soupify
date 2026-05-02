package vinc.sechs.soupify_core.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import vinc.sechs.soupify_core.Manager.ScoreboardManager;
import vinc.sechs.soupify_core.Utils.Rank_Utils;

public class NameTask implements Runnable {

    private final static NameTask instance = new NameTask();

    //private final Scoreboard board = ScoreboardManager.BOARD;
    private final Rank_Utils rankUtils = new Rank_Utils();

    private NameTask() {}

    public static NameTask getInstance() {
        return instance;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updatePlayerDisplay(player);
        }
    }

    private void updatePlayerDisplay(Player player) {

        Rank_Utils.Rank rank = rankUtils.getRanked(player);
        String colour = rankUtils.getColour(rank);

        String displayName = colour + player.getName();

        // TABLIST nur ändern wenn nötig
        if (!displayName.equals(player.getPlayerListName())) {
            player.setPlayerListName(displayName);
        }


            player.setDisplayName(displayName);


        updateNametag(player);
    }

    private void updateNametag(Player viewer) {

        Scoreboard board = viewer.getScoreboard();
        if (board == null) return;

        for (Player target : Bukkit.getOnlinePlayers()) {

            String teamName = target.getName();

            if (teamName.length() > 16) {
                teamName = teamName.substring(0, 16);
            }

            Team team = board.getTeam(teamName);

            if (team == null) {
                team = board.registerNewTeam(teamName);
            }

            // 👉 Farbe aus DB für den TARGET Spieler

            Rank_Utils.Rank rank = rankUtils.getRanked(target);


            String prefix = rankUtils.getColour(rank);

            if (!prefix.equals(team.getPrefix())) {
                team.setPrefix(prefix);
            }

            if (!team.hasEntry(target.getName())) {
                team.addEntry(target.getName());
            }
        }
    }

    /*

    private void updateNametag(Player player, String prefix) {

        String teamName = player.getName();

        // 1.8 Limit
        if (teamName.length() > 16) {
            teamName = teamName.substring(0, 16);
        }

        if (player.getScoreboard() != null) {



        Scoreboard board = player.getScoreboard();



        Team team = board.getTeam(teamName);

        if (team == null) {
            team = board.registerNewTeam(teamName);
        }

        // nur setzen wenn nötig
        if (!prefix.equals(team.getPrefix())) {
            team.setPrefix(prefix);
        }

        if (!team.hasEntry(player.getName())) {
            team.addEntry(player.getName());
        }

        }

    }

     */
}