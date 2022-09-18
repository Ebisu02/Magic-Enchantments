package nodropitems.nodropitems;

import nodropitems.nodropitems.customEnchants.CE_DrivenByTheWind;
import nodropitems.nodropitems.customEnchants.CE_PoisoningTouch;
import nodropitems.nodropitems.customEnchants.CE_Undroppable;
import nodropitems.nodropitems.customEnchants.CE_Vampiric;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static nodropitems.nodropitems.CE_Utilities.*;

public final class CE_Main extends JavaPlugin {

    public static CE_Vampiric enchantment_vampiric;
    public static CE_Undroppable enchantment_undroppable;
    public static CE_DrivenByTheWind enchantment_drivenByTheWind;
    public static CE_PoisoningTouch enchantment_poisoningTouch;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new CE_Realisations(), this);
        Bukkit.getServer().getConsoleSender().sendMessage("[CustomEnchantments]: Plugin is enabled!");
        getCommand("cenchant").setExecutor(this);
        getCommand("cenchant").setTabCompleter(new CE_TabCompleter());

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
    }

    @Override
    public void onDisable() {
        // for enchantments shutdown logic
        onDisable_forEnchantments(enchantment_vampiric);
        onDisable_forEnchantments(enchantment_undroppable);
        onDisable_forEnchantments(enchantment_poisoningTouch);
        onDisable_forEnchantments(enchantment_drivenByTheWind);
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
                if (args.length != 2) {
                    sendError(sender);
                    return false;
                }
                // Enchantments

                // Undroppable
                if (args[0].equalsIgnoreCase("nodrop")) {
                    CE_CommandExecutors.customEnchantment_Undroppable(player, item, meta, lore, "1");
                }
                // Poison
                else if (args[0].equalsIgnoreCase("poison")) {
                    CE_CommandExecutors.customEnchantment_PoisoningTouch(player, item, meta, lore, args[1]);
                }
                // Vampiric
                else if (args[0].equalsIgnoreCase("vampiric")) {
                    CE_CommandExecutors.customEnchantment_Vampiric(player, item, meta, lore, args[1]);
                }
                // Driven by the wind
                else if (args[0].equalsIgnoreCase("driven_by_the_wind")) {
                    CE_CommandExecutors.customEnchantment_DrivenByTheWind(player, item, meta, lore, "1");
                }
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
            sender.sendMessage("You don't have permission to do that!");
        }
        return false;
    }
}
