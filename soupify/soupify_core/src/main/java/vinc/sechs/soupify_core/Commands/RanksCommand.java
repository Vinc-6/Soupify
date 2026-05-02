package vinc.sechs.soupify_core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Soupify_core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Bukkit.getScheduler().runTaskAsynchronously(Soupify_core.instance, () -> {

            try (Connection conn = Soupify_core.getDataSource().getConnection()) {

                String checkSql = "SELECT * FROM ranks";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);

                ResultSet rs = checkStmt.executeQuery();

                String ranks = "";

                while (rs.next()) {

                    String tag = rs.getString("tag");
                    String name = rs.getString("name");
                    String colour = rs.getString("colour");


                    ranks += colour+ "[" + tag + "] " + "Example§7:§f " + name;

                    if (!rs.isLast()) {
                        ranks += "\n";

                    }
                    else {
                        player.sendMessage(ranks);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return true;
    }
}
