package vinc.sechs.soupify_lobby.helper;

import org.bukkit.entity.Player;
import vinc.sechs.soupify_lobby.Soupify_Lobby;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ServerSwitcherHelper {

    // Spieler auf einen BungeeCord Server schicken
    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server); // Name des Zielservers
            player.sendPluginMessage(Soupify_Lobby.instance, "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}