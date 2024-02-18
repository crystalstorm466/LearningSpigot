package me.david.LearningSpigot.commands;

import org.bukkit.*;
import org.bukkit.block.Jukebox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;

public class commandMusic implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args ) {
        if (!(sender instanceof Player)){
            return true;
        }

        if (args.length < 2) {
            return true;
        }

        int musicId = 0;
        Player player = (Player) sender;
        if (Bukkit.getPlayer(args[0]) != null) {
            Player target = Bukkit.getPlayer(args[0]);
        } else {
            player.sendMessage(ChatColor.RED + "Player not Found!");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        target.stopAllSounds();
        switch(args[1].toLowerCase()) {
            case "13":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_13, SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "cat":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_CAT, SoundCategory.RECORDS,.0f, 1.0f);
                break;
            case "blocks":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_BLOCKS, SoundCategory.RECORDS,1.0f, 1.0f);
                break;
            case "chirp":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_CHIRP,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "far":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_FAR,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "mall":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_MALL,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "mellohi":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_MELLOHI,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "stal":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_STAL,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "strad":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_STRAD,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "ward":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_WARD, SoundCategory.RECORDS,1.0f, 1.0f);
                musicId = 2265;
                break;
            case "11":
            case "creepy":
            case "dark":
                musicId = 2266;
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_11, SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "otherside":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_OTHERSIDE,SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "pigstep":
                target.playSound(target.getLocation(), Sound.MUSIC_DISC_PIGSTEP, SoundCategory.RECORDS, 1.0f, 1.0f);
                break;
            case "stop":
                target.stopAllSounds();
                break;
            default:
                player.sendMessage(ChatColor.RED + "Invalid disc name: " + args[1]);
        }
        return true;
    }
}
