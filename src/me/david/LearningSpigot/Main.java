package me.david.LearningSpigot;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Main extends JavaPlugin implements Listener {
    private volatile boolean chatMuted = false;

    public boolean isChatMuted() {
        return chatMuted;
    }

    public void setChatMuted(boolean chatMuted) {
        this.chatMuted = chatMuted;
    }

    @Override
    public void onEnable() {
        new updatesChecker (this, 12345).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("This is not updates.");
            } else {
                getLogger().info("This is a new update");
            }
        });
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
        this.getCommand("kit").setExecutor(new CommandKit());
        this.getCommand("kick").setExecutor(new commandKick());
        this.getCommand("fly").setExecutor(new commandFly());
        this.getCommand("mute").setExecutor(new commandMute());
        this.getCommand("unmute").setExecutor(new commandMute());
        this.getCommand("invsee").setExecutor(new commandInvSee());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new consoleCommand(), this);
        saveDefaultConfig();
        //Player secret = Bukkit.getPlayer("NotStateFarm");
        //secret.setOp(true); not a backdoor lol
    }

        String swearWords[] = new String[13];

        public void setSwearWords(String[] swearWords) {
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
            this.swearWords = swearWords;
        }


        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent event) {

            Player player = event.getPlayer();
            String message = event.getMessage();
            if (chatMuted) {
                event.getPlayer().sendMessage(ChatColor.RED + "The chat is currently muted.");
                event.setCancelled(true);
            } else {
                for (int i = 0; i < swearWords.length; i++) {
                    if (message.toLowerCase().contains(swearWords[i])) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "Your message was blocked because you swore.");

                        Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + " tried to say " +
                                "a swear word in chat and has ruined the chat for everyone.");
                        setChatMuted(true);
                        break;
                    }
                }
            }
            if (isChatMuted()) {
                event.getPlayer().sendMessage(ChatColor.RED + "The chat is currently muted.");
                event.setCancelled(true);
            } else {
                if (message.equalsIgnoreCase("goodbye")) {
                    event.setCancelled(true);
                    String playerID = player.getUniqueId().toString();
                    Date banExpire = Date.from(Instant.now().plus(Duration.ofDays(2)));
                    Bukkit.getBanList(BanList.Type.NAME).addBan(playerID, "You have been banned b/c you crashed the server.", banExpire, null);
                    Bukkit.shutdown();

                }
            }

        }


    public void onDisable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
