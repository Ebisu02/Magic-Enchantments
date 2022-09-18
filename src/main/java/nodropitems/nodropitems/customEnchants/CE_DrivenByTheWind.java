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

public class CE_DrivenByTheWind extends Enchantment implements CE_Executor {
    private final String Name = "Driven by the wind";

    public CE_DrivenByTheWind(String namespace) {
        super(new NamespacedKey(getPlugin(), namespace));
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
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
        item.addUnsafeEnchantment(CE_Main.enchantment_drivenByTheWind, 1);
        meta.addEnchant(CE_Main.enchantment_drivenByTheWind, 1, true);
        lore.add("Driven by the wind I");
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
