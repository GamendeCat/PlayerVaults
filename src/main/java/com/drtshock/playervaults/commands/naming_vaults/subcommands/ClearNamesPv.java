package com.drtshock.playervaults.commands.naming_vaults.subcommands;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.commands.naming_vaults.SubCommand;
import com.drtshock.playervaults.vaultmanagement.VaultOperations;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearNamesPv extends SubCommand {
    private PlayerVaults plugin;

    public ClearNamesPv(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getSub() {
        return "clear";
    }

    @Override
    public String getPermission() {
        return "pvname.clear";
    }

    @Override
    public String getDescription() {
        return "Clearing all the pv names";
    }

    @Override
    public String getSyntax() {
        return "/pvname clear";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        plugin.getNameConfig().removeUser(player.getUniqueId().toString());

        player.sendMessage(plugin.getNameConfig().getClearedPv());
    }
}
