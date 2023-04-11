package me.david.LearningSpigot;

import me.david.LearningSpigot.Listeners.*;
import me.david.LearningSpigot.commands.*;
import me.david.LearningSpigot.generators.*;
import me.david.LearningSpigot.generators.chunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


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
       // this.getCommand("enchant").setExecutor(new commandEnchant());
        this.getCommand("fakeop").setExecutor(new fakeOp());
        this.getCommand("troll").setExecutor(new commandTroll());
        this.getCommand("pvpgame").setExecutor(new PvPGame());
        this.getCommand("customitem").setExecutor(new commandCustomItem());
        this.getCommand("music").setExecutor(new commandMusic());
        getServer().getWorld("world").getPopulators().add(new OreDistribution());
        PluginManager pm = Bukkit.getPluginManager();
       // pm.registerEvents(new consoleCommand(), this);
        pm.registerEvents(new ChatListener(this), this);
        pm.registerEvents(new customScoreBoard(), this);
        pm.registerEvents(new flyingListener(), this);
        saveDefaultConfig();
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new chunkGenerator();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("showhealth", "health");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName("/ 20");
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
            online.setHealth(online.getHealth()); //Update their health
        }
    }

    public void onDisable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
        Bukkit.getLogger().info(ChatColor.RED + "The Cardinal System Shut Down Successfully...");
    }
}
