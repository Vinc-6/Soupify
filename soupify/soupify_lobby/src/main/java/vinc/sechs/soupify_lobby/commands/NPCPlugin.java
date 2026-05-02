package vinc.sechs.soupify_lobby.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.entity.ArmorStand;

public class NPCPlugin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können NPCs spawnen!");
            return true;
        }

        Player player = (Player) sender;
        Location loc = player.getLocation();

        // NPC als ArmorStand spawnen
        ArmorStand npc = player.getWorld().spawn(loc, ArmorStand.class);
        npc.setCustomName("MeinNPC");
        npc.setCustomNameVisible(true);
        npc.setGravity(false);   // steht fest
        npc.setBasePlate(false); // keine Baseplate
        npc.setVisible(true);    // ArmorStand sichtbar, kann als NPC herhalten
        npc.setArms(false);

        player.sendMessage("NPC erfolgreich gespawnt!");
        return true;
    }
}