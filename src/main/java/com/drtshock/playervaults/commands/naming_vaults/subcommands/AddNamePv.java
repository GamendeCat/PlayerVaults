package com.drtshock.playervaults.commands.naming_vaults.subcommands;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.commands.naming_vaults.SubCommand;
import com.drtshock.playervaults.vaultmanagement.VaultOperations;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddNamePv extends SubCommand {

    private PlayerVaults plugin;

    public AddNamePv(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getSub() {
        return "add";
    }

    @Override
    public String getPermission() {
        return "pvname.add";
    }

    @Override
    public String getDescription() {
        return "Adding a pv name";
    }

    @Override
    public String getSyntax() {
        return "/pvname add <name> <number>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length < 2) {
            player.sendMessage(plugin.getNameConfig().getPvAddSyntax());
            return;
        }

        int pv;
        try {
            pv = Integer.parseInt(args[1]);
        }catch(NumberFormatException e) {
            player.sendMessage(plugin.getNameConfig().getPvAddSyntax());
            return;
        }

        String pvName = args[0];

        if(!VaultOperations.checkPerms(sender, pv)) {
            player.sendMessage(plugin.getNameConfig().getNoPermVault());
            return;
        }

        plugin.getNameConfig().addPvName(player, player.getUniqueId().toString(), pvName, pv);
    }
}
