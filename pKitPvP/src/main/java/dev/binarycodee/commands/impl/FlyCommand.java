
package dev.binarycodee.commands.impl;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.utils.ChatUtils;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand
        extends KitPvPCommand {
    public FlyCommand() {
        super(KitPvP.getInstance(), "fly", "kitpvp.commands.fly", true);
    }

    public boolean execute(CommandSender sender, List<String> args) {

        Player player = (Player) sender;

        if (!player.hasPermission("pkitpvp.commands.fly")) {
            player.sendMessage(ChatUtils.getFormattedText("messages.unknowcommand"));
            return false;
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ChatUtils.getFormattedText("fly.disabled"));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(ChatUtils.getFormattedText("fly.enabled"));
        }
        return false;
    }

}
