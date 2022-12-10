package me.david.LearningSpigot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.*;
import org.bukkit.plugin.Plugin;


public class permissionsController extends PermissibleBase {

    private final Permissible permissible;

    private final Plugin plugin;

    public permissionsController(Permissible permissible, Plugin plugin) {
        super(permissible);
        this.permissible = permissible;
        this.plugin = plugin;


    }
    @Override
    public boolean hasPermission(String permission) {
        if (super.hasPermission(permission) || hasPermissionAttachment(permission)) {
            return true;

        }

        if (permissible instanceof Player) {
            Player player = (Player) permissible;
            return hasGroupPermission(player, permission);
        }

        return false;
    }

    private boolean hasPermissionAttachment(String permission) {
        for (PermissionAttachmentInfo attachmentInfo : permissible.getEffectivePermissions()) {
            PermissionAttachment attachment = attachmentInfo.getAttachment();
            if (attachment != null && attachment.getPermissions().containsKey(permission)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasGroupPermission(Player player, String permission) {
        // Get the player's group
        String group = getPlayerGroup(player);

        // Check if the group has the permission
        Permission groupPermission = Bukkit.getPluginManager().getPermission(group);
        if (groupPermission != null && groupPermission.getChildren().containsKey(permission)) {
            return true;
        }

        // The group doesn't have the permission
        return false;
    }

    private String getPlayerGroup(Player player) {
        //TODO
        return "";
    }


}
