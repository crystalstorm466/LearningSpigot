package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class commandPing implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (command.getName().equalsIgnoreCase("ping")) {
            if (sender instanceof  Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String playerName = player.getName();
                    player.sendMessage(ChatColor.GREEN + "[Learning Spigot] Your ping is " + Bukkit.getPlayer(playerName).getPing());
                    return true;
                } else if (args.length == 1) {
                    Player player = (Player) sender;
                    String playerName = Bukkit.getPlayer(args[0]).getName();
                    player.sendMessage(ChatColor.GREEN + "[Learning Spigot] " + playerName + "'s ping is " + Bukkit.getPlayer(playerName).getPing());
                    return true;
                }
            } else {
                Bukkit.getLogger().info("[Learning Spigot] You must run this command as a player.");
                return true;


            }
        }
        return false;
    }


}
