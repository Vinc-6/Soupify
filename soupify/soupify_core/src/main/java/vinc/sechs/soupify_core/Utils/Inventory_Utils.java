package vinc.sechs.soupify_core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vinc.sechs.soupify_core.settings.ColorSettings;

import java.util.Arrays;

public class Inventory_Utils {

    public static Inventory CreateSettingsInv(Player player) {

        Inventory inv = Bukkit.createInventory(null, 9*3, "Settings");

        ItemStack color = new ItemStack(Material.PAPER);
        ItemMeta colorMeta = color.getItemMeta();
        colorMeta.setDisplayName(ColorSettings.firstColor + "§lChat Color");
        colorMeta.setLore(Arrays.asList(
                "§7Change the color of",
                "§7your chat messages."
        ));
        color.setItemMeta(colorMeta);

        inv.setItem(10,color);

        fillInventoryWithBorder(inv);

        return inv;
    }
    public static Inventory GetColourInv(Player player) {

        Inventory inv = Bukkit.createInventory(null, 9*4, "Chat Color");

        ItemStack blue = new ItemStack(Material.INK_SACK, 1, (short) 12);
        ItemMeta blueMeta = blue.getItemMeta();
        blueMeta.setDisplayName("§9Blue");
        blue.setItemMeta(blueMeta);

        ItemStack darkBlue = new ItemStack(Material.INK_SACK, 1, (short) 4);
        ItemMeta darkBlueMeta = darkBlue.getItemMeta();
        darkBlueMeta.setDisplayName("§1Dark Blue");
        darkBlue.setItemMeta(darkBlueMeta);

        ItemStack green = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta greenMeta = green.getItemMeta();
        greenMeta.setDisplayName("§aGreen");
        green.setItemMeta(greenMeta);

        ItemStack darkGreen = new ItemStack(Material.INK_SACK, 1, (short) 2);
        ItemMeta darkGreenMeta = darkGreen.getItemMeta();
        darkGreenMeta.setDisplayName("§2Dark Green");
        darkGreen.setItemMeta(darkGreenMeta);

        ItemStack cyan = new ItemStack(Material.DIAMOND);
        ItemMeta cyanMeta = cyan.getItemMeta();
        cyanMeta.setDisplayName("§bCyan");
        cyan.setItemMeta(cyanMeta);

        ItemStack darkCyan = new ItemStack(Material.INK_SACK, 1, (short) 6);
        ItemMeta darkCyanMeta = darkCyan.getItemMeta();
        darkCyanMeta.setDisplayName("§3Dark Cyan");
        darkCyan.setItemMeta(darkCyanMeta);

        ItemStack red = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta redMeta = red.getItemMeta();
        redMeta.setDisplayName("§cRed");
        red.setItemMeta(redMeta);

        ItemStack darkRed = new ItemStack(Material.REDSTONE);
        ItemMeta darkRedMeta = darkRed.getItemMeta();
        darkRedMeta.setDisplayName("§4Dark Red");
        darkRed.setItemMeta(darkRedMeta);

        ItemStack pink = new ItemStack(Material.INK_SACK, 1, (short) 13);
        ItemMeta pinkMeta = pink.getItemMeta();
        pinkMeta.setDisplayName("§dPink");
        pink.setItemMeta(pinkMeta);

        ItemStack purple = new ItemStack(Material.INK_SACK, 1, (short) 5);
        ItemMeta purpleMeta = purple.getItemMeta();
        purpleMeta.setDisplayName("§5Purple");
        purple.setItemMeta(purpleMeta);

        ItemStack orange = new ItemStack(Material.INK_SACK, 1, (short) 14);
        ItemMeta orangeMeta = orange.getItemMeta();
        orangeMeta.setDisplayName("§6Orange");
        orange.setItemMeta(orangeMeta);

        ItemStack yellow = new ItemStack(Material.INK_SACK, 1, (short) 11);
        ItemMeta yellowMeta = yellow.getItemMeta();
        yellowMeta.setDisplayName("§eYellow");
        yellow.setItemMeta(yellowMeta);

        ItemStack white = new ItemStack(Material.INK_SACK, 1, (short) 15);
        ItemMeta whiteMeta = white.getItemMeta();
        whiteMeta.setDisplayName("§fWhite");
        white.setItemMeta(whiteMeta);

        ItemStack gray = new ItemStack(Material.INK_SACK, 1, (short) 8);
        ItemMeta grayMeta = gray.getItemMeta();
        grayMeta.setDisplayName("§8Gray");
        gray.setItemMeta(grayMeta);

        inv.setItem(10, blue);
        inv.setItem(11, green);
        inv.setItem(12, cyan);
        inv.setItem(13, red);
        inv.setItem(14, pink);
        inv.setItem(15, yellow);
        inv.setItem(16, white);
        inv.setItem(19, darkBlue);
        inv.setItem(20, darkGreen);
        inv.setItem(21, darkCyan);
        inv.setItem(22, darkRed);
        inv.setItem(23, purple);
        inv.setItem(24, orange);
        inv.setItem(25, gray);

        ItemStack back = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§c§lBack");
        back.setItemMeta(backMeta);

        inv.setItem(0, back);

        fillInventoryWithBorder(inv);
        return inv;
    }

    private static void fillInventoryWithBorder(Inventory inv) {
        int size = inv.getSize();
        int rows = size / 9;

        ItemStack borderItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta borderItemMeta = borderItem.getItemMeta();
        borderItemMeta.setDisplayName(" ");
        borderItem.setItemMeta(borderItemMeta);

        ItemStack fillerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fillerItemMeta = fillerItem.getItemMeta();
        fillerItemMeta.setDisplayName(" ");
        fillerItem.setItemMeta(fillerItemMeta);

        for (int i = 0; i < size; i++) {
            if (inv.getItem(i) != null) continue; // Nur leere Slots füllen

            int row = i / 9;
            int col = i % 9;

            boolean isBorder = (row == 0 || row == rows - 1 || col == 0 || col == 8);

            if (isBorder) {
                inv.setItem(i, borderItem);
            } else {
                inv.setItem(i, fillerItem);
            }
        }
    }
}
