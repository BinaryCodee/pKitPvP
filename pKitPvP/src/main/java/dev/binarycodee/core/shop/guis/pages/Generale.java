package dev.binarycodee.core.shop.guis.pages;

import dev.binarycodee.KitPvP;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Generale {

    public static void openGenerale(Player player) {
        Inventory shopGUI = Bukkit.createInventory(null, 27, ChatUtils.getColoredText("&aGenerale"));

        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
        diamondSwordMeta.setDisplayName(ChatUtils.getColoredText("&2Spada in Diamante"));
        List<String> diamondSwordLore = new ArrayList<>();
        diamondSwordLore.add(ChatUtils.getColoredText("&aPrezzo: &f" + getPrice("diamondsword") + " soldi"));
        diamondSwordLore.add(ChatUtils.getColoredText("&7"));
        diamondSwordMeta.setLore(diamondSwordLore);
        diamondSword.setItemMeta(diamondSwordMeta);

        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta rodMeta = rod.getItemMeta();
        rodMeta.setDisplayName(ChatUtils.getColoredText("&2Canna da Pesca"));
        List<String> rodLore = new ArrayList<>();
        rodLore.add(ChatUtils.getColoredText("&aPrezzo: &f" + getPrice("rod") + " soldi"));
        rodMeta.setLore(rodLore);
        rod.setItemMeta(rodMeta);


        shopGUI.setItem(12, diamondSword);
        shopGUI.setItem(13, rod);

        player.openInventory(shopGUI);
    }

    private static double getPrice(String item) {
        if (item.equalsIgnoreCase("diamondsword")) {
            return 700.0;
        }
        if (item.equalsIgnoreCase("rod")) {
            return 400.0;
        }
        return 0.0;
    }

    private static String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return KitPvP.formatDouble(price);
    }
}