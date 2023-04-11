package me.david.LearningSpigot.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class consoleCommand implements Listener {
    @EventHandler
    public void onConsoleChat(ServerCommandEvent event) {
        //this exists as I used to run this on server.pro and they would pull this type of shit by putting ads.
        String command = event.getCommand();
        if (command.startsWith("say")) {
            event.setCancelled(true);
            String message = command.substring(4);
            Bukkit.broadcastMessage(ChatColor.GOLD + "[SERVER] Someone tried to send an ad lol.");
        }
    }
}
