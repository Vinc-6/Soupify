package vinc.sechs.soupify_core.ServerBasic;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vinc.sechs.soupify_core.Commands.SettingsCommand;
import vinc.sechs.soupify_core.Utils.Inventory_Utils;
import vinc.sechs.soupify_core.Utils.Player_Utils;
import vinc.sechs.soupify_core.Utils.Settings_Utils;
import vinc.sechs.soupify_core.settings.ColorSettings;

import java.util.Arrays;

public class Settings implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {


        Player player = (Player) event.getWhoClicked();
        Settings_Utils settingsUtils = new Settings_Utils();

        if (event.getView().getTitle().equals("Settings"))  {

            if (event.getCurrentItem().getType() == Material.PAPER
                    && event.getCurrentItem().getItemMeta().getDisplayName() == (ColorSettings.firstColor + "§lChat Color")) {
                player.openInventory(Inventory_Utils.GetColourInv(player));
            }


            event.setCancelled(true);
        }

        if (event.getView().getTitle().equals("Chat Color"))  {

            if (event.getCurrentItem().getItemMeta().getDisplayName() == ("§c§lBack")) {
                player.openInventory(Inventory_Utils.CreateSettingsInv(player));
            }

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§9Blue":
                case "§1Dark Blue":
                case "§aGreen":
                case "§2Dark Green":
                case "§bCyan":
                case "§3Dark Cyan":
                case "§cRed":
                case "§4Dark Red":
                case "§dPink":
                case "§5Purple":
                case "§6Orange":
                case "§eYellow":
                case "§fWhite":
                case "§8Gray":
                    settingsUtils.SetChatColor(player,event.getCurrentItem().getItemMeta().getDisplayName().substring(0,2));
                    player.openInventory(Inventory_Utils.CreateSettingsInv(player));
                    break;
            }

            event.setCancelled(true);
        }

    }


}
