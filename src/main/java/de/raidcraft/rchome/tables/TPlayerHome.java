package de.raidcraft.rchome.tables;

import de.raidcraft.RaidCraft;
import de.raidcraft.rchome.RCHomePlugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Philip on 18.01.2016.
 */
@Getter
@Setter
@Entity
@Table(name = "rchome_player_homes")
public class TPlayerHome {

    @Id
    private int id;
    private String player;
    private UUID playerId;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private String world;
    @Version
    private Long version;

    public static Location getHome(Player player) {

        RCHomePlugin plugin = RaidCraft.getComponent(RCHomePlugin.class);

        TPlayerHome tPlayerHome = plugin.getDatabase().find(TPlayerHome.class).where()
                .eq("player_id", player.getUniqueId())
                .eq("world", player.getLocation().getWorld().getName()).findUnique();
        if(tPlayerHome == null) {
            return null;
        }

        return new Location(player.getWorld(),
                tPlayerHome.getX(),
                tPlayerHome.getY(),
                tPlayerHome.getZ(),
                tPlayerHome.getYaw(),
                tPlayerHome.getPitch());
    }

    public static void setHome(Player player) {

        RCHomePlugin plugin = RaidCraft.getComponent(RCHomePlugin.class);

        deleteHome(player);

        TPlayerHome tPlayerHome = new TPlayerHome();
        tPlayerHome.setPlayer(player.getName());
        tPlayerHome.setPlayerId(player.getUniqueId());
        tPlayerHome.setX(player.getLocation().getX());
        tPlayerHome.setY(player.getLocation().getY());
        tPlayerHome.setZ(player.getLocation().getZ());
        tPlayerHome.setPitch(player.getLocation().getPitch());
        tPlayerHome.setYaw(player.getLocation().getYaw());
        tPlayerHome.setWorld(player.getLocation().getWorld().getName());

        plugin.getDatabase().save(tPlayerHome);
    }

    public static void deleteHome(Player player) {

        RCHomePlugin plugin = RaidCraft.getComponent(RCHomePlugin.class);

        TPlayerHome tPlayerHome = plugin.getDatabase().find(TPlayerHome.class).where()
                .eq("player_id", player.getUniqueId())
                .eq("world", player.getLocation().getWorld().getName()).findUnique();
        if(tPlayerHome == null) {
            return;
        }

        plugin.getDatabase().delete(tPlayerHome);
    }
}
