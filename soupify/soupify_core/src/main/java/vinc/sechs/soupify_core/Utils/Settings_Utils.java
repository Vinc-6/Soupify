package vinc.sechs.soupify_core.Utils;

import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Soupify_core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Settings_Utils {

    public String GetChatColor(Player player) {

        String chatcolour = "";
        try (Connection conn = Soupify_core.getDataSource().getConnection()) {
            String sql = "SELECT chat_colour FROM settings WHERE player_uuid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, player.getUniqueId().toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                chatcolour = rs.getString("chat_colour");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }

        return chatcolour;

    }

    public String SetChatColor(Player player, String color) {

        try (Connection conn = Soupify_core.getDataSource().getConnection()) {

            String sql = "UPDATE settings SET chat_colour = ? WHERE player_uuid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, color);
            stmt.setString(2, player.getUniqueId().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
