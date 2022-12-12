package me.david.LearningSpigot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;


public class updatesChecker {

    private final JavaPlugin plugin;
    private final int FINALID;

    public updatesChecker (JavaPlugin plugin, int FINALID) {
        this.plugin = plugin;
        this.FINALID = FINALID;
    }

    public void getVersion(final Consumer<String> consumer) { //todo this won't do anything until I upload my plugin
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.FINALID).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
        }
    } catch (IOException exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
});
    }



    }

