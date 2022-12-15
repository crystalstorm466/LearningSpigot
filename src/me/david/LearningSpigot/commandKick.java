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


public class commandKick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kick")) {
            if (args.length >= 1) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission("LearningSpigot.kick")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        String reason = null;
                        if (!(args.length == 1)) {
                            reason = args[1];
                            for (int i = 2; i < args.length; i++) {
                                reason = (reason + " " + args[i]);
                            }
                        } else {
                            reason = "You were kicked from the server!";
                        }

                        target.kickPlayer("Kicked by " + player.getName() + " for reason\n" + reason);
                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "[SERVER] " + args[0] + " has been kicked! For reason: " + reason );

                    }
                    //give items


                }
            }
        }

        return true;
    }

}
