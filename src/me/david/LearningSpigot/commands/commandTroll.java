package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandTroll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("troll")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 3) {
                    if (args[1].equalsIgnoreCase("asian")) {
                        //TODO plays emotional damage to player
                        Player target = Bukkit.getPlayer(args[0]);
                        target.playSound(target.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_1, 10, 29);

                    } else if (args[1].equalsIgnoreCase("//todo")) {
                        //TODO
                    }
                }
            }
        }
        return true;
    }
}
