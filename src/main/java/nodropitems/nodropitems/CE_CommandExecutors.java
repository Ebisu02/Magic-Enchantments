package nodropitems.nodropitems;

import nodropitems.nodropitems.customEnchants.CE_Vampiric;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CE_CommandExecutors {
    public static void customEnchantment_Undroppable(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
        item.addUnsafeEnchantment(CE_Main.enchantment_undroppable, 1);
        lore.add("Undroppable");
        if (meta.hasLore()) {
            for (String l : meta.getLore()) {
                lore.add(l);
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static void customEnchantment_DrivenByTheWind(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
        item.addUnsafeEnchantment(CE_Main.enchantment_drivenByTheWind, 1);
        lore.add("Driven by the wind");
        lore.add("\n");
        if (meta.hasLore()) {
            for (String l : meta.getLore()) {
                lore.add(l);
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static void customEnchantment_PoisoningTouch(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
        if (lvl.equalsIgnoreCase("1")) {
            item.addUnsafeEnchantment(CE_Main.enchantment_poisoningTouch, 1);
            lore.add("Poisoning Touch I");
            if (meta.hasLore()) {
                for (String l : meta.getLore()) {
                    lore.add(l);
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        if (lvl.equalsIgnoreCase("2")) {
            item.addUnsafeEnchantment(CE_Main.enchantment_poisoningTouch, 2);
            lore.add("Poisoning Touch II");
            if (meta.hasLore()) {
                for (String l: meta.getLore()) {
                    lore.add(l);
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public static void customEnchantment_Vampiric(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
        if (lvl.equalsIgnoreCase("1")) {
            item.addUnsafeEnchantment(CE_Main.enchantment_vampiric, 1);
            lore.add("Vampiric I");
            if (meta.hasLore()) {
                for (String l : meta.getLore()) {
                    lore.add(l);
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        if (lvl.equalsIgnoreCase("2")) {
            item.addUnsafeEnchantment(CE_Main.enchantment_vampiric, 2);
            lore.add("Vampiric II");
            if (meta.hasLore()) {
                for (String l: meta.getLore()) {
                    lore.add(l);
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }
}
