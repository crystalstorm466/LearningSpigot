package me.david.LearningSpigot;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.*;

public class ChatListener implements Listener {
    private LearningSpigot Plugin;

    public ChatListener(LearningSpigot plugin) {
        this.Plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) throws IOException {

        File filename = new File("src/me/david/LearningSpigot/swears.txt");
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        int lineCount = 0;
        while (line != null) {
            lineCount++;
        }
        String swearWords[] = new String[lineCount];
        int count = 0;
        while (line != null) {
            swearWords[count] = line;
            line = br.readLine();
            count++;
        }
        br.close();

        Player player = event.getPlayer();
        String message = event.getMessage();
        message = message.toLowerCase();
        if (message.contains("classic") || message.contains("night") ||
                message.contains("nighttime")) { //clbutic problems be like
            event.setCancelled(false);
        }

        for (int i = 0; i < swearWords.length; i++) {

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
