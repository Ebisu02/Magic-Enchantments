package nodropitems.nodropitems;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
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

public final class Nodropitems extends JavaPlugin implements Listener, TabExecutor {

    @Override
    public void onEnable() {
        CustomEnchants.register();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getConsoleSender().sendMessage("[CustomEnchantments]: Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getServer().getConsoleSender().sendMessage("[CustomEnchantments]: Plugin is disabled!");
        saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (sender.isOp()) {
            Player player = (Player) sender;
            ItemStack item = ((Player) sender).getItemInHand();
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<String>();
            if (label.equalsIgnoreCase("cenchant")) {
                if (args.length > 2 || args.length == 0) {
                    sendError(sender);
                }
                // Enchantments
                if (args[0].equalsIgnoreCase("nodrop")) {
                    if (args.length == 1) {
                        customEnchantment_Undroppable(player, item, meta, lore);
                    }
                    else {
                        sendError(sender);
                    }
                }
                else if (args[0].equalsIgnoreCase("poison")) {
                    customEnchantment_PoisoningTouch(player, item, meta, lore, args[1]);
                }
                else if (args[0].equalsIgnoreCase("vampiric")) {
                    customEnchantment_Vampiric(player, item, meta, lore, args[1]);
                }
                else if (args[0].equalsIgnoreCase("driven_by_the_wind")) {
                    if (args.length == 1) {
                        customEnchantment_DrivenByTheWind(player, item, meta, lore);
                    }
                    else {
                        sendError(sender);
                    }
                }
                else {
                    sendError(sender);
                }
                sender.sendMessage(ChatColor.GREEN + "Your item has been enchanted!");
            }
/*          getServer().getConsoleSender().sendMessage(args[0] + "1");
            getServer().getConsoleSender().sendMessage(cmd.toString());
            getServer().getConsoleSender().sendMessage(label);*/
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
            e.setKeepInventory(true);
            Inventory inv = e.getEntity().getInventory();
            ArrayList<ItemStack> toDrop = new ArrayList<ItemStack>();
            ArrayList<ItemStack> toSave = new ArrayList<ItemStack>();
            for (ItemStack item: inv) {
                if (item != null) {
                    if (item.getItemMeta().getLore().contains(ChatColor.DARK_RED + "Undroppable")) {
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

    public void customEnchantment_Undroppable(Player player, ItemStack item, ItemMeta meta, List<String> lore) {
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

    public void customEnchantment_DrivenByTheWind(Player player, ItemStack item, ItemMeta meta, List<String> lore) {
        item.addUnsafeEnchantment(CustomEnchants.DRIVEN_BY_THE_WIND, 2);
        lore.add(ChatColor.GREEN + "Driven by the wind");
        if (meta.hasLore()) {
            for (String l : meta.getLore()) {
                lore.add(l);
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public void customEnchantment_PoisoningTouch(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
        if (lvl.equalsIgnoreCase("1")) {
            item.addUnsafeEnchantment(CustomEnchants.POISON_TOUCH, 1);
            lore.add(ChatColor.GREEN + "Poisoning I");
            if (meta.hasLore()) {
                for (String l : meta.getLore()) {
                    lore.add(l);
                }
            }
        }
        if (lvl.equalsIgnoreCase("2")) {
            item.addUnsafeEnchantment(CustomEnchants.POISON_TOUCH, 2);
            lore.add(ChatColor.GREEN + "Poisoning II");
            if (meta.hasLore()) {
                for (String l: meta.getLore()) {
                    lore.add(l);
                }
            }
        }
    }

    public void customEnchantment_Vampiric(Player player, ItemStack item, ItemMeta meta, List<String> lore, String lvl) {
        if (lvl.equalsIgnoreCase("1")) {
            item.addUnsafeEnchantment(CustomEnchants.VAMPRIC, 1);
            lore.add(ChatColor.GREEN + "Vampiric I");
            if (meta.hasLore()) {
                for (String l : meta.getLore()) {
                    lore.add(l);
                }
            }
        }
        if (lvl.equalsIgnoreCase("2")) {
            item.addUnsafeEnchantment(CustomEnchants.VAMPRIC, 2);
            lore.add(ChatColor.GREEN + "Vampiric II");
            if (meta.hasLore()) {
                for (String l: meta.getLore()) {
                    lore.add(l);
                }
            }
        }
    }

    public void sendError(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Unknown command!");
    }
}
