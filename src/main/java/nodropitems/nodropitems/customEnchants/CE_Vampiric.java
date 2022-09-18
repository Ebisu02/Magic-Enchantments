package nodropitems.nodropitems.customEnchants;

import nodropitems.nodropitems.CE_Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static nodropitems.nodropitems.CE_Utilities.getPlugin;

public class CE_Vampiric extends Enchantment implements CE_Executor {
    private final String Name = "Vampiric";

    public CE_Vampiric(String namespace) {
        super(new NamespacedKey(getPlugin(), namespace));
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }

    @Override
    public void enchant(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
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
