package de.raidcraft.rchome.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import de.raidcraft.rchome.RCHomePlugin;
import org.bukkit.command.CommandSender;

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


    }

    @Command(
            aliases = {"sethome"},
            desc = "Set home"
    )
    @CommandPermissions("rchome.cmd.sethome")
    public void sethome(CommandContext context, CommandSender sender) throws CommandException {


    }
}
