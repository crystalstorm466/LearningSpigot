package me.david.LearningSpigot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
            loreList[0] = (ChatColor.GOLD + "Winner of the Hide and Seek Competition");
            loreList[1] = (ChatColor.GOLD + "Awarded to: " + player.getName() + " who won as a Seeker.");
            loreList[2] = (ChatColor.GOLD + "This award is highly prestigious\n only a select few has the honor of receiving it.");
            awardData.setLore(Arrays.asList(loreList));
            awardData.setDisplayName( ChatColor.RED + "Hide and Seek Trophy - Seeker - " + player.getName());
            award.setItemMeta(awardData);

            player.getInventory().addItem(award);
        }
        return true;
    }


}
