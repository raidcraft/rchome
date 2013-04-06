package de.raidcraft.rchome.listener;

import de.raidcraft.RaidCraft;
import de.raidcraft.rchome.WorldGuardUtil;
import de.raidcraft.rchome.tables.PlayerHomesTable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Philip
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if(!player.hasPermission("rchome.bed.sethome")) {
            return;
        }

        if(event.getClickedBlock() == null || event.getClickedBlock().getType() != Material.BED_BLOCK) {
            return;
        }

        if(WorldGuardUtil.INST.isMember(player)) {
            player.setBedSpawnLocation(player.getLocation(), true);
            RaidCraft.getTable(PlayerHomesTable.class).setHome(player);
            player.sendMessage(ChatColor.GREEN + "Dein Home wurde gesetzt!");
            return;
        }
        player.sendMessage(ChatColor.RED + "Du kannst dein Home nur auf eigenen Grundstücken setzen!");
    }
}
