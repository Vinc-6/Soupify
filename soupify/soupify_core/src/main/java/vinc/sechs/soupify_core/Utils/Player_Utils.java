package vinc.sechs.soupify_core.Utils;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.UUID;

import vinc.sechs.soupify_core.Soupify_core;

public class Player_Utils {

    public void loadOrCreatePlayer(Player player) {

        Bukkit.getScheduler().runTaskAsynchronously(Soupify_core.instance, () -> {

            try (Connection conn = Soupify_core.getDataSource().getConnection()) {

                String checkSql = "SELECT uuid FROM players WHERE uuid = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, player.getUniqueId().toString());

                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    String updateSql = "UPDATE players SET last_join = NOW(), name = ? WHERE uuid = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);

                    updateStmt.setString(1, player.getName());
                    updateStmt.setString(2, player.getUniqueId().toString());

                    updateStmt.executeUpdate();

                } else {
                    String insertSql = "INSERT INTO players (uuid, name, first_join) VALUES (?, ?, NOW())";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);

                    insertStmt.setString(1, player.getUniqueId().toString());
                    insertStmt.setString(2, player.getName());

                    insertStmt.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadOrCreatePlayerSettings(Player player) {

        Bukkit.getScheduler().runTaskAsynchronously(Soupify_core.instance, () -> {

            try (Connection conn = Soupify_core.getDataSource().getConnection()) {

                String checkSql = "SELECT player_uuid FROM settings WHERE player_uuid = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, player.getUniqueId().toString());

                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                } else {
                    String insertSql = "INSERT INTO settings (player_uuid) VALUES (?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);

                    insertStmt.setString(1, player.getUniqueId().toString());

                    insertStmt.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
