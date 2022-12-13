package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private Main Plugin;

    public ChatListener(Main plugin) {
        this.Plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        final Main plugin = new Main();
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

        String swearWords[] = new String[13];
        swearWords[1] = "heck";
        swearWords[2] = "hell";
        swearWords[3] = "ass";
        swearWords[4] = "bitch";
        swearWords[5] = "asshole";
        swearWords[6] = "fuck";
        swearWords[7] = "frick";
        swearWords[8] = "feck";
        swearWords[9] = "asshole";
        swearWords[10] = "pussy";
        swearWords[11] = "porn";

        Player player = event.getPlayer();
        String message = event.getMessage();
        for (int i = 0; i < swearWords.length; i++) {
            if (message.equalsIgnoreCase(swearWords[i])) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Your message was blocked because you swore.");

                Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + " tried to say " +
                        "a swear word in chat and has ruined the chat for everyone.");
                break;
            }
        }
    }
}
