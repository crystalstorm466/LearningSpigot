package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class commandEnchant implements CommandExecutor {
    final static File FILEPATH = new File("enchants.txt");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("enchant")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("LearningSpigot.admin")) {
                    if (args.length == 3) { //player [0] , enchantment [1], level[2]
                        Enchantment enchantment = null;
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        ItemStack item = (target.getItemInHand());
                        if (item == null || item.getType() == Material.AIR) {
                            sender.sendMessage(ChatColor.RED + "[Learning Spigot] The player is not holding an item!");
                            return true;
                        }
                        ArrayList<String> enchants = new ArrayList<>();
                        try {
                            enchants = enchantsFromFile();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        String closestMatch = getClosestMatch(args[1], enchants);
                        enchantment = Enchantment.getByName(closestMatch);

                        item.addUnsafeEnchantment(enchantment, Integer.parseInt(args[2]));
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "[Learning Spigot] No Perms!");
                    return true;
                }
            }
        }
        return false;
    }

    public static String getClosestMatch(String input, ArrayList<String> candidates) {
        int minDistance = Integer.MAX_VALUE;
        String closestMatch = null;

        for (String candidate : candidates) {
            int distance = getLevenshteinDistance(input, candidate);
            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = candidate;
            }
        }

        return closestMatch;
    }

    public static int getLevenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)), dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }


    public static ArrayList<String> enchantsFromFile() throws FileNotFoundException {
        ArrayList<String> enchants = new ArrayList<>();
        File enchantsFile = new File(FILEPATH.toURI());
        Scanner fileScan = new Scanner(enchantsFile);
        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            if (line.startsWith("#")) {
                continue; //line is a comment
            }
            String[] parts = line.split(":");
            for (int i = 0; i < parts.length; i++) {
                enchants.add(parts[i]); //every other one would be the Enchantment.*;
            }
        }
        fileScan.close();
        return enchants;
    }

}

