package dev.binarycodee.core.shop.listeners;

import dev.binarycodee.core.shop.guis.ShopGUI;
import dev.binarycodee.core.shop.guis.pages.Generale;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (event.getCurrentItem()==null)
            return;

        if (event.getClickedInventory().getTitle().equals(ChatUtils.getColoredText("&aShop"))) {
            event.setCancelled(true);
        }

        if (event.getView().getTitle().equals(ChatUtils.getColoredText("&aShop"))) {
            if (clickedItem != null && clickedItem.getType() == Material.DIAMOND_SWORD) {
                Generale generale = new Generale();
                generale.openGenerale(player);
            }
        } else if (event.getView().getTitle().equals(ChatUtils.getColoredText("&aShop"))) {
            event.setCancelled(true);
        }
    }
}
