package nodropitems.nodropitems.customEnchants.Listeners;

import nodropitems.nodropitems.CE_Main;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CE_PlayerHitOtherPlayerEventListener implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        // For Driven by the wind enchantment
        if (e.getDamager() instanceof Player) {
            Bukkit.getServer().getConsoleSender().sendMessage("Test");
        }
    }
}
