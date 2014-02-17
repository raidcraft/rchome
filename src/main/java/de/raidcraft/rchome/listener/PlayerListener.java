package de.raidcraft.rchome.listener;

import de.raidcraft.RaidCraft;
import de.raidcraft.rchome.WorldGuardUtil;
import de.raidcraft.rchome.tables.PlayerHomesTable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Philip
 */
public class PlayerListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if(!player.hasPermission("rchome.bed.sethome")) {
            return;
        }

        if(event.getClickedBlock() == null 
            || event.getClickedBlock().getType() != Material.BED_BLOCK
            || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (WorldGuardUtil.INST.isMember(player)) {
            player.setBedSpawnLocation(player.getLocation(), true);
            RaidCraft.getTable(PlayerHomesTable.class).setHome(player);
            player.sendMessage(ChatColor.GREEN + "Dein Home wurde gesetzt!");
            return;
        } else {
            // cancel the interact event to prevent setting the bed spawn location
            event.setCancelled(true);
        }
        player.sendMessage(ChatColor.RED + "Du kannst dein Home nur auf eigenen Grundstücken setzen!");
    }
}