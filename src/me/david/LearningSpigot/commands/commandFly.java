package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check if the sender is a player
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check if the player has the permission to use the /fly command
            if (player.hasPermission("LearningSpigot.fly")) {
                if (args.length == 1) {
                    boolean isFlying = false;
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setAllowFlight(true);
                    if (target.isFlying()) {
                        target.setAllowFlight(false);
                        target.setFlying(false);
                    } else {
                        target.setAllowFlight(true);
                        target.setFlying(true);
                    }
                    return true;
                } else {
                    // Toggle the player's flying state
                    if (player.isFlying()) {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        // Send a message to the player
                        player.sendMessage("Flying mode toggled!");
                    } else {
                        player.setFlying(true);
                        player.setAllowFlight(true);
                        player.sendMessage("Flying mode toggled!");
                    }
                }
            } else {
                // Send a message to the player
                player.sendMessage("You don't have permission to use the /fly command!");
            }
        }

        // Return true to indicate that the command was successful
        return true;

}

}
