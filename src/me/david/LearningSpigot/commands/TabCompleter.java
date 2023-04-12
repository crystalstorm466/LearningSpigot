package me.david.LearningSpigot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter  {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if ( command.getName().equalsIgnoreCase("troll")) {
            if ( sender instanceof Player) {
                if (args.length == 0) {
                    list.add("Player");
                    Collections.sort(list);
                    return list;
                } else if (args.length == 1) {
                    list.add("throw");
                    list.add("smite");
                    list.add("scream");
                    for (String s : list) {
                        if (!s.toLowerCase().startsWith(args[0].toLowerCase())) {
                            list.remove(s);
                        }
                    }
                    Collections.sort(list);
                    return list;
                }
            }
        } else if (command.getName().equalsIgnoreCase("music")) {
            if ( sender instanceof Player ) {
                if (args.length == 0 ) {
                    list.add("Target");
                    Collections.sort(list);
                    return list;
                } else if ( args.length == 1 ) {
                    list.add("13");
                    list.add("cat");
                    list.add("blocks");
                    list.add("chirp");
                    list.add("far");
                    list.add("mall");
                    list.add("mellohi");
                    list.add("stal");
                    list.add("strad");
                    list.add("ward");
                    list.add("11");
                    list.add("otherside");
                    list.add("pigstep");
                    list.add("stop");
                    for (String s : list) {
                        if (!s.toLowerCase().startsWith(args[0].toLowerCase())) {
                            list.remove(s);
                        }
                    }
                    Collections.sort(list);
                    return list;
                }
            }
        } else if (command.getName().equalsIgnoreCase("customitem")) {
            if ( sender instanceof Player) {
                if (args.length == 0) {
                    list.add("Target");
                } else if ( args.length == 1 ) {
                    list.add("wings");
                    list.add("sword");
                    list.add("pickaxe");
                    for (String s : list) {
                        if (!s.toLowerCase().startsWith(args[0].toLowerCase())) {
                            list.remove(s);
                        }
                    }
                    Collections.sort(list);
                    return list;
                }
            }
        }
        return null;
    }
}
