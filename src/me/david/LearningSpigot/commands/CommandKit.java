package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
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
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!(target == null)) {
                    giveItems(target);
                } else {
                    player.sendMessage(ChatColor.RED + "Unable to find player!");
                }
            } else {
                giveItems(player);
            }
            return true;
            //give items
        }
        return true;
    }

    private void giveItems(Player target) {
        ItemStack boat = new ItemStack(Material.VINDICATOR_SPAWN_EGG, 50);
        ItemStack nether = new ItemStack(Material.NETHERITE_BLOCK, 78);
        ItemStack netherite = new ItemStack(Material.NETHERITE_BLOCK, 10); //alt way
        nether.setAmount(200);

        target.getInventory().addItem(nether, boat, netherite);
        ItemStack award = new ItemStack(Material.TOTEM_OF_UNDYING);
        award.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta awardData = award.getItemMeta();
        awardData.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        String[] loreList = new String[3];
        loreList[0] = (ChatColor.GOLD + "Founder's Item: Learning Spigot");
        loreList[1] = (ChatColor.GOLD + "Awarded to: " + target.getName() + " who developed this plugin.");
        loreList[2] = (ChatColor.GOLD + "This award is highly prestigious\n only a select few has the honor of receiving it.");
        awardData.setLore(Arrays.asList(loreList));
        awardData.setDisplayName( ChatColor.RED + "Plugin Development - Learning Spigot - " + target.getName());
        award.setItemMeta(awardData);

        target.getInventory().addItem(award);

        ItemStack weapon = new ItemStack(Material.STICK);
        weapon.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
        ItemMeta weaponData = weapon.getItemMeta();
        String[] loreListWeapon = new String[3];
        loreListWeapon[0] = (ChatColor.DARK_PURPLE + "Weapon of Back de Knock");
        loreListWeapon[1] = (ChatColor.DARK_GREEN + "Can knockback players a great distance.");
        weaponData.setLore(Arrays.asList(loreListWeapon));
        weaponData.setDisplayName(ChatColor.GOLD + "Plugin Development - Learning Spigot - Knockback Weapon");
        weapon.setItemMeta(weaponData);
        target.getInventory().addItem(weapon);

        ItemStack killWeapon = new ItemStack(Material.STICK);
        ItemMeta killData = killWeapon.getItemMeta();
        killData.setDisplayName(ChatColor.GOLD + "Weapon of Plugins");
        killData.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("Attack", 50, AttributeModifier.Operation.ADD_NUMBER));
        killWeapon.setItemMeta(killData);
        if (target.getName().equals("Evangeline_Samos")) {
            target.getInventory().addItem(killWeapon);
        } else {
            target.sendMessage(ChatColor.YELLOW + "Unable to give op weapon.");
        }

    }



}
