package com.drtshock.playervaults.commands.naming_vaults;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.vaultmanagement.VaultOperations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PvNameCommand implements CommandExecutor, TabCompleter {

    public static List<String> commands = new ArrayList<>();
    private PlayerVaults plugin;

    public PvNameCommand(PlayerVaults plugin) {
        SubCommand.loadCommands(plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) {
            plugin.getLogger().info("You are not a player");
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("pvname")) {

            return true;
        }

        if(args.length == 0) {
            for(String i : plugin.getNameConfig().getHelpMessage()) {
                player.sendMessage(i);
            }
            return true;
        }

        String command = args[0];
        if (commands.contains(command)) {
            SubCommand subCommand = SubCommand.getCommands().get(command);
            if (subCommand.getPermission() != null) {
                if (!sender.hasPermission(subCommand.getPermission())) {
                    plugin.getCommand(plugin.getNameConfig().getNoPerm());
                    return true;
                }
            }
            subCommand.onCommand(sender, Arrays.copyOfRange(args,1, args.length));
            return true;
        }

        for(String i : plugin.getNameConfig().getHelpMessage()) {
            player.sendMessage(i);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
