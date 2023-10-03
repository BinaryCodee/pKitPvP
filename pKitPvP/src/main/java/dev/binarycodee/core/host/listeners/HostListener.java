package dev.binarycodee.core.host.listeners;

import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class HostListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (event.getCurrentItem() == null)
            return;

        if (event.getClickedInventory().getTitle().equals(ChatUtils.getColoredText("&2Host"))) {
            event.setCancelled(true);
        }

        if (event.getView().getTitle().equals(ChatUtils.getColoredText("&2Host"))) {
            if (clickedItem != null && (clickedItem.getType() == Material.STICK || clickedItem.getType() == Material.BOW)) {
                player.closeInventory();
                player.sendMessage(ChatUtils.getColoredText("&4&lERRORE&6!!! &cQuesto &6Host-Event&c è in sviluppo!"));
                event.setCancelled(true);
            }
        } else if (event.getView().getTitle().equals(ChatUtils.getColoredText("&2Host"))) {
            event.setCancelled(true);
        }
    }
}
