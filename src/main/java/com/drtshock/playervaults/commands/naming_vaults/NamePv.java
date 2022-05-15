package com.drtshock.playervaults.commands.naming_vaults;

import com.drtshock.playervaults.PlayerVaults;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class NamePv {

    private Map<Integer, String> pvNames;

    public NamePv() {
        pvNames = new HashMap<>();
    }

    public void addPvName(PlayerVaults plugin, Player player, int number, String name) {
        if(pvNames.get(number) != null && name != null) {
            player.sendMessage(plugin.getNameConfig().getUpdatedPvName()
                    .replaceAll("<last_name>", pvNames.get(number))
                    .replaceAll("<new_name>", name));
            pvNames.replace(number, name);
            return;
        }

        for(String i : pvNames.values()) {
            if(i.equals(name)) {
                player.sendMessage(plugin.getNameConfig().getPvNameAlreadyExist().replaceAll("<name>", name));
                return;
            }
        }

        pvNames.put(number, name);
        player.sendMessage(plugin.getNameConfig().getSuccessAdded()
                .replaceAll("<name>", name)
                .replaceAll("<number>", String.valueOf(number)));
    }

    public void addPvName(int number, String name) {
        if(pvNames.get(number) != null && name == null) {
            return;
        }
        pvNames.put(number, name);
    }

    public void removePvName(PlayerVaults plugin, Player player, String name) {
        for(int i : pvNames.keySet()) {
            if(name.equals(pvNames.get(i))) {
                pvNames.remove(i, name);
                player.sendMessage(plugin.getNameConfig().getSuccessRemoved().replaceAll("<name>", name));
                return;
            }
        }

        player.sendMessage(plugin.getNameConfig().getNoPvNamed().replaceAll("<name>", name));
    }

    public Map<Integer, String> getPvNames() {
        return pvNames;
    }
}
