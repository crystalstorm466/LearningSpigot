package me.david.LearningSpigot;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.entity.Player.*;


public class commandKick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kick")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission("LearningSpigot.kick")) {
                        player.kickPlayer(args[0]);
                        sender.sendMessage(args[0] + " has been kicked");

                    }
                    //give items


                }
            }
        }

        return true;
    }

}
