package nodropitems.nodropitems.customEnchants.Listeners;

import nodropitems.nodropitems.CE_Main;
//import nodropitems.nodropitems.customEnchants.Tasks.CE_FreezingTask;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import static nodropitems.nodropitems.CE_Utilities.getPlugin;

public class CE_PlayerHitOtherPlayerEventListener implements Listener {
    @EventHandler
    public void onPlayerHitWith_DrivenByTheWind(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            try {
                // For Driven by the wind enchantment
                if (((Player) e.getDamager()).getEquipment().getBoots().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_drivenByTheWind)) {
                    ((Player) e.getDamager()).getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
                }
            }
            catch (Exception er) {
                // DEBUG
                // er.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPlayerHitWith_Vampiric(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            try {
                // For Vampiric Enchant
                if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_vampiric)) {
                    // Do
                    //e.getDamager().sendMessage("TEST");
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Вампиризм I")) {
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
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Вампиризм II")) {
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
                // DEBUG
                // er.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPlayerHitWith_PoisonTouch(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            try {
                // For Poisoning Touch
                if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_poisoningTouch)) {
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Отравляющее касание I")) {
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
                    if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Отравляющее касание II")) {
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
                // DEBUG
                // er.printStackTrace();
            }
        }
    }

    //TODO: Need to rework it. Idk reason, but now it's not working, im waiting answer from spigot users on forum
    //Problem: 1. Effect not stacking (E.g. if u hit player once, u cant refresh effect duration)
    //Problem: 2. Incorrect damage encounter
/*    @EventHandler
    public void onPlayerHitWith_FreezingTouch(EntityDamageByEntityEvent e) {
        try {
            if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getEnchants().containsKey(CE_Main.enchantment_freezingTouch)) {
                if (((Player) e.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getLore().contains("Ледяное касание I")) {
                    LivingEntity victim = (LivingEntity) e.getEntity();
                    float movementSpeed = 1;
                    if (victim instanceof Player) {
                        movementSpeed = ((Player) victim).getWalkSpeed();
                    }
                    BukkitTask task = (BukkitTask) new CE_FreezingTask(victim).runTaskTimer(getPlugin(), 0L, 100L);
                    if (victim instanceof Player) {
                        ((Player) victim).setWalkSpeed(movementSpeed);
                    }
                }
            }
        } catch (Exception er) {
            // DEBUG
            // er.printStackTrace();
        }
    }*/
}
