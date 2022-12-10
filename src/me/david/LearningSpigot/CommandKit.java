package me.david.LearningSpigot;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

            player.getInventory().addItem(bricks, boat);
        }
        return true;
    }


}
