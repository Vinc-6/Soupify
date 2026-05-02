package vinc.sechs.soupify_core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import vinc.sechs.soupify_core.Utils.Rank_Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RankCommand implements CommandExecutor, TabCompleter {

    private final Rank_Utils rankUtils;

    private static final List<String> ACTIONS = List.of("set", "remove", "info");

    public RankCommand(Rank_Utils rankUtils) {
        this.rankUtils = rankUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player executer)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /rank <set|remove|info> <player> [rank]");
            return true;
        }

        String action = args[0].toLowerCase();

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found!");
            return true;
        }

        switch (action) {
            case "set":
                return handleSet(executer, target, args);
            case "remove":
                return handleRemove(executer, target);
            case "info":
                return handleInfo(executer, target);
            default:
                sender.sendMessage("§cInvalid action!");
                return true;
        }
    }

    private boolean handleSet(Player executer, Player target, String[] args) {

        if (!rankUtils.hasPermissions(Rank_Utils.Rank.Moderator, executer)) {
            executer.sendMessage(Rank_Utils.noPermission());
            return true;
        }

        if (args.length < 3) {
            executer.sendMessage("§cUsage: /rank set <player> <rank>");
            return true;
        }

        Rank_Utils.Rank rank = getRank(args[2]);

        if (rank == null) {
            executer.sendMessage("§cInvalid rank!");
            return true;
        }

        Rank_Utils.Rank executorRank = rankUtils.getRanked(executer);
        Rank_Utils.Rank targetRank = rankUtils.getRanked(target);

        if (rank == Rank_Utils.Rank.Default) {
            executer.sendMessage("§cUse /rank remove instead.");
            return true;
        }

        if (executorRank.getValue() <= rank.getValue()) {
            executer.sendMessage(Rank_Utils.noPermission());
            return true;
        }

        if (targetRank.getValue() >= executorRank.getValue()) {
            executer.sendMessage(Rank_Utils.noPermission());
            return true;
        }

        rankUtils.setRanked(target, rank);
        executer.sendMessage("§aRank updated!");
        return true;
    }

    private boolean handleRemove(Player executer, Player target) {

        if (!rankUtils.hasPermissions(Rank_Utils.Rank.Moderator, executer)) {
            executer.sendMessage(Rank_Utils.noPermission());
            return true;
        }

        Rank_Utils.Rank executorRank = rankUtils.getRanked(executer);
        Rank_Utils.Rank targetRank = rankUtils.getRanked(target);

        if (targetRank.getValue() >= executorRank.getValue()) {
            executer.sendMessage(Rank_Utils.noPermission());
            return true;
        }

        rankUtils.removeRanked(target);
        executer.sendMessage("§aRank removed!");
        return true;
    }

    private boolean handleInfo(Player executer, Player target) {
        Rank_Utils.Rank rank = rankUtils.getRanked(target);
        executer.sendMessage("§7Player §e" + target.getName() + " §7has rank §a" + rank.name());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if (args.length == 1) {
            return ACTIONS.stream()
                    .filter(a -> a.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (args.length == 2) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
            List<String> ranks = new ArrayList<>();
            for (Rank_Utils.Rank rank : Rank_Utils.Rank.values()) {
                ranks.add(rank.name().toLowerCase());
            }
            return ranks.stream()
                    .filter(r -> r.startsWith(args[2].toLowerCase()))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private Rank_Utils.Rank getRank(String input) {
        for (Rank_Utils.Rank rank : Rank_Utils.Rank.values()) {
            if (rank.name().equalsIgnoreCase(input)) {
                return rank;
            }
        }
        return null;
    }

}