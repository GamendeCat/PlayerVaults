package com.drtshock.playervaults.commands.naming_vaults;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.commands.naming_vaults.subcommands.AddNamePv;
import com.drtshock.playervaults.commands.naming_vaults.subcommands.ClearNamesPv;
import com.drtshock.playervaults.commands.naming_vaults.subcommands.ListNamesPv;
import com.drtshock.playervaults.commands.naming_vaults.subcommands.RemoveNamePv;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public abstract class SubCommand {
    private static HashMap<String, SubCommand> commands;

    public abstract String getSub();
    public abstract String getPermission();
    public abstract String getDescription();
    public abstract String getSyntax();

    public abstract void onCommand(CommandSender sender, String[] args);

    public static void loadCommands(PlayerVaults plugin) {
        commands = new HashMap<>();

        loadCommand(new AddNamePv(plugin), new ClearNamesPv(plugin), new ListNamesPv(plugin), new RemoveNamePv(plugin));
    }

    private static void loadCommand(SubCommand ... subs) {
        for (SubCommand sub: subs) {
            commands.put(sub.getSub(), sub);
            PvNameCommand.commands.add(sub.getSub());
        }
    }

    public static HashMap<String, SubCommand> getCommands() {
        return commands;
    }
}
