package me.david.LearningSpigot.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Arrays;
import java.util.Random;
import java.util.function.BiConsumer;

public class commandGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("game")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "[Learning Spigot] Not enough arguments! </game <runner> <hunter1> hunter2>>");
                    return true;
                }
                if (args[0].equalsIgnoreCase("stop")) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "The Game is now over!");
                    return true;
                }

               if (args.length <= 3) {

                   Player player = (Player) sender;
                   Player runner = Bukkit.getPlayer(args[0]);
                   Player hunter1 = Bukkit.getPlayer(args[1]);
                   Player hunter2 = Bukkit.getPlayer(args[2]);

                   if (runner == null || hunter1 == null || hunter2 == null) {
                          player.sendMessage(ChatColor.RED + "[Learning Spigot] One or more players are not online!");
                          return true;
                   }
                        giveHunter(hunter1, hunter2);
                       Bukkit.broadcastMessage(ChatColor.GOLD + "[GAME] The seeker is now glowing!");
                       runner.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 255, 20, false));


                       Random random = new Random();
                       if (random.nextInt(10) >= 6) {
                           hunter1.getInventory().clear();
                           hunter2.getInventory().clear();
                           Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[GAME] The Hunters have lost items!");
                       } else {
                           runner.getInventory().clear();
                           Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[GAME] The Seeker have lost items!");
                       }
                       if (runner.getHealth() == 0) {
                           huntersWin(runner, hunter1, hunter2);
                           return true;
                       } else {
                           runnersWin(runner);
                           return true;
                       }

               } else {
                   sender.sendMessage(ChatColor.RED + "[Learning Spigot] Not enough arguments! </game <runner> <hunter1> hunter2>>");
                   return true;
               }
               }
            }

        return true;
    }

    private void runnersWin(Player runner) {
        runner.getWorld().playSound(runner.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2F, 0.1F);
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[GAME] The Seeker: " + runner.getName()
                + " has won the game! Congratulations!");
        ItemStack award = new ItemStack(Material.TOTEM_OF_UNDYING);
        award.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta awardData = award.getItemMeta();
        awardData.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        String loreList[] = new String[3];
        loreList[0] = (ChatColor.GOLD + "Winner of the Hide and Seek Competition");
        loreList[1] = (ChatColor.GOLD + "Awarded to: " + runner.getName() + " who won as a Seeker.");
        loreList[2] = (ChatColor.GOLD + "This award is highly prestigious\n only a select few has the honor of receiving it.");
        awardData.setLore(Arrays.asList(loreList));
        awardData.setDisplayName( ChatColor.RED + "Hide and Seek Trophy - Seeker - " + runner.getName());
        award.setItemMeta(awardData);

        runner.getInventory().addItem(award);
    }

    private void huntersWin(Player runner, Player hunter1, Player hunter2) {
        runner.getWorld().playSound(runner.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2F, 0.1F);

        Bukkit.broadcastMessage(ChatColor.GOLD + "[GAME] The Hunters: " + hunter1.getName() +", " + hunter2.getName() +
                " has won the game! Congratulations!");
        ItemStack award = new ItemStack(Material.TOTEM_OF_UNDYING);
        award.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta awardData = award.getItemMeta();
        awardData.setDisplayName("WINNER OF Hide and Seek! - Hunters -" + hunter1.getName() + " and " + hunter2.getName());
        awardData.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        String loreList[] = new String[3];
        loreList[0] = (ChatColor.GOLD + "Winner of the Hide and Seek");
        loreList[1] = (ChatColor.GOLD + "Awarded to: " + hunter1.getName() + " and " + hunter2.getName() + " who both won as Hunters.");
        loreList[2] = (ChatColor.GOLD + "This award is highly prestigious\n only a select few has the honor of receiving it.");
        awardData.setLore(Arrays.asList(loreList));
        award.setItemMeta(awardData);

        hunter1.getInventory().addItem(award);
        hunter2.getInventory().addItem(award);
    }

    private void giveHunter(Player hunter1, Player hunter2) {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack leggins = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack feet = new ItemStack(Material.IRON_BOOTS);
        hunter1.getInventory().addItem(helmet, chestplate, leggins, feet);
        hunter2.getInventory().addItem(helmet, chestplate, leggins, feet);


        Bukkit.broadcastMessage(ChatColor.GOLD + "[GAME] Hunters now have enchanted Iron Armor!");

        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        chestplate.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        leggins.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
        hunter1.getInventory().removeItem(helmet, chestplate, leggins, feet);
        hunter2.getInventory().removeItem(helmet, chestplate, leggins, feet);

        hunter1.getInventory().addItem(helmet, chestplate, leggins, feet);
        hunter2.getInventory().addItem(helmet, chestplate, leggins, feet);



    }
}