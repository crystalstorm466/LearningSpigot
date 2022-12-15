package me.david.LearningSpigot;

import me.david.LearningSpigot.generators.chunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LearningSpigot extends JavaPlugin implements Listener {


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
        this.getCommand("game").setExecutor(new commandGame());
        this.getCommand("feed").setExecutor(new commandFeed());
        this.getCommand("heal").setExecutor(new commandFeed());
        getServer().getWorld("world").getPopulators().add(new OreDistribution());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new consoleCommand(), this);
       // pm.registerEvents(ChatListener(), this);
        saveDefaultConfig();
        //Player secret = Bukkit.getPlayer("NotStateFarm");
        //secret.setOp(true); not a backdoor lol
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new chunkGenerator();
    }


    public void onDisable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
