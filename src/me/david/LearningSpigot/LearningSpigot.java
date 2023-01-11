package me.david.LearningSpigot;

import me.david.LearningSpigot.commands.*;
import me.david.LearningSpigot.generators.chunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LearningSpigot extends JavaPlugin implements Listener {

    public static LearningSpigot plugin;
    @Override
    public void onEnable() {
        plugin = this;
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
        this.getCommand("enchant").setExecutor(new commandEnchant());
        this.getCommand("fakeop").setExecutor(new fakeOp());
        this.getCommand("troll").setExecutor(new commandTroll());
        getServer().getWorld("world").getPopulators().add(new OreDistribution());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new consoleCommand(), this);
        pm.registerEvents(new ChatListener(this), this);
        saveDefaultConfig();
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new chunkGenerator();
    }


    public void onDisable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
