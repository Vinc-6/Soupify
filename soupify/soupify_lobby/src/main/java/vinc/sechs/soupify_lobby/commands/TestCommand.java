package vinc.sechs.soupify_lobby.commands;

import org.bukkit.command.CommandExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import vinc.sechs.soupify_lobby.Soupify_Lobby;


import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;


import java.util.HashMap;
import java.util.UUID;


public class TestCommand implements CommandExecutor {

    private final HashMap<UUID, ArmorStand> mounts = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;
        ArmorStand stand1 = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        stand1.setVisible(true);
        stand1.setGravity(false);
        stand1.setMarker(true);

        ArmorStand stand = player.getWorld().spawn(stand1.getEyeLocation().add(0, 1,0), ArmorStand.class);
        stand.setVisible(true);
        stand.setGravity(false);
        stand.setMarker(true);

        Bat bat = player.getWorld().spawn(stand1.getEyeLocation(), Bat.class);
        bat.setCustomName("test");
        bat.setCustomNameVisible(true);

        Boat boat = player.getWorld().spawn(stand1.getEyeLocation(), Boat.class);
        boat.setCustomName("test");
        boat.setPassenger(player);
        boat.setCustomNameVisible(true);

        bat.setPassenger(boat);
        stand1.setPassenger(stand);
        stand.setPassenger(bat);


        new BukkitRunnable() {
            @Override
            public void run() {
                if (!stand.isValid()) {
                    cancel();
                    return;
                }
                stand.teleport(stand1.getEyeLocation().add(0, 1,0));
            }
        }.runTaskTimer(Soupify_Lobby.instance, 0L, 0L);




        new BukkitRunnable() {
            @Override
            public void run() {
                if (!bat.isValid()) {
                    cancel();
                    return;
                }
                bat.teleport(stand.getEyeLocation());
            }
        }.runTaskTimer(Soupify_Lobby.instance, 0L, 0L);


        return true;


    }
}
