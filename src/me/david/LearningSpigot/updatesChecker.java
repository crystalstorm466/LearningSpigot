package me.david.LearningSpigot;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.bukkit.Bukkit.*;


public class updatesChecker {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/{owner}/{repo}/releases/latest";
    private static final String OWNER = "crystalstorm466";
    private static final String repo = "LearningSpigot";
    private String currentVersion = LearningSpigot.plugin.getDescription().getVersion();
    private boolean updateAvailabe = false;




    //    private void checkForUpdates() {
//        try {
//            URL url = new URL(GITHUB_API_URL.replace("{owner}", OWNER).replace("{repo}", repo));
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                Scanner response = new Scanner(connection.getInputStream());
//                StringBuilder responseBuilder = new StringBuilder();
//
//                while ((response.hasNextLine())) {
//                    responseBuilder.append(response.nextLine());
//                }
//                response.close();
//
//                JsonObject latestRelease = new Gson().fromJson(response.toString(), JsonObject.class);
//                String latestVersion = latestRelease.get("tag_name").getAsString();
//                String releaseUrl = latestRelease.get("html_url").getAsString();
//
//                if (!(currentVersion.equalsIgnoreCase(latestVersion))) {
//                    this.updateAvailabe = true;
//                    getLogger().warning("An update is available for " + getName() + "! LatestVersion " + latestVersion);
//                    getLogger().warning("Download it at: " + releaseUrl);
//                } else {
//                    getLogger().warning("Could not check for updates: Response Code " + responseCode);
//                }
//             }
//            }  catch (ProtocolException e) {
//            getLogger().info("Unable to check for updates.");
//            throw new RuntimeException(e);
//        } catch (MalformedURLException e) {
//            getLogger().info("Unable to check for updates.");
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            getLogger().info("Unable to check for updates.");
//            throw new RuntimeException(e);
//        }
//    }
    public void checkForUpdates() {
        try {
            URL url = new URL(GITHUB_API_URL.replace("{owner}", OWNER).replace("{repo}", repo));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner response = new Scanner(connection.getInputStream());
                StringBuilder responseBuilder = new StringBuilder();

                while ((response.hasNextLine())) {
                    responseBuilder.append(response.nextLine());
                }
                response.close();

                JsonElement rootElement = JsonParser.parseString(responseBuilder.toString());
                if (rootElement.isJsonArray()) {
                    // Response is an array, get the first item
                    JsonArray releases = rootElement.getAsJsonArray();
                    if (releases.size() > 0) {
                        JsonObject latestRelease = releases.get(0).getAsJsonObject();
                        String latestVersion = latestRelease.get("tag_name").getAsString();
                        String releaseUrl = latestRelease.get("html_url").getAsString();

                        if (!(currentVersion.equalsIgnoreCase(latestVersion))) {
                            this.updateAvailabe = true;
                            getLogger().warning("An update is available for " + getName() + "! LatestVersion " + latestVersion);
                            getLogger().warning("Download it at: " + releaseUrl);
                            Bukkit.getServer().broadcastMessage(ChatColor.RED + "An udate is available for " + getName() + "! Latest Version " + latestVersion);
                            Bukkit.getServer().broadcastMessage(ChatColor.RED + "You are currently on " + getVersion());
                        } else {
                            getLogger().warning("You have the latest version of Learning Spigot!");
                            getServer().broadcastMessage(ChatColor.GREEN + "You have the latest version of Learning Spigot!");

                        }
                    } else {
                        getLogger().warning("No releases found for " + getName() + ".");
                    }
                } else if (rootElement.isJsonObject()) {
                    // Response is an object, assume it is a single release
                    JsonObject latestRelease = rootElement.getAsJsonObject();
                    String latestVersion = latestRelease.get("tag_name").getAsString();
                    String releaseUrl = latestRelease.get("html_url").getAsString();

                    if (!(currentVersion.equalsIgnoreCase(latestVersion))) {
                        this.updateAvailabe = true;
                        getLogger().warning("An update is available for " + getName() + "! LatestVersion " + latestVersion);
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "An udate is available for " + getName() + "! Latest Version " + latestVersion);
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "You are currently on " + getVersion());
                        getLogger().warning("Download it at: " + releaseUrl);
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "Download it at: " + releaseUrl);

                    } else {
                        getLogger().warning("You have the latest version of " + getName() + ".");
                        getServer().broadcastMessage(ChatColor.GREEN + "You have the latest version of Learning Spigot!");

                    }
                } else {
                    getLogger().warning("Invalid response from GitHub API.");
                }
            } else {
                getLogger().warning("Could not check for updates: Response Code " + responseCode);
            }
        } catch (Exception e) {
            getLogger().warning("Unable to check for updates: " + e.getMessage());
        }
    }




}

