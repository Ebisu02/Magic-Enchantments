/*
package nodropitems.nodropitems.customEnchants.Tasks;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CE_FreezingTask extends BukkitRunnable {
    private LivingEntity victim;

    public CE_FreezingTask(LivingEntity victim) {
        this.victim = victim;
    }

    @Override
    public void run() {
        if (victim.getHealth() > 1) {
            if (victim instanceof Player) {
                ((Player) victim).getPlayer().setWalkSpeed(((Player) victim).getWalkSpeed() - ((float) 0.25));
            }
            victim.setHealth(victim.getHealth() - 0.5);
            System.out.println((double)victim.getHealth());
        }
        else {
            if (victim instanceof Player) {
                ((Player) victim).getPlayer().setWalkSpeed(((Player) victim).getWalkSpeed() - ((float) 0.25));
            }
            System.out.println((double)victim.getHealth());
        }
    }
}
*/
