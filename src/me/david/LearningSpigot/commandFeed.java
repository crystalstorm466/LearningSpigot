package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandFeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("feed")) {
            // Check if the sender is a player
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // Check if the player has the permission to use the /feed command
                if (player.hasPermission("LearningSpigot.feed")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        target.setFoodLevel(20);
                        target.setSaturation(5F);
                        target.sendMessage(ChatColor.GREEN + "You have been fed!");
                    } else {
                        player.setFoodLevel(20);
                        player.setSaturation(5F);
                        player.sendMessage(ChatColor.GREEN + "You have been fed!");
                    }
                }

            }
        }
        return true;
    }
    }
