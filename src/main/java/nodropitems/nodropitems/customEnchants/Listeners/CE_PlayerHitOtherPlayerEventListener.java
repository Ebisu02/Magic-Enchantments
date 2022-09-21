package nodropitems.nodropitems.customEnchants.Listeners;

import nodropitems.nodropitems.CE_Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CE_PlayerHitOtherPlayerEventListener implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            // For Driven by the wind enchantment
            try {
                if (((Player) e.getDamager()).getEquipment().getBoots().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_drivenByTheWind)) {
                    ((Player) e.getDamager()).getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
                }
            }
            catch (Exception er) {
                // To Debug
                //er.printStackTrace();
            }
            // For Vampiric Enchant
            try {
                if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_vampiric)) {
                    // Do
                    //e.getDamager().sendMessage("TEST");
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Vampiric I")) {
                        double toHeal = e.getFinalDamage();
                        toHeal = toHeal * 0.1;
                        if (((Player) e.getDamager()).getHealth() + toHeal > ((Player) e.getDamager()).getMaxHealth())
                        {
                            ((Player) e.getDamager()).setHealth(((Player) e.getDamager()).getMaxHealth());
                        }
                        else {
                            ((Player) e.getDamager()).setHealth(((Player) e.getDamager()).getHealth() + toHeal);
                        }
                    }
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Vampiric II")) {
                        double toHeal = e.getFinalDamage();
                        toHeal = toHeal * 0.25;
                        if (((Player) e.getDamager()).getHealth() + toHeal > ((Player) e.getDamager()).getMaxHealth())
                        {
                            ((Player) e.getDamager()).setHealth(((Player) e.getDamager()).getMaxHealth());
                        }
                        else {
                            ((Player) e.getDamager()).setHealth(((Player) e.getDamager()).getHealth() + toHeal);
                        }
                    }
                }
            }
            catch (Exception er) {
                // To Debug
                //er.printStackTrace();
            }
            // For Poisoning Touch
            try {
                if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_poisoningTouch)) {
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Poisoning touch I")) {
                        if (e.getEntity() instanceof Player) {
                            Player victim = (Player) e.getEntity();
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 0));
                        }
                        else if (e.getEntity() instanceof Mob) {
                            //e.getDamager().sendMessage("TEST");
                            Mob victim = (Mob) e.getEntity();
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 0));
                        }
                        else if (e.getEntity() instanceof NPC) {
                            NPC victim = (NPC) e.getEntity();
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 0));
                        }
                    }
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Poisoning touch II")) {
                        if (e.getEntity() instanceof Player) {
                            Player victim = (Player) e.getEntity();
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 0));
                        }
                        else if (e.getEntity() instanceof Mob) {
                            Mob victim = (Mob) e.getEntity();
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 0));
                        }
                        else if (e.getEntity() instanceof NPC) {
                            NPC victim = (NPC) e.getEntity();
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 0));
                        }
                    }
                }
            }
            catch (Exception er) {
                // To Debug
                //er.printStackTrace();
            }
        }
    }
}
