package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class commandTroll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("troll")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length <= 2) {
                    String option = args[1].toLowerCase();
                    switch(option) {
                        case "smite":
                            Player target = Bukkit.getPlayer(args[0]);
                            assert target != null;
                            Location location = target.getLocation();
                            target.getWorld().strikeLightning(location);
                            target.sendMessage(ChatColor.GREEN + "Thou hast been smited!");
                            break;
                        case "throw":
                            Player throwTarget = Bukkit.getPlayer(args[0]);
                            assert throwTarget != null;
                            throwTarget.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10, 10));
                            throwTarget.sendMessage(ChatColor.YELLOW + "You have been thrown!");
                            break;
                    }

                } else {
                    player.sendMessage(ChatColor.DARK_RED + "Incorrect usage! " + ChatColor.GOLD + "/troll <player> <smite|troll>");
                }
            }
        }
        return true;
    }
}
