package dev.binarycodee.commands.impl;

import dev.binarycodee.listeners.PlayerListener;
import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.data.PlayerData;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends KitPvPCommand {
    public SpawnCommand() {
        super(KitPvP.getInstance(), "spawn", "kitpvp.commands.spawn", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("spawn.no-permission"));
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        Location spawn = (Location) KitPvP.getFileManager().getConfig().get("spawn-location");

        if (spawn == null) {
            player.sendMessage(ChatUtils.getFormattedText("spawn.no-spawn-found"));
            return false;
        }

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());
        data.atSpawn = true;

        KitPvP.getDataManager().updateData(data);
        player.teleport(spawn);
        if (!PlayerListener.falldamage.contains(player)) {
            PlayerListener.falldamage.add(player);
        }
        return false;
    }
}
