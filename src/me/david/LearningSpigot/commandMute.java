package me.david.LearningSpigot;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;


public class commandMute implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mute")) {
            if (args.length == 0) {
                    Player player = (Player) sender;
                    if (player.hasPermission("LearningSpigot.mute")) {
                        Bukkit.broadcastMessage(ChatColor.RED + "Someone tried to use /mute and that is still be worked on.");
                        return true;
                }
            }
        }

        return false;
    }

}

