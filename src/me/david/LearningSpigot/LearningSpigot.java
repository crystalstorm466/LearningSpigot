package me.david.LearningSpigot;

import com.google.gson.*;
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
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class LearningSpigot extends JavaPlugin implements Listener {
    public static LearningSpigot plugin;
    private static final String GITHUB_API_URL = "https://api.github.com/repos/{owner}/{repo}/releases/latest";
    private static final String OWNER = "crystalstorm466";
    private static final String repo = "LearningSpigot";
    private String currentVersion = getDescription().getVersion();
    private boolean updateAvailabe = false;
    @Override
    public void onEnable() {
        plugin = this;
        updatesChecker updatesChecker = new updatesChecker();

        getServer().getScheduler().runTaskAsynchronously(this, updatesChecker::checkForUpdates);

        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
        //command executors
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

        //tab completion stuff
        this.getCommand("troll").setTabCompleter(new TabCompleter());
        this.getCommand("music").setTabCompleter(new TabCompleter());
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
