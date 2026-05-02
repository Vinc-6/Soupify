package vinc.sechs.soupify_lobby.listeners;


import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import vinc.sechs.soupify_lobby.Settings.ColorSettings;
import vinc.sechs.soupify_lobby.commands.Utils;
import vinc.sechs.soupify_lobby.helper.InventoryHelper;
import vinc.sechs.soupify_lobby.helper.MessageHelper;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.event.player.PlayerPickupItemEvent;


public class PlayerListener implements Listener {


    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {

        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (e.getItem() == null) return;

        //Warps
        if (e.getItem().getType() == Material.COMPASS && e.getItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "Navigation")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR ||
                    e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.openInventory(InventoryHelper.GetWarpInventory());
            }
        }

        //Party
        if (e.getItem().getType() == Material.REDSTONE_COMPARATOR && e.getItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "Settings")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR ||
                    e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                MessageHelper.commingSoonMSG(player);
            }
        }

        //Spawn
        if (e.getItem().getType() == Material.NETHER_STAR && e.getItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "Spawn")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR ||
                    e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Utils.tpToSpawn(player);
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        // Spawn-Grund prüfen, z.B. NATURAL = natürliche Spawns (Tiere, Monster)
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            event.setCancelled(true); // Verhindert das Spawnen
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getLocation().getY() <= 0) {
            Utils.tpToSpawn(player);
        }
    }

    @EventHandler
    public void onPlayerDropEvent(PlayerDropItemEvent event) {
            event.setCancelled(true);
    }
}
