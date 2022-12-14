package nodropitems.nodropitems.customEnchants.Listeners;

import nodropitems.nodropitems.CE_Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


public class CE_PlayerDeathEventListener implements Listener {
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent e) {
        // For Undroppable enchantment
        if (e.getEntity() instanceof Player) {
            e.setKeepInventory(true);
            Inventory inv = e.getEntity().getInventory();
            ArrayList<ItemStack> toDrop = new ArrayList<ItemStack>();
            ArrayList<ItemStack> toSave = new ArrayList<ItemStack>();
            for (ItemStack item: inv) {
                if (item != null) {
                    if (item.getItemMeta().getEnchants().containsKey(CE_Main.enchantment_undroppable)) {
                        toSave.add(item);
                    } else {
                        toDrop.add(item);
                    }
                }
            }
            e.getEntity().getPlayer().getInventory().clear();
            int counter = 0;
            for (ItemStack item: toSave) {
                e.getEntity().getInventory().setItem(counter, item);
                ++counter;
            }
            for (ItemStack item: toDrop) {
                e.getEntity().getPlayer().getWorld().dropItem(e.getEntity().getLocation(), item);
            }
        }
    }
}
