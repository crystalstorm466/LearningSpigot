package me.david.LearningSpigot;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.entity.Player.*;


public class commandMute implements CommandExecutor {

    public static boolean mute;
    private Main plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mute")) {
            if (args.length == 0) {
                    Player player = (Player) sender;
                    if (player.hasPermission("LearningSpigot.mute")) {
                        if(plugin.isChatMuted()) {
                            player.sendMessage("Chat is already muted!");
                        } else {
                            plugin.setChatMuted(true);
                            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " has muted the server!");
                        }


                    }
                    //give items


                }
            }
        if (command.getName().equalsIgnoreCase("unmute")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission("LearningSpigot.mute")) {
                        if (plugin.isChatMuted()) {
                            plugin.setChatMuted(false);
                            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + player.getName() + " has unmuted the server!");
                        } else {
                            player.sendMessage("Chat isn't muted!");
                        }
                    }
                }
            }
        }

        return true;
    }

}
