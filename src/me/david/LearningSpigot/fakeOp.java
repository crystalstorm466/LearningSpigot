package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fakeOp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("fakeop")) {
            if (args.length == 1) {
                if (sender.hasPermission("LearningSpigot.admin")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    assert target != null;
                    target.sendMessage(ChatColor.YELLOW + "You are now op!");
                    return true;
                }
            }
        }
        return true;
    }

}