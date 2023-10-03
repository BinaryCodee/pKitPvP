package dev.binarycodee.commands.impl.subcommands;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.Subcommand;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand extends Subcommand {

    public ReloadCommand() {
        super("kitpvp", "reload", "kitpvp.commands.reload", false);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        KitPvP.getInstance().reloadConfiguration();
        sender.sendMessage(ChatUtils.getFormattedText("admin.plugin-reloaded"));
    }

}
