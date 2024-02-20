package me.david.LearningSpigot.Listeners;

import me.david.LearningSpigot.LearningSpigot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.*;

public class ChatListener implements Listener {
    private LearningSpigot Plugin;
    final static File FILEPATH = new File("swears.txt");
    final static File swearsList = new File(FILEPATH.toURI());

    public ChatListener(LearningSpigot plugin) {
        this.Plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) throws IOException {

     //   File filename = new File(getClass().getResource("Listeners/swears.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(swearsList));
        String line = br.readLine();
        int lineCount = 0;
        while (line != null) {
            lineCount++;
            line = br.readLine();
        }
        br.close();
        br = new BufferedReader(new FileReader(swearsList));
        String[] swearWords = new String[lineCount];
        for (int i = 0; i < lineCount; i++) {
            swearWords[i] = br.readLine().toLowerCase();
        }
        br.close();

        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        for (String swearWord : swearWords) {
            if (swearWord != null && message.contains(swearWord)) {
                player.sendMessage(ChatColor.RED + "Your message was blocked because you swore.");
                Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " tried to say " +
                        "a swear word in chat and has ruined the chat for everyone.");
                event.setCancelled(true);

                return; // No need to continue checking if a swear word is found
            }
        }
        event.setCancelled(false);
    }
}
