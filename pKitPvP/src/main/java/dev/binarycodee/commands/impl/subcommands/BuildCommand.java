package dev.binarycodee.commands.impl.subcommands;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.Subcommand;
import dev.binarycodee.data.PlayerData;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BuildCommand extends Subcommand {

    public BuildCommand() {
        super("kitpvp", "build", "kitpvp.commands.build", true);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());
        data.isBuilder = !data.isBuilder;

        sender.sendMessage(ChatUtils.getFormattedText("anti-build.build-mode-" + (data.isBuilder ? "on" : "off")));

        KitPvP.getDataManager().updateData(data);
    }
}
