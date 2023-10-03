package dev.binarycodee.core.shop.guis;

import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopGUI {

    public static void openShopGUI(Player player) {
        Inventory shopGUI = Bukkit.createInventory(null, 27, ChatUtils.getColoredText("&aShop"));

        ItemStack generale = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta generaleMeta = generale.getItemMeta();
        generaleMeta.setDisplayName(ChatUtils.getColoredText("&aGenerale"));
        generale.setItemMeta(generaleMeta);

        ItemStack pozioni = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta pozioniMeta = pozioni.getItemMeta();
        pozioniMeta.setDisplayName(ChatUtils.getColoredText("&aPotenziamenti"));
        pozioni.setItemMeta(pozioniMeta);

        ItemStack incantesimi = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta incantesimiMeta = incantesimi.getItemMeta();
        incantesimiMeta.setDisplayName(ChatUtils.getColoredText("&aIncantesimi"));
        incantesimi.setItemMeta(incantesimiMeta);

        shopGUI.setItem(10, generale);
        shopGUI.setItem(12, pozioni);
        shopGUI.setItem(14, incantesimi);

        player.openInventory(shopGUI);
    }
}