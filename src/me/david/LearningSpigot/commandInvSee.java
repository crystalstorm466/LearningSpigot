package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class commandInvSee implements CommandExecutor {
    private final Inventory inv;
    public GUI() {
        inv = Bukkit.createInventory(null, 9, "Inventory");
    }

    public void initalizeItems(PlayerInventory inventory) {
        inv.addItem(createGUIItem(inventory));

        for (int b = 0; b < inventory.getSize(); b++) {
            inv.addItem(inventory.getItem(b));
        }
    }

    protected ItemStack createGuiItem(inv) {
        ItemStack item = null;
        for (int c = 0; c < inv.getSize(); c++) {
            item = new ItemStack(inv.getItem(c).getType(), 1);
            final ItemMeta meta = item.getItemMeta();

            // Set the name of the item
            meta.setDisplayName(String.valueOf(inv.getItem(c).getItemMeta()));
            item.setItemMeta(inv.getItem(c).getItemMeta());

        }

        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!(args[0] == 0)) {
                if (player.hasPermission("LearningSpigot.invsee")) {
                    PlayerInventory inventory = Bukkit.getPlayer(args[0]).getInventory();

                    for (int i = 0; i < inventory.getSize(); i++) {
                        initalizeItems(inventory);
                    }
                    openInventory(player);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You need to provide a player!");
            }


        }
    }
}


