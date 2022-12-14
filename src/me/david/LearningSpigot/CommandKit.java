package me.david.LearningSpigot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CommandKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            //give items
            ItemStack boat = new ItemStack(Material.ACACIA_BOAT);
            ItemStack bricks = new ItemStack(Material.BRICK);
            ItemStack netherite = new ItemStack(Material.NETHERITE_BLOCK, 10); //alt way
            bricks.setAmount(200);

            player.getInventory().addItem(bricks, boat, netherite);
            ItemStack award = new ItemStack(Material.TOTEM_OF_UNDYING);
            award.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            ItemMeta awardData = award.getItemMeta();
            awardData.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            String loreList[] = new String[3];
            loreList[0] = (ChatColor.GOLD + "Founder's Item: Learning Spigot");
            loreList[1] = (ChatColor.GOLD + "Awarded to: Evangeline_Samos who developed this plugin.");
            loreList[2] = (ChatColor.GOLD + "This award is highly prestigious\n only a select few has the honor of receiving it.");
            awardData.setLore(Arrays.asList(loreList));
            awardData.setDisplayName( ChatColor.RED + "Plugin Development - Learning Spigot - " + player.getName());
            award.setItemMeta(awardData);

            player.getInventory().addItem(award);

            ItemStack weapon = new ItemStack(Material.STICK);
            weapon.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
            ItemMeta weaponData = weapon.getItemMeta();
            String loreListWeapon[] = new String[3];
            loreListWeapon[0] = (ChatColor.DARK_PURPLE + "Weapon of Back de Knock");
            loreListWeapon[1] = (ChatColor.DARK_GREEN + "Can knockback players a great distance.");
            weaponData.setLore(Arrays.asList(loreListWeapon));
            weaponData.setDisplayName(ChatColor.GOLD + "Plugin Development - Learning Spigot - Knockback Weapon");
            weapon.setItemMeta(weaponData);
            player.getInventory().addItem(weapon);
        }
        return true;
    }


}
