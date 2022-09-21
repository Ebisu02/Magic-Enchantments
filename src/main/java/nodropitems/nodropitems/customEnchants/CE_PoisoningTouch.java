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

public class CE_PoisoningTouch extends Enchantment implements CE_Executor {
    private final String Name = "Poisoning Touch";

    public CE_PoisoningTouch(String namespace) {
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
            item.addUnsafeEnchantment(CE_Main.enchantment_poisoningTouch, 1);
            meta.addEnchant(CE_Main.enchantment_poisoningTouch, 1, true);
            lore.add("Poisoning touch I");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        if (lvl.equalsIgnoreCase("2")) {
            item.addUnsafeEnchantment(CE_Main.enchantment_poisoningTouch, 2);
            meta.addEnchant(CE_Main.enchantment_poisoningTouch, 2, true);
            lore.add("Poisoning touch II");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }
}
