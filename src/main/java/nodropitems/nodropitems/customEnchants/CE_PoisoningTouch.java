package nodropitems.nodropitems.customEnchants;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import static nodropitems.nodropitems.CE_Utilities.getPlugin;

public class CE_PoisoningTouch extends Enchantment {

    public CE_PoisoningTouch(String namespace) {
        super(new NamespacedKey(getPlugin(), namespace));
    }

    @Override
    public String getName() {
        return "Poisoning Touch";
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
}
