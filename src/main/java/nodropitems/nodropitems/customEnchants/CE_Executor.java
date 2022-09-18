package nodropitems.nodropitems.customEnchants;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface CE_Executor {
    public void enchant(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl);
}
