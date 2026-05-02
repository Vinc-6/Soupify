package vinc.sechs.soupify_core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vinc.sechs.soupify_core.Utils.Inventory_Utils;
import vinc.sechs.soupify_core.settings.ColorSettings;

import java.util.Arrays;

public class SettingsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player)sender;
        player.openInventory(Inventory_Utils.CreateSettingsInv(player));

        return true;
    }


}
