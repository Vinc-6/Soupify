package vinc.sechs.soupify_lobby.helper;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import vinc.sechs.soupify_lobby.Settings.ColorSettings;

import java.util.Arrays;


public class InventoryHelper extends JavaPlugin{

    static public Inventory GetWarpInventory()
    {
        Inventory inv = Bukkit.createInventory(  null, 9*3, "Navigation");

        ItemStack practice = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta practiceMeta = practice.getItemMeta();
        practiceMeta.setDisplayName(ColorSettings.firstColor + "§lPractice");
        practiceMeta.setLore(Arrays.asList(
                "§7Compete with the best players",
                "§7and practice your skills in",
                "§71v1, 2v2 & parties",
                "",
                "§fPlayers: "+ ColorSettings.firstColor +"0",
                "",
                "§a\u25BA Click to join"
                ));
        practice.setItemMeta(practiceMeta);

        ItemStack training = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta trainingMeta = training.getItemMeta();
        trainingMeta.setDisplayName(ColorSettings.firstColor + "§lTraining");
        trainingMeta.setLore(Arrays.asList(
                "§7Offers a variety of challenges",
                "§7to train you souping skills.",
                "§7Suitable for every player.",
                "",
                "§fPlayers: "+ ColorSettings.firstColor +"0",
                "",
                "§a\u25BA Click to join"
        ));
        training.setItemMeta(trainingMeta);

        ItemStack caveffa = new ItemStack(Material.STONE);
        ItemMeta caveffaMeta = caveffa.getItemMeta();
        caveffaMeta.setDisplayName(ColorSettings.firstColor + "§lCaveFFA");
        caveffaMeta.setLore(Arrays.asList(
                "§7CaveFFA is an intense FFA",
                "§7gamemode set in a cave.",
                "§7where players can choose kits ",
                "§7and battle each other",
                "",
                "§fPlayers: "+ ColorSettings.firstColor +"0",
                "",
                "§a\u25BA Click to join"
        ));
        caveffa.setItemMeta(caveffaMeta);

        ItemStack cxc = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta cxcMeta = cxc.getItemMeta();
        cxcMeta.setDisplayName(ColorSettings.firstColor + "§lClan X Clan");
        cxcMeta.setLore(Arrays.asList(
                "§7Clan vs. Clan lets clans battle",
                "§7each other for dominance",
                "§7in epic fights",
                "",
                "§fPlayers: "+ ColorSettings.firstColor +"0",
                "",
                "§a\u25BA Click to join"
        ));
        cxc.setItemMeta(cxcMeta);

        ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta blackMeta = black.getItemMeta();
        blackMeta.setDisplayName(" ");
        black.setItemMeta(blackMeta);

        ItemStack gray = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta grayMeta = black.getItemMeta();
        grayMeta.setDisplayName(" ");
        gray.setItemMeta(grayMeta);

        inv.setItem(10,practice);
        inv.setItem(12,caveffa);
        inv.setItem(14,cxc);
        inv.setItem(16,training);

        inv.setItem(0, black);
        inv.setItem(1, black);
        inv.setItem(2, black);
        inv.setItem(3, black);
        inv.setItem(4, black);
        inv.setItem(5, black);
        inv.setItem(6, black);
        inv.setItem(7, black);
        inv.setItem(8, black);
        inv.setItem(9, black);
        inv.setItem(11, gray);
        inv.setItem(13, gray);
        inv.setItem(15, gray);
        inv.setItem(17, black);
        inv.setItem(18, black);
        inv.setItem(19, black);
        inv.setItem(20, black);
        inv.setItem(21, black);
        inv.setItem(22, black);
        inv.setItem(23, black);
        inv.setItem(24, black);
        inv.setItem(25, black);
        inv.setItem(26, black);

        return  inv;
    }
}
