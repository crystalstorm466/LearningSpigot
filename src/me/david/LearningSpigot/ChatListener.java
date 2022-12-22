package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private LearningSpigot Plugin;

    public ChatListener(LearningSpigot plugin) {
        this.Plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        String swearWords[] = new String[13];
        swearWords[0] = "heck";
        swearWords[1] = "hell";
        swearWords[2] = "ass";
        swearWords[3] = "bitch";
        swearWords[4] = "asshole";
        swearWords[5] = "fuck";
        swearWords[6] = "frick";
        swearWords[7] = "feck";
        swearWords[8] = "asshole";
        swearWords[9] = "pussy";
        swearWords[10] = "porn";

        Player player = event.getPlayer();
        String message = event.getMessage();
        for (int i = 0; i < swearWords.length; i++) {

            message = message.toLowerCase();
            if (message.contains(swearWords[i])) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Your message was blocked because you swore.");

                /*
                Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + " tried to say " +
                        "a swear word in chat and has ruined the chat for everyone."
                 */

                break;
            }
        }
    }
}
