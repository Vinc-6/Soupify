package vinc.sechs.soupify_lobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import vinc.sechs.soupify_lobby.Tasks.ScoreboardTask;
import vinc.sechs.soupify_lobby.commands.NPCPlugin;
import vinc.sechs.soupify_lobby.commands.SetSpawnCommand;
import vinc.sechs.soupify_lobby.commands.SpawnCommand;
import vinc.sechs.soupify_lobby.commands.TestCommand;
import vinc.sechs.soupify_lobby.listeners.*;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;


public final class Soupify_Lobby extends JavaPlugin {

    public static JavaPlugin instance;

    private BukkitTask taskSB;
    private BukkitTask taskSBEffect;


    @Override
    public void onEnable() {
        instance = this;
        initListner();

        Bukkit.getWorlds().forEach(world ->
                world.setDifficulty(Difficulty.PEACEFUL)
        );

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getWorlds().get(0).setGameRuleValue("doMobSpawning", "false");


        taskSB = getServer().getScheduler().runTaskTimer(this, ScoreboardTask.getInstance(), 0, 0);
    }

    @Override
    public void onDisable() {
        if (taskSB != null) {
            taskSB.cancel();
        }

        if (taskSBEffect != null) {
            taskSBEffect.cancel();
        }
    }

    private void initListner() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new WeatherListener(), this);

        getCommand("test").setExecutor(new TestCommand());
        getCommand("spawnnpc").setExecutor(new NPCPlugin());
        getCommand("setlobbyspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }
}
