package dev.binarycodee.core.host.guis;

import dev.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HostGUI {

    public static void openHostGUI(Player player) {
        Inventory hostGUI = Bukkit.createInventory(null, 27, ChatUtils.getColoredText("&2Host"));

        ItemStack host1 = new ItemStack(Material.STICK);
        ItemMeta host1Meta = host1.getItemMeta();
        host1Meta.setDisplayName(ChatUtils.getColoredText("&2&lSUMO"));
        List<String> host1Lore = new ArrayList<>();
        host1Lore.add(ChatUtils.getColoredText("&f"));
        host1Lore.add(ChatUtils.getColoredText("&aInformazioni ->"));
        host1Lore.add(ChatUtils.getColoredText("&8* &fEvento creato appositamente per i Giocatori"));
        host1Lore.add(ChatUtils.getColoredText("&8* &fche hanno il rank &2&lPHYTON&e!"));
        host1Lore.add(ChatUtils.getColoredText("&f"));
        host1Lore.add(ChatUtils.getColoredText("&aObbiettivo ->"));
        host1Lore.add(ChatUtils.getColoredText("&8* &fButtare giù il tuo nemico dalla"));
        host1Lore.add(ChatUtils.getColoredText("&8* &fpiattaforma per vincere i &2&lROUND&e!"));
        host1Lore.add(ChatUtils.getColoredText("&f"));
        host1Meta.setLore(host1Lore);
        host1.setItemMeta(host1Meta);

        ItemStack host2 = new ItemStack(Material.BOW);
        ItemMeta host2Meta = host2.getItemMeta();
        host2Meta.setDisplayName(ChatUtils.getColoredText("&2&lBOW BOOST"));
        List<String> host2Lore = new ArrayList<>();
        host2Lore.add(ChatUtils.getColoredText("&f"));
        host2Lore.add(ChatUtils.getColoredText("&cAttualmente non ci sono Informazioni o Obbiettivi"));
        host2Lore.add(ChatUtils.getColoredText("&csu questo Host-Event!"));
        host2Lore.add(ChatUtils.getColoredText("&f"));
        host2Meta.setLore(host2Lore);
        host2.setItemMeta(host2Meta);

        hostGUI.setItem(12, host1);
        hostGUI.setItem(14, host2);

        player.openInventory(hostGUI);
    }
}