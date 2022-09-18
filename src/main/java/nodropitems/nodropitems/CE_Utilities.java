package nodropitems.nodropitems;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.HashMap;

public class CE_Utilities {
    private static CE_Main plugin;

    public static CE_Main getPlugin() {
        return plugin;
    }

    public static void onDisable_forEnchantments(Enchantment enchant) {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");
            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
            if (byKey.containsKey(enchant.getKey())) {
                byKey.remove(enchant.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");
            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);
            if (byName.containsKey(enchant.getName())) {
                byName.remove(enchant.getName());
            }
        } catch (Exception e) {
            return;
        }
    }

    public static void sendError(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Unknown command!");
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if (registered) {
            // Succes!
        }
    }
}
