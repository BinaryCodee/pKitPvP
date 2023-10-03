package dev.binarycodee.commands.impl;

import dev.binarycodee.commands.impl.subcommands.*;
import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.CommandHandler;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MainCommand extends KitPvPCommand {

    public MainCommand() {
        super(KitPvP.getInstance(), "kitpvp", "kitpvp.commands.admin", false);

        setNoSubCommandFoundMessage(ChatUtils.getFormattedText("admin.no-sub-command-found"));
        setNoPermissionMessage(ChatUtils.getColoredText("&fRunning &a&lpKitPvP v1.0.0-ALPHA &fby &a&l@BinaryCodee&e!"));

        CommandHandler.addSubCommand(this, new SetStatsCommand());
        CommandHandler.addSubCommand(this, new SetSpawnCommand());
        CommandHandler.addSubCommand(this, new SetBountyCommand());
        CommandHandler.addSubCommand(this, new BuildCommand());
        CommandHandler.addSubCommand(this, new AlertCommand());
        CommandHandler.addSubCommand(this, new ReloadCommand());
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        if (args.size() != 0) return false;

        KitPvP.getFileManager().getMessages().getStringList("admin.help-command")
                .forEach(msg -> sender.sendMessage(ChatUtils.getColoredText(msg)));
        return false;
    }

}
