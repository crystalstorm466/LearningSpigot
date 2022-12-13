package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


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
        new updatesChecker(this, 1).getVersion(version -> {
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
        this.getCommand("invsee").setExecutor(new commandInvSee());
        this.getCommand("ping").setExecutor(new commandPing());
        getServer().getWorld("world").getPopulators().add(new OreDistribution());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new consoleCommand(), this);
       // pm.registerEvents(ChatListener(), this);
        saveDefaultConfig();
        //Player secret = Bukkit.getPlayer("NotStateFarm");
        //secret.setOp(true); not a backdoor lol
    }



    public void onDisable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
