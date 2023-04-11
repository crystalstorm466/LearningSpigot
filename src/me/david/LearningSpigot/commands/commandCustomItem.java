package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class commandCustomItem implements CommandExecutor {


    private static int quantity;
    private static ItemStack masterElytra;
    public static ItemStack getWings() {
        createWings();
        return masterElytra;
    }
    public static void createWings() {
        ItemStack wings = new ItemStack(Material.ELYTRA);
        ItemMeta wingsData = wings.getItemMeta();
        wingsData.setUnbreakable(true);
        wingsData.setDisplayName(ChatColor.AQUA + "ALFHEIM ONLINE WINGS");
        wingsData.addAttributeModifier(Attribute.GENERIC_FLYING_SPEED, new AttributeModifier("Flying Speed", 30, AttributeModifier.Operation.ADD_NUMBER));
        wingsData.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("Health", 20, AttributeModifier.Operation.ADD_NUMBER));
        wingsData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        String[] loreArray = new String[5];
        loreArray[0] = ChatColor.GREEN + "Somehow these set of wings were rumored to be apart of a completely separate game..";
        loreArray[1] = ChatColor.RED + "There's an issue with these wings that could crash the server if used...";
        loreArray[3] = ChatColor.ITALIC + " " + ChatColor.GRAY + "There are numerous markings on the wings but they are unreadable however there are some hints of a wierd AI system..";
        loreArray[4] = ChatColor.MAGIC + "This pickaxe is " + quantity++ + " made in the server.";
        wingsData.setLore(Arrays.asList(loreArray));
        wings.setItemMeta(wingsData);
        masterElytra = wings;
    }

    public Player eventChecker;

    //private final String ADMIN_UUID = "6154ea32-c99c-4398-94e8-945d4de8ff8e";
    private final String ADMIN_NAME = "Evangeline_Samos";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("customitem")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length <= 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    assert target != null;
                    switch(args[1].toLowerCase()) {
                        case "sword":
                            give_custom_item(target, "sword");
                            Player admin = Bukkit.getPlayer(ADMIN_NAME);
                            if (admin != null) {
                                admin.sendMessage(ChatColor.DARK_RED + " " + ChatColor.BOLD + "SERVER ROOM ACCESSED! KEY GPS LOCATION CHANGED:\n" +
                                        "NEW LOCATION: " + target.getLocation());
                                admin.playSound(admin.getLocation(), Sound.ENTITY_GHAST_SCREAM, 3.0F, 0.5F);
                            }
                            return true;
                        case "pickaxe":
                             give_custom_item(target, "pickaxe");
                             admin = Bukkit.getPlayer(ADMIN_NAME);
                            if (admin != null) {
                                admin.sendMessage(ChatColor.DARK_RED + " " + ChatColor.BOLD + "SERVER ROOM ACCESSED! UNKNOWN ITEM REMOVED: GPS LOCATION CHANGED:\n" +
                                        "NEW LOCATION: " + target.getLocation());
                                admin.playSound(admin.getLocation(), Sound.ENTITY_GHAST_SCREAM, 3.0F, 0.5F);
                            }
                            return true;
                        case "wings":
                            give_custom_item(target, "wings");
                            admin = Bukkit.getPlayer(ADMIN_NAME);
                            eventChecker = target;
                            if (admin != null) {
                                admin.sendMessage(ChatColor.DARK_RED + " " + ChatColor.BOLD + "SERVER ROOM ACCESSED! UNKNOWN ITEM REMOVED: GPS LOCATION CHANGED:\n" +
                                        "NEW LOCATION: " + target.getLocation());
                                admin.playSound(admin.getLocation(), Sound.ENTITY_GHAST_SCREAM, 3.0F, 0.5F);
                            }
                            return true;
                        default:
                            target.sendMessage(ChatColor.RED + "Unknown item! Usage: /customitem <player> <sword|pickaxe|wings>");
                            return true;
                    }
                }
            }
        }


        return true;
    }


    private void give_custom_item(Player target, String item) {
        if (item.equals("sword")) {
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta swordData = sword.getItemMeta();
            swordData.setUnbreakable(true);
            swordData.setDisplayName(ChatColor.AQUA + "The Server Key");
            swordData.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("Attack", 5, AttributeModifier.Operation.ADD_NUMBER));
            swordData.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, new AttributeModifier("Attack", 4, AttributeModifier.Operation.ADD_NUMBER));
            swordData.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier("Speed", 4, AttributeModifier.Operation.ADD_NUMBER));
            swordData.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("Health", 20, AttributeModifier.Operation.ADD_NUMBER));
            swordData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            String[] loreArray = new String[5];
            loreArray[0] = ChatColor.GREEN + "This sword was found in an old storage cabient inside of the original Hypixel server room";
            loreArray[1] = ChatColor.GREEN + "Legends has that this sword has immense power on any server....";
            loreArray[2] = ChatColor.MAGIC + "This key " + ChatColor.RESET + " can unlock " + ChatColor.MAGIC + " any permissions for " + ChatColor.RESET + " server admins.";
            loreArray[3] = ChatColor.ITALIC + " " + ChatColor.GRAY + "There are numerous markings on the sword blade but they are unreadable however there are some hints of java..";
            loreArray[4] = "This sword is " + quantity++ + " made in the server.";
            swordData.setLore(Arrays.asList(loreArray));
            sword.setItemMeta(swordData);
            target.getInventory().addItem(sword);
        } else if (item.equals("pickaxe")) {
            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta pickData = pickaxe.getItemMeta();
            pickData.setUnbreakable(true);
            pickData.addEnchant(Enchantment.DIG_SPEED, 70, true);
            pickData.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("Attack", 5, AttributeModifier.Operation.ADD_NUMBER));
            pickData.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, new AttributeModifier("Attack", 4, AttributeModifier.Operation.ADD_NUMBER));
            pickData.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier("Speed", 4, AttributeModifier.Operation.ADD_NUMBER));
            pickData.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("Health", 20, AttributeModifier.Operation.ADD_NUMBER));
            pickData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            String[] loreArray = new String[5];
            loreArray[0] = ChatColor.GREEN + "The only tool that is able to break the server SSD.";
            loreArray[1] = ChatColor.GREEN + "Legends has it that the Server Owner has tried to look for it and will give OP to any who can find it.";
            loreArray[3] = ChatColor.ITALIC + " " + ChatColor.GRAY + "There are numerous markings on the blade but they are unreadable however there are some hints of java..";
            loreArray[4] = "This pickaxe is " + quantity++ + " made in the server.";
            pickData.setLore(Arrays.asList(loreArray));
            pickaxe.setItemMeta(pickData);
            target.getInventory().addItem(pickaxe);
        } else if (item.equals("wings")) {
            ItemStack wings = new ItemStack(Material.ELYTRA);
            ItemMeta wingsData = wings.getItemMeta();
            wingsData.setUnbreakable(true);
            wingsData.setDisplayName(ChatColor.AQUA + "ALFHEIM ONLINE WINGS");
            wingsData.addAttributeModifier(Attribute.GENERIC_FLYING_SPEED, new AttributeModifier("Flying Speed", 30, AttributeModifier.Operation.ADD_NUMBER));
            wingsData.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("Health", 20, AttributeModifier.Operation.ADD_NUMBER));
            wingsData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            String[] loreArray = new String[5];
            loreArray[0] = ChatColor.GREEN + "Somehow these set of wings were rumored to be apart of a completely separate game..";
            loreArray[1] = ChatColor.RED + "There's an issue with these wings that could crash the server if used...";
            loreArray[3] = ChatColor.ITALIC + " " + ChatColor.GRAY + "There are numerous markings on the wings but they are unreadable however there are some hints of a wierd AI system..";
            loreArray[4] = ChatColor.MAGIC + "This pickaxe is " + quantity++ + " made in the server.";
            wingsData.setLore(Arrays.asList(loreArray));
            wings.setItemMeta(wingsData);
            target.getInventory().addItem(wings);


        }
    }


}
