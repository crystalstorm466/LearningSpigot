package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class commandInvSee implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("invsee")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(args.length == 0)) {
                    if (player.hasPermission("LearningSpigot.invsee")) {
                    /*PlayerInventory inventory = Bukkit.getPlayer(args[0]).getInventory();

                    for (int i = 0; i < inventory.getSize(); i++) {
                        initalizeItems(inventory);
                    }
                    openInventory(player);
                }*/
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        player.openInventory(target.getInventory());
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "[Learning Spigot] You need to provide a player!");
                    return true;
                }
            }
        }
        return true;
    }

}
