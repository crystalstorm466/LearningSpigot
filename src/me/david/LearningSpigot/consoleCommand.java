package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class consoleCommand implements Listener {
    @EventHandler
    public void onConsoleChat(ServerCommandEvent event) {
        String command = event.getCommand();
        if (command.startsWith("say")) {
            event.setCancelled(true);
            String message = command.substring(4);
            Bukkit.broadcastMessage("Someone tried to send an ad lol.");
        }
    }
}
