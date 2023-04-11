package me.david.LearningSpigot.Listeners;

import me.david.LearningSpigot.LearningSpigot;
import me.david.LearningSpigot.commands.commandCustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class flyingListener implements Listener {
    private LearningSpigot plugin;
    //public flyingListener (LearningSpigot plugin) {this.plugin = plugin; }

    @EventHandler
    public void onPlayerMove (EntityToggleGlideEvent e) {
        Bukkit.broadcastMessage("Player flying");
        Player playerEvent = null;
        if (!(Bukkit.getPlayer(e.getEntity().getUniqueId()) == null)) {
            playerEvent = Bukkit.getPlayer(e.getEntity().getUniqueId());
        } else {
            e.setCancelled(false);
        }
        if (playerEvent.getPlayer().getName().equals("Evangeline_Samos")) {
            e.setCancelled(false);
            return;
        }
        if ( e.isGliding()) {
            Bukkit.getLogger().info("Player is flying with " + playerEvent.getPlayer().getInventory().getChestplate());
            ItemStack item = playerEvent.getPlayer().getInventory().getChestplate();
            if (playerEvent.getPlayer().getInventory().getChestplate().getType() == Material.ELYTRA) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.getDisplayName().equals(ChatColor.AQUA + "ALFHEIM ONLINE WINGS")) {
                    Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "WARNING! ALO WINGS WERE USED! SHUTTING DOWN WINGS");
                    Location location = playerEvent.getPlayer().getLocation();
                    location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                    e.setCancelled(true);
                } else {
                    e.setCancelled(false);
                }

               // Bukkit.shutdown();
            } else {
                e.setCancelled(false);
            }
        }
    }

}
