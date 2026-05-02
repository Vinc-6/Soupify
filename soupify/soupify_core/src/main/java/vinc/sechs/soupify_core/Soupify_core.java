package vinc.sechs.soupify_core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import vinc.sechs.soupify_core.Commands.*;
import vinc.sechs.soupify_core.Manager.MessageManager;
import vinc.sechs.soupify_core.Manager.ScoreboardManager;
import vinc.sechs.soupify_core.ServerBasic.Chat;
import vinc.sechs.soupify_core.ServerBasic.ServerJoin;
import vinc.sechs.soupify_core.ServerBasic.Settings;
import vinc.sechs.soupify_core.Tasks.NameTask;

public final class Soupify_core extends JavaPlugin {
    public static JavaPlugin instance;
    private static HikariDataSource dataSource;

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
    private BukkitTask taskName;


    @Override
    public void onEnable() {

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getWorlds().get(0).setGameRuleValue("announceAchievements", "false");

        instance = this;

        initDatenbank();
        initListener();
        initCommands();

        taskName = getServer().getScheduler().runTaskTimer(this, NameTask.getInstance(), 0, 0);
    }

    @Override
    public void onDisable() {
        if (taskName != null) {
            taskName.cancel();
        }
    }

    private void initListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ServerJoin(), this);
        pluginManager.registerEvents(new Chat(), this);
        pluginManager.registerEvents(new Settings(), this);
    }

    private void initCommands() {

        MessageManager messageManager = new MessageManager();
        getCommand("msg").setExecutor(new MsgCommand(messageManager));
        getCommand("r").setExecutor(new RCommand(messageManager));

        getCommand("settings").setExecutor(new SettingsCommand());

        getCommand("ranks").setExecutor(new RanksCommand());

        getCommand("l").setExecutor(new HubCommand());
        getCommand("hub").setExecutor(new HubCommand());

        RankCoammand rankCMD = new RankCoammand();

        getCommand("rank").setExecutor(rankCMD);
        getCommand("rank").setTabCompleter(rankCMD);
    }

    private void initDatenbank() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                "jdbc:mysql://localhost:3306/soupify?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        );
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("code");
        config.setPassword("Z^8U.jD-25*f?iMCuy09");

        dataSource = new HikariDataSource(config);
    }
}
