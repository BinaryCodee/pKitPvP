package dev.binarycodee.commands.impl;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.utils.ChatUtils;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand
        extends KitPvPCommand {
    public DiscordCommand() {
        super(KitPvP.getInstance(), "discord", "kitpvp.commands.discord", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        Player player = (Player)sender;
        player.sendMessage(ChatUtils.getFormattedText("discord.message"));
        return false;
    }
}
