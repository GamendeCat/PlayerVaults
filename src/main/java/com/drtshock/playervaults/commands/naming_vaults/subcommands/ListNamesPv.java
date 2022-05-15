package com.drtshock.playervaults.commands.naming_vaults.subcommands;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.commands.naming_vaults.NamePv;
import com.drtshock.playervaults.commands.naming_vaults.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListNamesPv extends SubCommand {

    private PlayerVaults plugin;

    public ListNamesPv(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getSub() {
        return "list";
    }

    @Override
    public String getPermission() {
        return "pvname.list";
    }

    @Override
    public String getDescription() {
        return "Post a list of all your pv names";
    }

    @Override
    public String getSyntax() {
        return "/pvname list";
    }

    // - Number : Name

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        NamePv namePv = plugin.getNameConfig().getNamePv(player.getUniqueId().toString());

        if(namePv == null)  {
            player.sendMessage(plugin.getNameConfig().getNoPvNames());
            return;
        }

        player.sendMessage(plugin.getNameConfig().getListTitle());

        Map<Integer, String> map;

        if(namePv.getPvNames() == null) {
            player.sendMessage(plugin.getNameConfig().getNoPvNames());
            return;
        }

        map = namePv.getPvNames();

        List<Integer> list = new ArrayList<>();

        for(int i : map.keySet()) {
            list.add(i);
        }

        Collections.sort(list);

        for(Integer number : list) {
            String name = map.get(number);

            player.sendMessage(plugin.getNameConfig().getExampleList().replaceAll("<number>", String.valueOf(number)).replaceAll("<name>", name));
        }
    }
}
