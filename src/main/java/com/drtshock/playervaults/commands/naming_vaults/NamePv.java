package com.drtshock.playervaults.commands.naming_vaults;

import com.drtshock.playervaults.PlayerVaults;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NamePv {

    private Map<Integer, String> pvNames;

    public void NamePv() {
        pvNames = new HashMap<>();
    }

    public void addPvName(Player player, int number, String name) {
        if(pvNames.get(number) != null && name == null) {
            player.sendMessage(PlayerVaults.getInstance().getNameConfig().getUpdatedPvName()
                    .replaceAll("<number>", pvNames.get(number)
                            .replaceAll("<name>", name)));
            pvNames.replace(number, name);
            return;
        }
        pvNames.put(number, name);
    }

    public void addPvName(int number, String name) {
        if(pvNames.get(number) != null && name == null) {
            return;
        }
        pvNames.put(number, name);
    }

    public void removePvName(Player player, String name) {
        for(int i : pvNames.keySet()) {
            if(name.equals(pvNames.get(i))) {
                pvNames.remove(i, name);
                player.sendMessage(PlayerVaults.getInstance().getNameConfig().getSuccessRemoved());
                return;
            }
        }

        player.sendMessage(PlayerVaults.getInstance().getNameConfig().getNoPvNamed().replaceAll("<name>", name));
    }

    public Map<Integer, String> getPvNames() {
        return pvNames;
    }
}
