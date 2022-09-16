package nodropitems.nodropitems;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Nodropitems extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        CustomEnchants.register();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        //this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.isOp()) {
            Player player = (Player) sender;
            ItemStack item = ((Player) sender).getItemInHand();
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<String>();
            // #################
            // #################
            // ###NODROP########
            // #################
            // #################
            if (label.equalsIgnoreCase("cenchant nodrop")) {
                if (!(sender instanceof Player)) {
                    return true;
                }
                item.addUnsafeEnchantment(CustomEnchants.NODROP, 1);
                lore.add(ChatColor.DARK_RED + "Undroppable");
                if (meta.hasLore()) {
                    for (String l : meta.getLore()) {
                        lore.add(l);
                    }
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
            // #################
            // #################
            // ###POISON########
            // #################
            // #################
            if (label.equalsIgnoreCase("cenchant poison 1"))
            {
                if (!(sender instanceof Player)) {
                    return true;
                }
                item.addUnsafeEnchantment(CustomEnchants.POISON_TOUCH, 1);
                lore.add(ChatColor.DARK_GREEN + "Poisoning touch I");
                if (meta.hasLore()) {
                    for (String l: meta.getLore()) {
                        lore.add(l);
                    }
                }
            }
            if (label.equalsIgnoreCase("cenchant poison 2"))
            {
                if (!(sender instanceof Player)) {
                    return true;
                }
                item.addUnsafeEnchantment(CustomEnchants.POISON_TOUCH, 2);
                lore.add(ChatColor.DARK_GREEN + "Poisoning touch II");
                if (meta.hasLore()) {
                    for (String l: meta.getLore()) {
                        lore.add(l);
                    }
                }
            }
            // #################
            // #################
            // ###VAMPIRIC######
            // #################
            // #################
            if (label.equalsIgnoreCase("cenchant vampiric 1")) {
                if (!(sender instanceof Player)) {
                    return true;
                }
                item.addUnsafeEnchantment(CustomEnchants.VAMPRIC, 1);
                lore.add(ChatColor.DARK_GREEN + "Vampiric I");
                if (meta.hasLore()) {
                    for (String l: meta.getLore()) {
                        lore.add(l);
                    }
                }
            }
            if (label.equalsIgnoreCase("cenchant vampiric 2")) {
                if (!(sender instanceof Player)) {
                    return true;
                }
                item.addUnsafeEnchantment(CustomEnchants.VAMPRIC, 2);
                lore.add(ChatColor.DARK_GREEN + "Vampiric II");
                if (meta.hasLore()) {
                    for (String l: meta.getLore()) {
                        lore.add(l);
                    }
                }
            }
            // ##################
            // ##################
            // DRIVEN BY THE WIND
            // ##################
            // ##################
            if (label.equalsIgnoreCase("cenchant driven by the wind")) {
                if (!(sender instanceof Player)) {
                    return true;
                }
                item.addUnsafeEnchantment(CustomEnchants.DRIVEN_BY_THE_WIND, 2);
                lore.add(ChatColor.DARK_GREEN + "Driven by the wind");
                if (meta.hasLore()) {
                    for (String l: meta.getLore()) {
                        lore.add(l);
                    }
                }
            }
            sender.sendMessage("Success!");
            return true;
        }
        else {
            sender.sendMessage("You don't have permission to do that!");
        }
        return false;
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            //this.getServer().broadcastMessage("TestTestTestTestTestTestTestTestTest");
            e.setKeepInventory(true);
            Inventory inv = e.getEntity().getInventory();
            ArrayList<ItemStack> toDrop = new ArrayList<ItemStack>();
            ArrayList<ItemStack> toSave = new ArrayList<ItemStack>();
            for (ItemStack item: inv) {
                if (item != null) {
                    if (item.getEnchantments().containsKey(CustomEnchants.NODROP)) {
                        toSave.add(item);
                    } else {
                        toDrop.add(item);
                    }
                }
            }
            //this.getServer().broadcastMessage("Hmmmzzz");
            //this.getServer().broadcastMessage("Hmmm0");
            e.getEntity().getPlayer().getInventory().clear();
            int counter = 0;
            //this.getServer().broadcastMessage("Hmmm1");
            for (ItemStack item: toSave) {
                e.getEntity().getInventory().setItem(counter, item);
                ++counter;
            }
            //this.getServer().broadcastMessage("Hmmm2");
            for (ItemStack item: toDrop) {
                e.getEntity().getPlayer().getWorld().dropItem(e.getEntity().getLocation(), item);
            }
            //target.getInventory().clear();
            // Removing Extra items from player inventory
            //this.getServer().broadcastMessage("Hmmm3");
            //e.getEntity().getPlayer().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.GOLD_INGOT));
        }
    }
}
