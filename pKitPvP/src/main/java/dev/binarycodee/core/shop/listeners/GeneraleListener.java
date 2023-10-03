package dev.binarycodee.core.shop.listeners;

import dev.binarycodee.KitPvP;
import dev.binarycodee.core.shop.guis.pages.Generale;
import dev.binarycodee.utils.ChatUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GeneraleListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (event.getCurrentItem()==null)
            return;

        if (event.getClickedInventory().getTitle().equals(ChatUtils.getColoredText("&aGenerale"))) {
            event.setCancelled(true);
        }

        if (event.getView().getTitle().equals(ChatUtils.getColoredText("&aGenerale"))) {
            if (clickedItem != null && clickedItem.getType() == Material.DIAMOND_SWORD) {
                event.setCancelled(true);

                Economy economy = KitPvP.getEconomy();
                if (economy != null) {
                    double price = 700.0;

                    if (economy.has(player, price)) {
                        economy.withdrawPlayer(player, price);
                        player.sendMessage(ChatUtils.getColoredText("&fHai acquistato correttamente &ax1 Spada in Diamante&f per &a" + price + " Soldi&e!"));

                        ItemStack purchasedItem = new ItemStack(Material.DIAMOND_SWORD);
                        player.getInventory().addItem(purchasedItem);
                    } else {
                        player.sendMessage(ChatUtils.getColoredText("&cNon hai abbastanza soldi per acquistare questo oggetto!"));
                    }
                } else {
                    player.sendMessage(ChatUtils.getColoredText("&cSi è verificato un errore durante l'acquisto. Riprova più tardi."));
                }
            }
            if (clickedItem != null && clickedItem.getType() == Material.FISHING_ROD) {
                event.setCancelled(true);

                Economy economy = KitPvP.getEconomy();
                if (economy != null) {
                    double price = 400.0;

                    if (economy.has(player, price)) {
                        economy.withdrawPlayer(player, price);
                        player.sendMessage(ChatUtils.getColoredText("&fHai acquistato correttamente &ax1 Canna da Pesca&f per &a" + price + " Soldi&e!"));

                        ItemStack purchasedItem = new ItemStack(Material.FISHING_ROD);
                        player.getInventory().addItem(purchasedItem);
                    } else {
                        player.sendMessage(ChatUtils.getColoredText("&cNon hai abbastanza soldi per acquistare questo oggetto!"));
                    }
                } else {
                    player.sendMessage(ChatUtils.getColoredText("&cSi è verificato un errore durante l'acquisto. Riprova più tardi."));
                }
            }
        }
    }
}