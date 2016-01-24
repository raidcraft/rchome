package de.raidcraft.rchome.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import de.raidcraft.RaidCraft;
import de.raidcraft.rchome.RCHomePlugin;
import de.raidcraft.rchome.tables.TPlayerHome;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Philip
 */
public class RCHomeCommands {

    public RCHomeCommands(RCHomePlugin module) {

    }

    @Command(
            aliases = {"home"},
            desc = "Go home"
    )
    @CommandPermissions("rchome.cmd.home")
    public void home(CommandContext context, CommandSender sender) throws CommandException {

        Player player = (Player)sender;
        Location home = TPlayerHome.getHome(player);
        if(home == null) {
            throw new CommandException("Du hast noch kein Home gesetzt!");
        }

        if(!player.hasPermission("rchome.home.everywhere")) {
            int radius = RaidCraft.getComponent(RCHomePlugin.class).config.homeRadius;
            if(home.distance(player.getLocation()) > radius) {
                throw new CommandException("Du bist mehr als " + radius + " Blöcke von deinem Home entfernt!");
            }
        }

        player.teleport(home);
        player.sendMessage(ChatColor.GREEN + "Du wurdest an dein Home teleportiert!");
    }

    @Command(
            aliases = {"sethome"},
            desc = "Set home"
    )
    @CommandPermissions("rchome.cmd.sethome")
    public void sethome(CommandContext context, CommandSender sender) throws CommandException {

        Player player = (Player)sender;
        player.setBedSpawnLocation(player.getLocation(), true);
        TPlayerHome.setHome(player);
        player.sendMessage(ChatColor.GREEN + "Dein Home wurde gesetzt!");
    }
}
