package dev.binarycodee.commands.impl;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AssegnoCommand extends KitPvPCommand {
    public AssegnoCommand() {
        super(KitPvP.getInstance(), "assegno", "kitpvp.commands.assegno", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.size() >= 1) {
                String numero = args.get(0);


                if (!isInteger(numero) || Integer.parseInt(numero) <= 0) {
                    player.sendMessage(ChatUtils.getFormattedText("assegno.invalid-number"));
                    return false;
                }

                int price = Integer.parseInt(numero);
                double balance = KitPvP.getEconomy().getBalance(player);

                if (balance < price) {
                    player.sendMessage(ChatUtils.getFormattedText("assegno.not-enough-money"));
                    return false;
                }

                ItemStack banknote = KitPvP.createBanknote(player.getName(), Double.parseDouble(numero));

                KitPvP.getEconomy().withdrawPlayer(player, price);
                player.getInventory().addItem(banknote);
                player.sendMessage(ChatUtils.getFormattedText("assegno.sent")
                        .replaceAll("%soldi%", String.valueOf(numero)));
                return true;
            } else {
                player.sendMessage(ChatUtils.getFormattedText("assegno.usage"));
                return false;
            }
        } else {
            sender.sendMessage("Questo comando può essere eseguito solo da un giocatore.");
            return false;
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
