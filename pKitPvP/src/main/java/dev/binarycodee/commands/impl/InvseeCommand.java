package dev.binarycodee.commands.impl;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class InvseeCommand extends KitPvPCommand {
    public InvseeCommand() {
        super(KitPvP.getInstance(), "invsee", "kitpvp.commands.zinvsee", true);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        Player player = (Player)sender;
        if (!player.hasPermission("pkitpvp.commands.invsee")) {
            player.sendMessage(ChatUtils.getFormattedText("messages.unknowcommand"));
            return false;
        }
        if (args.size() < 1) {
            player.sendMessage(ChatUtils.getFormattedText("invsee.usage"));
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args.get(0));
        if (targetPlayer == null) {
            player.sendMessage(ChatUtils.getFormattedText("invsee.player-not-found"));
            return false;
        }

        Player viewer = (Player) sender;
        Inventory targetInventory = targetPlayer.getInventory();

        viewer.openInventory(targetInventory);
        viewer.sendMessage(ChatUtils.getFormattedText("invsee.sent"));

        return true;
    }
}
