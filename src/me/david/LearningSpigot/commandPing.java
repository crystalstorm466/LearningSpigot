package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandPing implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (command.getName().equalsIgnoreCase("ping")) {
            if (args[1] == " " ) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "The player's ping is " + Bukkit.getPlayer(args[1]).getPing());
                return true;
            } else {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "Pong! " + Bukkit.getPlayer(String.valueOf(player)).getPing());
                return true;
            }

        }
        return false;
    }


}
