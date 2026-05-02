package vinc.sechs.soupify_lobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Sound;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import vinc.sechs.soupify_lobby.LocationData;
import vinc.sechs.soupify_lobby.Settings.ColorSettings;
import vinc.sechs.soupify_lobby.Settings.NameSettings;
import vinc.sechs.soupify_lobby.Soupify_Lobby;
import vinc.sechs.soupify_lobby.commands.Utils;
import vinc.sechs.soupify_lobby.helper.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;

public class ConnectionListener implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            player.getPlayer().setAllowFlight(true);
        }

        Utils.tpToSpawn(player);

        player.setHealth(20);
        player.setSaturation(20);
        player.setExp(0);

        event.setJoinMessage(null);

        TextComponent clickableDiscord = new TextComponent(ColorSettings.firstColor + "discord.gg/soupify");
        clickableDiscord.setClickEvent(new ClickEvent(
                ClickEvent.Action.OPEN_URL,
                "https://discord.gg/Ywd6atpr3c"));

        TextComponent message1 = new TextComponent(ColorSettings.firstColor + "§lWelcome");
        TextComponent message2 = new TextComponent(" §fDiscord: ");
        message2.addExtra(clickableDiscord);

        player.spigot().sendMessage(message1);
        player.spigot().sendMessage(message2);

        setPlayerHotbar(player);

        // Titel leicht verzögert senden
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendTitle(ColorSettings.firstColor + NameSettings.serverName,
                        "§fThe Competitive Network");
            }
        }.runTaskLater(Soupify_Lobby.instance, 5L); // 5 Ticks = 0,25 Sekunden
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            if (event.isFlying()) {
                player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));

                // 🔊 Sound abspielen
                player.getWorld().playSound(
                        player.getLocation(),
                        Sound.ENDERDRAGON_WINGS,
                        1.0f,  // Lautstärke
                        1.0f   // Pitch
                );

                player.spigot().playEffect(
                        player.getLocation(),
                        org.bukkit.Effect.CLOUD,
                        0,
                        0,
                        0.2f,
                        0.2f,
                        0.2f,
                        0.01f,
                        20,
                        64
                );

                event.setCancelled(true);
            }
        }
    }

    private void setPlayerHotbar(Player player) {

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        ItemStack warps = new ItemStack(Material.COMPASS);
        ItemMeta metaWarp = warps.getItemMeta();
        metaWarp.setDisplayName(ColorSettings.firstColor + "Navigation");
        warps.setItemMeta(metaWarp);

        ItemStack settings = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta metaSettings = settings.getItemMeta();
        metaSettings.setDisplayName(ColorSettings.firstColor + "Settings");
        settings.setItemMeta(metaSettings);

        ItemStack server = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta1Server = server.getItemMeta();
        meta1Server.setDisplayName(ColorSettings.firstColor + "Spawn");
        server.setItemMeta(meta1Server);

        player.getInventory().setItem(0, warps);
        player.getInventory().setItem(7, settings);
        player.getInventory().setItem(8, server);
    }

}
