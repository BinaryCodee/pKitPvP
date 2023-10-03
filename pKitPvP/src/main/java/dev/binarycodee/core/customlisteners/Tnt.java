package dev.binarycodee.core.customlisteners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Tnt implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInHand();

        if (heldItem.getType() == Material.TNT) {
            event.setCancelled(true);

            if (heldItem.getAmount() > 1) {
                heldItem.setAmount(heldItem.getAmount() - 1);
            } else {
                player.getInventory().remove(heldItem);
            }

            TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(60);

            Vector direction = player.getLocation().getDirection().normalize().multiply(1.1);
            tnt.setVelocity(direction);
        }
    }

    @EventHandler
    public void onTNTExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
            Block centerBlock = event.getLocation().getBlock();
            int radius = 5;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Block nearbyBlock = centerBlock.getRelative(x, y, z);
                        Material blockType = nearbyBlock.getType();
                        if (blockType == Material.WEB || blockType == Material.OBSIDIAN) {
                            nearbyBlock.setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamageByTnt(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION && event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            event.setCancelled(true);
        }
    }
}