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
        boolean targetActive = false;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length <= 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if ((target == null)) {
                    target = player;
                } else {
                    target = Bukkit.getPlayer(args[0]);
                }
                switch(args[1].toLowerCase()) {
                    case "regular":
                    case "":
                        giveItems(target, 1);
                        return true;
                    case "op":
                        giveItems(target, 2);
                        return true;
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid Arguements! /kit <player> <1|2>");
                return true;
            }
            return true;
            //give items
        }
        return true;
    }

    private void giveItems(Player target, int kit) {
        if (kit == 1) {
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
            awardData.setDisplayName(ChatColor.RED + "Plugin Development - Learning Spigot - " + target.getName());
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
        } else if (kit == 2) {
            ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack diamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack diamondLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);

            ItemMeta helmetMeta = diamondHelmet.getItemMeta();
            ItemMeta chestMeta = diamondChestplate.getItemMeta();
            ItemMeta leggingsMeta = diamondLeggings.getItemMeta();
            ItemMeta bootsMeta = diamondBoots.getItemMeta();
            ItemMeta SwordMeta = diamondSword.getItemMeta();

            helmetMeta.setDisplayName(ChatColor.BLUE + "OP Helmet");
            helmetMeta.setUnbreakable(true);
            diamondHelmet.setItemMeta(helmetMeta);
            diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
            diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 6);
            diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 6);
            diamondHelmet.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);



            chestMeta.setDisplayName(ChatColor.BLUE + "OP Chest");
            chestMeta.setUnbreakable(true);
            diamondChestplate.setItemMeta(chestMeta);
            diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
            diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 6);
            diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 6);
            diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 6);
            diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 6);


            leggingsMeta.setDisplayName(ChatColor.BLUE + "OP Leggings");
            leggingsMeta.setUnbreakable(true);
            diamondLeggings.setItemMeta(leggingsMeta);
            diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
            diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 6);
            diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 6);
            diamondLeggings.addUnsafeEnchantment(Enchantment.THORNS, 3);

            bootsMeta.setDisplayName(ChatColor.BLUE + "OP Boots");
            bootsMeta.setUnbreakable(true);
            diamondBoots.setItemMeta(bootsMeta);
            diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
            diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 6);
            diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 6);
            diamondBoots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
            diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 6);

            SwordMeta.setDisplayName(ChatColor.BLUE + "OP Sword");
            SwordMeta.setUnbreakable(true);
            diamondSword.setItemMeta(SwordMeta);
            diamondSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
            diamondSword.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            diamondSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 6);


            target.getInventory().addItem(diamondHelmet, diamondChestplate, diamondLeggings, diamondBoots, diamondSword);
        }




    }



}
