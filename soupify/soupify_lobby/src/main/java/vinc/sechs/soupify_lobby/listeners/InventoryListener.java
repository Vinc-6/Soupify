package vinc.sechs.soupify_lobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import vinc.sechs.soupify_lobby.Settings.ColorSettings;
import vinc.sechs.soupify_lobby.commands.Utils;
import vinc.sechs.soupify_lobby.helper.InventoryHelper;
import vinc.sechs.soupify_lobby.helper.MessageHelper;
import vinc.sechs.soupify_lobby.helper.ServerSwitcherHelper;

public class InventoryListener implements Listener {

    @EventHandler
    public void onJoin(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        //Hotbar
        if (event.getCurrentItem().getType() == Material.COMPASS && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "Navigation")) {
            player.openInventory(InventoryHelper.GetWarpInventory());
        }

        //Spawn
        if (event.getCurrentItem().getType() == Material.NETHER_STAR && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "Spawn")) {
            Utils.tpToSpawn(player);
        }

        //Navigation
        if (event.getCurrentItem().getType() == Material.DIAMOND_SWORD && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "§lPractice")) {
            MessageHelper.commingSoonMSG(player);
        }

        if (event.getCurrentItem().getType() == Material.STONE && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "§lCaveFFA")) {
            MessageHelper.commingSoonMSG(player);
        }

        if (event.getCurrentItem().getType() == Material.MUSHROOM_SOUP && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "§lClan X Clan")) {
            MessageHelper.commingSoonMSG(player);
        }

        if (event.getCurrentItem().getType() == Material.RED_MUSHROOM && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "§lTraining")) {
            if (player != null) {
                ServerSwitcherHelper serverSwitcherHelper = new ServerSwitcherHelper();
                serverSwitcherHelper.sendPlayerToServer(player, "training");
            }
        }

        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }
}
