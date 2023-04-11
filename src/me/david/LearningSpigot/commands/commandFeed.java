package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

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
                        if (!(target == null)) {
                            target.setFoodLevel(20);
                            target.setSaturation(5F);
                            target.sendMessage(ChatColor.GREEN + "You have been fed!");
                        } else {
                            player.sendMessage(ChatColor.RED + "This player can not be found!");
                        }
                    } else {
                        player.setFoodLevel(20);
                        player.setSaturation(5F);
                        player.sendMessage(ChatColor.GREEN + "You have been fed!");
                    }
                }

            }
        }
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("LearningSpigot.heal")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (!(target == null)) {
                            target.setFoodLevel(20);
                            target.setSaturation(5F);
                            double maxHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                            target.setHealth(maxHealth);
                            for (PotionEffect effect : target.getActivePotionEffects()) {
                                target.removePotionEffect(effect.getType());
                            }
                            target.sendMessage(ChatColor.GREEN + "You have been healed!");
                        } else {
                            player.sendMessage(ChatColor.RED + "That player does not exist!");
                        }
                    } else {
                        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                        player.setHealth(maxHealth);
                        player.setFoodLevel(20);
                        player.setSaturation(5F);
                        for (PotionEffect effect : player.getActivePotionEffects()) {
                            player.removePotionEffect(effect.getType());
                        }
                        player.sendMessage(ChatColor.GREEN + "You have been healed!");
                    }
                }
            }
        }
        return true;
    }
    }
