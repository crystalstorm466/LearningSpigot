package me.david.LearningSpigot;

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
                // Toggle the player's flying state

                player.setAllowFlight(true);
                player.setFlying(true);
                // Send a message to the player
                player.sendMessage("Flying mode toggled!");
            } else {
                // Send a message to the player
                player.sendMessage("You don't have permission to use the /fly command!");
            }
        }

        // Return true to indicate that the command was successful
        return true;

}

}
