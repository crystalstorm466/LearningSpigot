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

import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class commandEnchant implements CommandExecutor {
    final static File filePath = new File("src/me/david/LearningSpigot/enchants.txt");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("enchant")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("LearningSpigot.admin")) {
                    if (args.length == 3) {

                        Player target = Bukkit.getPlayer(args[0]);
                        ItemStack item = (target.getItemInUse());
                        if (item == null || item.getType() == Material.AIR) {
                        }
                        assert item != null;
                        //TODO add a way for players to get enchants slightly wrong or get tab-completion working
                        Map<String, String> mapFromFile = HashMapFromTextFile();


                        Enchantment enchantment = null;
                       /*
                        for (int i = 0; i < mapFromFile.size(); i++) {
                            String type = mapFromFile.get(i);

                            if (args[1].contains(mapFromFile.get(i))) {
                                enchantment = Enchantment.getByName(type);
                                break;
                            }
                        }
                        */


                        assert enchantment != null;
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

    public static Map<String, String> HashMapFromTextFile() {
        Map<String, String> enchants = new HashMap<String, String>();
        BufferedReader br = null;
        try {
            File file = filePath;
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String name = parts[0].trim();
                String number = parts[1].trim();
                if (!name.equals(" ") &&  !number.equals(""))  {
                    enchants.put(name, number);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                };
            }
        }
        return enchants;
    }
}