package de.raidcraft.rchome;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Author: Philip
 * Date: 13.11.12 - 20:00
 * Description:
 */
public class WorldGuardUtil {

    public final static WorldGuardUtil INST = new WorldGuardUtil();
    private WorldGuardPlugin worldGuard;

    public WorldGuardUtil() {

        worldGuard = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
    }

    public boolean isMember(Player player) {

        ApplicableRegionSet regions = worldGuard.getRegionManager(player.getLocation().getWorld()).getApplicableRegions(player.getLocation());
        if(regions.size() == 0) {
            return false;
        }
        for (ProtectedRegion region : regions) {
            if(region.getMembers().contains(player.getName()) || region.getOwners().contains(player.getName())) {
                return true;
            }
        }
        return false;
    }
}
