package me.david.LearningSpigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class TabCompleter implements org.bukkit.command.TabCompleter  {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if ( command.getName().equalsIgnoreCase("troll")) {
            if ( sender instanceof Player) {
                if (args.length == 1) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        list.add(player.getName());
                    }
                    Iterator<String> iter = list.iterator();
                    while (iter.hasNext()) {
                        String play = iter.next();
                        if (!play.toLowerCase().startsWith(args[0].toLowerCase())) {
                            iter.remove();
                        }
                    }
                    Collections.sort(list);
                    return list;
                }  else if (args.length == 2 ) {
                    List<String> trolls = Arrays.asList("throw", "smite", "scream");
                    Iterator<String> iter = trolls.iterator();
                    while (iter.hasNext()) {
                        String troll = iter.next();
                        if (!troll.toLowerCase().startsWith(args[1].toLowerCase())) {
                            iter.remove();
                        }
                    }
                    list.addAll(trolls);
                    Collections.sort(list);
                    return list;
                }
            }
        } else if (command.getName().equalsIgnoreCase("music")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        list.add(player.getName());
                    }
                    Iterator<String> iter = list.iterator();
                    while (iter.hasNext()) {
                        String play = iter.next();
                        if (!play.toLowerCase().startsWith(args[0].toLowerCase())) {
                            iter.remove();
                        }
                    }
                    Collections.sort(list);
                    return list;
                } else if (args.length == 2) {
                    List<String> songs = Arrays.asList("13", "cat", "blocks", "chirp", "far", "mall", "mellohi", "stal", "strad", "ward", "11", "otherside", "pigstep", "stop");
                    Iterator<String> iter = songs.iterator();
                    while (iter.hasNext()) {
                        String song = iter.next();
                        if (!song.toLowerCase().startsWith(args[1].toLowerCase())) {
                            iter.remove();
                        }
                    }
                    list.addAll(songs);
                }
                Collections.sort(list);
                return list;
            }
        } else if (command.getName().equalsIgnoreCase("customitem")) {
            if ( sender instanceof Player) {
                if (args.length == 1) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        list.add(player.getName());
                    }
                    Iterator<String> iter = list.iterator();
                    while (iter.hasNext()) {
                        String play = iter.next();
                        if (!play.toLowerCase().startsWith(args[0].toLowerCase())) {
                            iter.remove();
                        }
                    }
                    Collections.sort(list);
                    return list;
                } else if ( args.length == 2 ) {
                    List<String> items = Arrays.asList("wings", "sword", "pickaxe");
                    Iterator<String> iter = items.iterator();
                    while (iter.hasNext()) {
                        String item = iter.next();
                        if (!item.toLowerCase().startsWith(args[1].toLowerCase())) {
                            iter.remove();
                        }
                        list.addAll(items);
                    }
                    Collections.sort(list);
                    return list;
                }
            }
        }
        return null;
    }
}
