package vinc.sechs.soupify_lobby.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import vinc.sechs.soupify_core.Soupify_core;
import vinc.sechs.soupify_core.Utils.Rank_Utils;
import vinc.sechs.soupify_core.Manager.ScoreboardManager;
import vinc.sechs.soupify_lobby.Soupify_Lobby;
import vinc.sechs.soupify_lobby.Settings.ColorSettings;
import vinc.sechs.soupify_lobby.Settings.NameSettings;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask  implements Runnable{

    private final static ScoreboardTask instance = new ScoreboardTask();
    private int tickCounter = 0;
    private int ticksEffect = 300;

    private ScoreboardTask() {

    }


    @Override
    public void run() {

        tickCounter++;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard() != null && player.getScoreboard().getObjective("Soupify") != null) {
                if (tickCounter % ticksEffect == 0) {
                    doEffect(player, Soupify_Lobby.instance);
                }
                else {
                    updateScoreboard(player);
                }
            }
            else {
                    createNewScoreboard(player);
            }
        }
    }

    public static ScoreboardTask getInstance() {
        return instance;
    }

    private void createNewScoreboard(Player player) {



        String playerCount = getPlayerCount();
        String rank = getRank(player);
        String clan = getClan();


        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(NameSettings.serverName, "dummy");
        objective.setDisplayName(ColorSettings.firstColor + "§l"+ NameSettings.serverName + "§r§7 \u2503 §fLobby");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("§7§m" + line() + "§r").setScore(7);
        objective.getScore("").setScore(3);
        objective.getScore("§7§m" + line() + "§8").setScore(1);

        Team teamPlayer = scoreboard.registerNewTeam("teamPlayer");

        String playerKey = ChatColor.RED.toString();
        teamPlayer.addEntry(playerKey);
        teamPlayer.setPrefix("§f Players: ");
        teamPlayer.setSuffix(playerCount);
        objective.getScore(playerKey).setScore(6);

        Team teamRank = scoreboard.registerNewTeam("teamRank");
        String rankKey = ChatColor.BLACK.toString();
        teamRank.addEntry(rankKey);
        teamRank.setPrefix("§f Rank: ");
        teamRank.setSuffix(rank);
        objective.getScore(rankKey).setScore(5);

        Team teamClan = scoreboard.registerNewTeam("teamClan");
        String clanKey = ChatColor.BLUE.toString();
        teamClan.addEntry(clanKey);
        teamClan.setPrefix("§f Clan: ");
        teamClan.setSuffix(clan);
        objective.getScore(clanKey).setScore(4);


        String servername = getServerIP();

        String s1 = "";
        String s2 = "";

        if (servername.length() > 15) {
            s1 = servername.substring(0, 15);
            s2 = ColorSettings.firstColor + servername.substring(15, servername.length());
        }
        else {
            s1 = servername;
        }

        Team teamIP = scoreboard.registerNewTeam("teamIP");
        String ipKey = ChatColor.GOLD.toString();
        teamIP.addEntry(ipKey);
        teamIP.setPrefix(" " + s1);
        teamIP.setSuffix(s2);
        objective.getScore(ipKey).setScore(2);

        player.setScoreboard(scoreboard);

    }

    private static void updateScoreboard(Player player) {

        String playerCount = getPlayerCount();
        String rank = getRank(player);
        String clan = getClan();

        Scoreboard scoreboard = player.getScoreboard();
        Team teamPlayer = scoreboard.getTeam("teamPlayer");
        teamPlayer.setSuffix(playerCount);

        Team teamRank = scoreboard.getTeam("teamRank");
        teamRank.setSuffix(rank);

        Team teamClan = scoreboard.getTeam("teamClan");
        teamClan.setSuffix(clan);
    }

    private static void updateScoreboard(Player player, String servername1, String servername2) {

        String playerCount = getPlayerCount();
        String rank = getRank(player);
        String clan = getClan();

        Scoreboard scoreboard = player.getScoreboard();

        Team teamIP = scoreboard.getTeam("teamIP");

        teamIP.setPrefix(" " + servername1);
        teamIP.setSuffix(servername2);

    }

    private static String line() {

        String s = "";

        for (int i = 0; i <= 28; i++) {
            s += " ";
        }

        return s;

    }

    private static String getPlayerCount() {
        return ColorSettings.firstColor+ Integer.toString(Bukkit.getOnlinePlayers().size());
    }

    private static String getRank(Player player) {



        Rank_Utils rankUtils = new Rank_Utils();

        Rank_Utils.Rank rank = rankUtils.getRanked(player);

        return rankUtils.getColour(rank) + rankUtils.getName(rank);
    }

    private static String getClan() {
       return ColorSettings.firstColor +"None";
    }

    private static String getServerIP() {
        return ColorSettings.firstColor + NameSettings.serverAdresse;
    }

    private static void doEffect(Player player, JavaPlugin plugin) {

        String text = NameSettings.serverAdresse;

       new BukkitRunnable() {

            int index = 0;

            int ticksEffect = 0;

            Boolean white = true;

            @Override
            public void run() {

                if (index > text.length()) {
                    if (ticksEffect == 30) {
                        cancel();
                        return;
                    }

                    if (ticksEffect == 5 || ticksEffect ==  15 || ticksEffect == 25 ) {
                        white = false;
                    }

                    if (ticksEffect == 10 || ticksEffect == 20) {
                        white = true;
                    }

                    if (white == true) {

                        String servername = "§f" + NameSettings.serverAdresse;

                        String s1 = "";
                        String s2 = "";

                        if (servername.length() > 15) {
                            s1 = servername.substring(0, 15);
                            s2 = "§f" + servername.substring(15, servername.length());
                        }
                        else {
                            s1 = servername;
                        }
                        updateScoreboard(player, s1,s2);
                    }
                    else {

                        String servername = getServerIP();

                        String s1 = "";
                        String s2 = "";

                        if (servername.length() > 15) {
                            s1 = servername.substring(0, 15);
                            s2 = ColorSettings.firstColor + servername.substring(15, servername.length());
                        }
                        else {
                            s1 = servername;
                        }

                        updateScoreboard(player, s1 , s2);
                    }
                    ticksEffect++;
                }
                else {
                    doEffectLine(text, index, player);
                }
                index++;
            }

        }.runTaskTimer(plugin, 0L, 1L); // 1 Tick = 50ms
    }

    private static void doEffectLine(String text,int index, Player player) {
        String s1 = EffectString1(index, text);
        String s2 = EffectString2(index, text);

        updateScoreboard(player, s1, s2);
    }

    private static String EffectString1(int index, String text) {
        if (index >= text.length()) {
            return ("§f" + text).substring(0,15);
        }

        switch (index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                String retun = "§f" + text.substring(0, index) + ColorSettings.secondColor + text.substring(index, index + 1) + ColorSettings.firstColor + text.substring(index + 1, text.length());
                return retun.substring(0,15);
            case 8:
                return "§f" + text.substring(0,8) + ColorSettings.secondColor + text.substring(8,9);
            case 9:
                return "§f" + text.substring(0,9) + ColorSettings.secondColor + text.substring(9,10);
            case 10:
                return "§f" + text.substring(0,10) + ColorSettings.secondColor + text.substring(10,11);
            case 11:
                return "§f" + text.substring(0,11);
            case 12:
                return "§f" + text.substring(0,12);
            default:
                return  "§f" + text.substring(0,13);
        }
    }

    private static String EffectString2(int index, String text) {
        if (text.length() + 6 < 16) {
            return "";
        }

        if (index >= text.length()) {
            String s = "§f" + text;
            return "§f" + s.substring(15,s.length());
        }

        switch (index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return ColorSettings.firstColor +  text.substring(9, text.length());
            case 9:
                return ColorSettings.firstColor +  text.substring(10, text.length());
            case 10:
                return ColorSettings.firstColor +  text.substring(11, text.length());
            case 11:
                return ColorSettings.secondColor +  text.substring(11, 12) + ColorSettings.firstColor +  text.substring(12, text.length());
            case 12:
                return ColorSettings.secondColor +  text.substring(12, 13) + ColorSettings.firstColor +  text.substring(13, text.length());
            case 13:
                return ColorSettings.secondColor +  text.substring(13, 14) + ColorSettings.firstColor +  text.substring(14, text.length());
            default:
                String retun = "§f" + text.substring(0, index) + ColorSettings.secondColor + text.substring(index, index + 1) + ColorSettings.firstColor + text.substring(index + 1, text.length());

                if (index < text.length()) {
                    String color1 = "§f" + text.substring(0, index);
                    String color2 = ColorSettings.secondColor + text.substring(index, index + 1);
                    String color3 = ColorSettings.firstColor + text.substring(index + 1, text.length());

                    String s = color1 + color2 + color3;

                    return "§f" + s.substring(15,text.length());
                }

                return "";
        }
    }
}
