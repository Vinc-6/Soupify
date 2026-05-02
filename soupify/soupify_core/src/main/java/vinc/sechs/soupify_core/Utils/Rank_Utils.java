package vinc.sechs.soupify_core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Soupify_core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class Rank_Utils {

    public enum Rank {
        Default(0),
        Owner(1),
        Admin(2),
        Developer(3),
        Moderator(4),
        Media(7),
        Legend(8),
        VIP(9)
        ;

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public static Rank fromValue(int value) {
        for (Rank r : Rank.values()) {
            if (r.value  == value) {
                return r;
            }
        }
        return Rank.Default;
    }

    public Rank getRanked(Player player) {
        int id = 0;

        try (Connection conn = Soupify_core.getDataSource().getConnection()) {
            String sql = "SELECT rankID FROM players WHERE uuid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, player.getUniqueId().toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("rankID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Rank.Default;
        }

        return fromValue(id);
    }

    public String getTag(Rank rank) {

        String tag = "";
        try (Connection conn = Soupify_core.getDataSource().getConnection()) {
            String sql = "SELECT tag FROM ranks WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rank.getValue());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tag = rs.getString("tag");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }

        return tag;
    }

    public String getColour(Rank rank) {

        String colour = "§7";
        try (Connection conn = Soupify_core.getDataSource().getConnection()) {
            String sql = "SELECT colour FROM ranks WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rank.getValue());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                colour = rs.getString("colour");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "§7";
        }

        return colour;
    }

    public String getName(Rank rank) {

        String name = "Default";
        try (Connection conn = Soupify_core.getDataSource().getConnection()) {
            String sql = "SELECT name FROM ranks WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rank.getValue());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "§7";
        }

        return name;
    }

    public void setRanked(Player player, Rank rank) {
        try (Connection conn = Soupify_core.getDataSource().getConnection()) {

            String sql = "UPDATE players SET rankID = ? WHERE uuid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, rank.getValue());
            stmt.setString(2, player.getUniqueId().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRanked(Player player) {
        try (Connection conn = Soupify_core.getDataSource().getConnection()) {

            String sql = "UPDATE players SET rankID = null WHERE uuid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, player.getUniqueId().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasPermissions(Rank rank, Player player) {
        Rank playerRank = getRanked(player);

        if (playerRank == Rank.Default) {
            return false;
        }

        if (playerRank.getValue() <= rank.getValue()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String noPermission() {
        return "§cYou do not have the required rank to execute this command.";
    }
}
