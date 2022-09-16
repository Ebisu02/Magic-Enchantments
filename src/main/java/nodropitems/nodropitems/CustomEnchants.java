package nodropitems.nodropitems;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {
    public static final Enchantment NODROP = new EnchantWrapper("nodrop", "undroppable", 1);
    public static final Enchantment POISON_TOUCH = new EnchantWrapper("nodrop", "poisoning touch", 2); // poison 1 - 1, poison 2 - 2
    public static final Enchantment VAMPRIC = new EnchantWrapper("nodrop", "vampiric", 2); // 10% - 1, 20% - 2
    public static final Enchantment DRIVEN_BY_THE_WIND = new EnchantWrapper("nodrop", "driven by the wind", 1); // boots, +1st speed
    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.NODROP) &
                Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.POISON_TOUCH) &
                Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.VAMPRIC) &
                Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CustomEnchants.DRIVEN_BY_THE_WIND);
        if (!registered) {
            registerEnchantment(NODROP);
            registerEnchantment(POISON_TOUCH);
            registerEnchantment(VAMPRIC);
            registerEnchantment(DRIVEN_BY_THE_WIND);
        }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        }
        catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if (registered) {
            // do smth
        }
    }
}
