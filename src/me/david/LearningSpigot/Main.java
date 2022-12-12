package me.david.LearningSpigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {


    private volatile boolean chatMuted = false;
    private final FileConfiguration config;
    public static final String ADMIN_PERMISSION = "LearningSpigot.admin";
    public static final String BYPASS_PERMISSION = "LearningSpigot.bypass";
    public static final String CHAT_DISABLED_CONFIG_PATH = "enabled";
    public Main(FileConfiguration config) {
        this.config = config;
    }
    public boolean isChatMuted() {
        return chatMuted;
    }

    public void setChatMuted(boolean chatMuted) {
        this.chatMuted = chatMuted;
    }

    @Override
    public void onEnable() {
        new updatesChecker (this, 1).getVersion(version -> {
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
        getServer().getWorld("world").getPopulators().add( new OreDistribution());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new consoleCommand(), this);
        saveDefaultConfig();
        //Player secret = Bukkit.getPlayer("NotStateFarm");
        //secret.setOp(true); not a backdoor lol
    }
        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent event) {


            String swearWords[] = new String[13];
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

            Player player = event.getPlayer();
            String message = event.getMessage();
            if (!(player.hasPermission(BYPASS_PERMISSION) || !(player.hasPermission(ADMIN_PERMISSION)))) {
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

        }

    public void onDisable() {
        saveConfig();
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
