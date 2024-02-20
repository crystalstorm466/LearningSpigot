package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class commandPing implements CommandExecutor {
    public static  int TICK_COUNT = 0;
    public static long[] TICKS = new long[600];
    public static long LAST_TICK = 0L;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (command.getName().equalsIgnoreCase("ping") || command.getName().equalsIgnoreCase("tps")) {
            if (sender instanceof  Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String playerName = player.getName();
                    player.sendMessage(ChatColor.GREEN + "[Learning Spigot] Your ping is " + Bukkit.getPlayer(playerName).getPing());
                    player.sendMessage(ChatColor.GREEN + "[Learning Spigot] The server TPS: " + getTPS(100));
                    return true;
                } else if (args.length == 1) {
                    Player player = (Player) sender;
                    String playerName = Bukkit.getPlayer(args[0]).getName();
                    player.sendMessage(ChatColor.GREEN + "[Learning Spigot] " + playerName + "'s ping is " + Bukkit.getPlayer(playerName).getPing());
                    player.sendMessage(ChatColor.GREEN + "[Learning Spigot] The server TPS: " + getTPS(100));
                    return true;
                }
            } else {
                Bukkit.getLogger().info("[Learning Spigot] You must run this command as a player.");
                return true;


            }
        }
        return false;
    }

    public static double getTPS() { return getTPS(100); }
    public static double getTPS(int ticks) {
        if (TICK_COUNT < ticks ) {
            return 20.0D;
        }

        int target = (TICK_COUNT - 1 -ticks) % TICKS.length;
        long elapsed = System.currentTimeMillis() - TICKS[target];

        return ticks / (elapsed / 1000.0D);
     }

}
