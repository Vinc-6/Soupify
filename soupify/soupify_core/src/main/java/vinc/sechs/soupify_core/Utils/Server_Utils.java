package vinc.sechs.soupify_core.Utils;

import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Soupify_core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Server_Utils {

    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server); // Name des Zielservers
            player.sendPluginMessage(Soupify_core.instance, "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
