
package dev.binarycodee.listeners;

import dev.binarycodee.KitPvP;
import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomListener
        implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        double balance = KitPvP.getEconomy().getBalance(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (event.getItem() == null || event.getItem().getType() != Material.SUGAR) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
        this.removeSugarFromInventory(player);
        player.sendMessage(ChatUtils.getFormattedText("sugar.used"));

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerClaimNote(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getItemInHand();

        if (item == null || !KitPvP.isBanknote(item)) {
            return;
        }

        double amount = KitPvP.getBanknoteAmount(item);

        if (Double.compare(amount, 0) < 0) {
            return;
        }

        KitPvP.getEconomy().depositPlayer(player, amount);


        player.sendMessage(ChatUtils.getFormattedText("assegno.claim")
                .replaceAll("%soldi%", String.valueOf(amount)));


        if (item.getAmount() <= 1) {
            event.getPlayer().getInventory().removeItem(item);
        } else {
            item.setAmount(item.getAmount() - 1);
        }
    }



    private void removeSugarFromInventory(Player player) {
        player.getInventory().removeItem(new ItemStack[]{new ItemStack(Material.SUGAR, 1)});
    }
}
