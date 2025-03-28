package dev.binarycodee.commands.impl;

import dev.binarycodee.KitPvP;
import dev.binarycodee.commands.api.KitPvPCommand;
import dev.binarycodee.utils.ChatUtils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

public class PotionCommand extends KitPvPCommand {
    public PotionCommand() {
        super(KitPvP.getInstance(), "potion", "kitpvp.commands.potion", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {

        Player p = (Player) sender;
        ItemStack[] contents;
        int length = (contents = p.getInventory().getContents()).length;

        for(int k = 0; k < length; ++k) {
            ItemStack item = contents[k];
            if (item != null && item.getType() == Material.POTION) {
                Potion pot = Potion.fromItemStack(item);
                int amt = 0;
                ItemStack[] contents2;
                int times = (contents2 = p.getInventory().getContents()).length;

                int j;
                for(j = 0; j < times; ++j) {
                    ItemStack i = contents2[j];
                    if (i != null && i.getType() == Material.POTION) {
                        Potion pot2 = Potion.fromItemStack(i);
                        if (pot.getType() == pot2.getType() && pot.getEffects().equals(pot2.getEffects())) {
                            amt += i.getAmount();
                            p.getInventory().remove(i);
                        }
                    }
                }

                times = amt / 16;
                if (amt % 16 != 0) {
                    ++times;
                }

                for(j = 0; j < times; ++j) {
                    if (amt > 16) {
                        p.getInventory().addItem(new ItemStack[]{new ItemStack(Material.POTION, 16, item.getDurability())});
                    } else {
                        p.getInventory().addItem(new ItemStack[]{new ItemStack(Material.POTION, amt, item.getDurability())});
                    }

                    amt -= 16;
                }
            }
        }

        p.sendMessage(ChatUtils.getFormattedText("potion.sent"));
        return true;
    }



}
