package dev.binarycodee.commands.impl;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.data.PlayerData;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.List;

public class StatsCommand extends KitPvPCommand {
    public StatsCommand() {
        super(KitPvP.getInstance(), "stats", "kitpvp.commands.stats", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        String playerName;
        if (args.isEmpty()) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                playerName = player.getName();
            } else {
                sender.sendMessage(ChatUtils.getConfig("stats.no-player"));
                return false;
            }
        } else {
            playerName = args.get(0);
        }

        Player player = KitPvP.getInstance().getServer().getPlayer(playerName);
        if (player != null) {
            PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());
            sendStatsMessage(sender, data);
        } else {
            sender.sendMessage(ChatUtils.getConfig("stats.player-not-found").replaceAll("%player%", playerName));
        }

        return false;
    }

    private void sendStatsMessage(CommandSender sender, PlayerData data) {
        FileConfiguration config = KitPvP.getFileManager().getConfig();
        List<String> messages = config.getStringList("stats.lines");
        String playerName = data.getLastPlayer() != null ? data.getLastPlayer().getName() : "";
        sender.sendMessage(ChatUtils.getColoredText(config.getString("stats.header").replaceAll("%player%", playerName)));
        messages.forEach(line -> {
            String formattedLine = line
                    .replaceAll("%kills%", String.valueOf(data.kills))
                    .replaceAll("%deaths%", String.valueOf(data.deaths))
                    .replaceAll("%kd%", String.format("%.2f", data.getKD()))
                    .replaceAll("%bounty%", String.valueOf(data.getBounty()))
                    .replaceAll("%streak%", String.valueOf(data.streak))
                    .replaceAll("%combat%", data.inCombat ? ChatUtils.getConfig("stats.combat-true") : ChatUtils.getConfig("stats.combat-false"))
                    .replaceAll("%player%", playerName);
            sender.sendMessage(ChatUtils.getColoredText(formattedLine));
        });
    }
}