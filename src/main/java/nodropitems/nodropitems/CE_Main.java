package nodropitems.nodropitems;

import nodropitems.nodropitems.customEnchants.*;
import nodropitems.nodropitems.customEnchants.Listeners.CE_PlayerDeathEventListener;
import nodropitems.nodropitems.customEnchants.Listeners.CE_PlayerHitOtherPlayerEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static nodropitems.nodropitems.CE_Utilities.*;

public final class CE_Main extends JavaPlugin {

    //public static CE_FreezingTouch enchantment_freezingTouch;
    public static CE_Vampiric enchantment_vampiric;
    public static CE_Undroppable enchantment_undroppable;
    public static CE_DrivenByTheWind enchantment_drivenByTheWind;
    public static CE_PoisoningTouch enchantment_poisoningTouch;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setPlugin(this);
        // Freezing Touch
/*        enchantment_freezingTouch = new CE_FreezingTouch("freezing_touch");
        registerEnchantment(enchantment_freezingTouch);*/
        // vampiric
        enchantment_vampiric = new CE_Vampiric("vampiric");
        registerEnchantment(enchantment_vampiric);
        // undroppable
        enchantment_undroppable = new CE_Undroppable("undroppable");
        registerEnchantment(enchantment_undroppable);
        // Driven by the wind
        enchantment_drivenByTheWind = new CE_DrivenByTheWind("driven_by_the_wind");
        registerEnchantment(enchantment_drivenByTheWind);
        // Poisoning Touch
        enchantment_poisoningTouch = new CE_PoisoningTouch("poisoning_touch");
        registerEnchantment(enchantment_poisoningTouch);

        getCommand("cenchant").setTabCompleter(new CE_TabCompleter());
        this.getServer().getPluginManager().registerEvents(new CE_PlayerHitOtherPlayerEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new CE_PlayerDeathEventListener(), this);
        Bukkit.getServer().getConsoleSender().sendMessage("[CustomEnchantments]: Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        // for enchantments shutdown logic
        onDisable_forEnchantments(enchantment_vampiric);
        onDisable_forEnchantments(enchantment_undroppable);
        onDisable_forEnchantments(enchantment_poisoningTouch);
        onDisable_forEnchantments(enchantment_drivenByTheWind);
        //onDisable_forEnchantments(enchantment_freezingTouch);
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
            ArrayList<String> lore = new ArrayList<String>();
            if (label.equalsIgnoreCase("cenchant")) {
                if (args.length != 2) {
                    sendError(sender);
                    return false;
                }
                // Enchantments

                // Undroppable
                if (args[0].equalsIgnoreCase("nodrop") && args[1].equalsIgnoreCase("1")) {
                    if (!meta.getEnchants().containsKey(enchantment_undroppable)) {
                        enchantment_undroppable.enchant(player, item, meta, lore, args[1]);
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "This item already have this enchant!");
                    }
                }
                // Poison
                else if (args[0].equalsIgnoreCase("poison") && (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2"))) {
                    if (!meta.getEnchants().containsKey(enchantment_poisoningTouch)) {
                        enchantment_poisoningTouch.enchant(player, item, meta, lore, args[1]);
                    }
                }
                // Vampiric
                else if (args[0].equalsIgnoreCase("vampiric") && (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2"))) {
                    if (!meta.getEnchants().containsKey(enchantment_vampiric)) {
                        enchantment_vampiric.enchant(player, item, meta, lore, args[1]);
                    }
                }
                // Driven by the wind
                else if (args[0].equalsIgnoreCase("driven_by_the_wind") && args[1].equalsIgnoreCase("1")) {
                    if (!meta.getEnchants().containsKey(enchantment_drivenByTheWind)) {
                        enchantment_drivenByTheWind.enchant(player, item, meta, lore, args[1]);
                    }
                }
                // Freezing touch
/*                else if (args[0].equalsIgnoreCase("freezing") && (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2"))) {
                    if (!meta.getEnchants().containsKey(enchantment_freezingTouch)) {
                        enchantment_freezingTouch.enchant(player, item, meta, lore, args[1]);
                    }
                }*/
                // Send error if incorrect command
                else {
                    sendError(sender);
                    return false;
                }
                sender.sendMessage(ChatColor.GREEN + "Your item has been enchanted!");
            }
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
        }
        return false;
    }
}
