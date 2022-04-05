package com.drtshock.playervaults.commands.naming_vaults.subcommands;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.commands.naming_vaults.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveNamePv extends SubCommand {

    private PlayerVaults plugin;

    public RemoveNamePv(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getSub() {
        return "remove";
    }

    @Override
    public String getPermission() {
        return "pvname.remove";
    }

    @Override
    public String getDescription() {
        return "Remove a Pv name";
    }

    @Override
    public String getSyntax() {
        return "/pvname remove <name>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args[0] == null) {
            //todo send help message
            return;
        }

        String name = args[0];

        plugin.getNameConfig().removePvName(player ,player.getUniqueId().toString(), name);
    }
}
