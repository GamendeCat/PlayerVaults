package com.drtshock.playervaults.commands.naming_vaults;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.util.ConfigurationFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class NameConfig {

    private FileConfiguration config;
    private ConfigurationFile file;
    // Uuid - NamesPVs
    private Map<String, NamePv> uuidToNamePv;

    public NameConfig(PlayerVaults plugin) {
        file = new ConfigurationFile(plugin, "name.yml");
        config = file.getCustomConfig();
        loadUuidToNamePv();
        setValues();
    }

    public NamePv getNamePv(String uuid) {
        return uuidToNamePv.get(uuid);
    }

    public void loadUuidToNamePv() {
        uuidToNamePv = new HashMap<>();
        // uuids
        for(String key : config.getKeys(false)) {
            List<String> uuidToName = config.getStringList(key);
            NamePv namePv = new NamePv();
            for(String str : uuidToName) {
                String[] arr = str.split(":");
                int number = Integer.parseInt(arr[0]);
                String name = arr[1];
                namePv.addPvName(number, name);
            }
            uuidToNamePv.put(key, namePv);
        }
    }

    public void addPvName(Player player, String uuid, String name, int number) {
        if(uuidToNamePv.get(uuid) != null) {
            NamePv namePv = uuidToNamePv.get(uuid);
            namePv.addPvName(player, number, name);
            config.set(uuid, setNames(namePv));
        }else {
            NamePv namePv = new NamePv();
            namePv.addPvName(player, number, name);
            config.set(uuid, setNames(namePv));
        }
    }

    public List<String> setNames(NamePv namePv) {
        List<String> list = new ArrayList<>();
        for(int i : namePv.getPvNames().keySet()) {
            String pvName =  namePv.getPvNames().get(i);
            list.add(i + ":" + pvName);
        }
        return list;
    }

    public void removePvName(Player player, String uuid, String name) {
        if(uuidToNamePv.get(uuid) != null) {
            NamePv namePv = uuidToNamePv.get(uuid);
            namePv.removePvName(player, name);
            config.set(uuid, setNames(namePv));
        }else {
            NamePv namePv = new NamePv();
            namePv.removePvName(player, name);
            config.set(uuid, setNames(namePv));
        }
        file.saveConfig();
    }

    public void removeUser(String uuid) {
        if(config.get(uuid) == null) return;
        config.set(uuid, null);
        uuidToNamePv.remove(uuid);
    }

    private List<String> helpMessage;
    private String noPermVault;
    private String noPerm;
    private String pvAddSyntax;
    private String pvRemoveSyntax;
    private String pvListSyntax;
    private String pvClearSyntax;
    private String clearedPv;
    private String exampleList;
    private String updatedPvName;
    private String successRemoved;
    private String noPvNamed;
    private String listTitle;

    public void setValues() {
        ConfigurationSection section = config.getConfigurationSection("config");

        listTitle = section.getString("list-title");
        helpMessage = section.getStringList("help-message");
        noPermVault = section.getString("no-perm-vault");
        noPerm = section.getString("no-perm");
        pvAddSyntax = section.getString("pv-add-syntax");
        pvRemoveSyntax = section.getString("pv-remove-syntax");
        pvListSyntax = section.getString("pv-list-syntax");
        pvClearSyntax = section.getString("pv-clear-syntax");
        clearedPv = section.getString("cleared-pv");
        exampleList = section.getString("example-list");
        updatedPvName = section.getString("updated-pvname");
        successRemoved = section.getString("successfully-remove");
        noPvNamed = section.getString("no-pv-named");
    }

    public List<String> getHelpMessage() {
        return helpMessage;
    }

    public String getNoPermVault() {
        return noPermVault;
    }

    public String getNoPerm() {
        return noPerm;
    }

    public String getPvAddSyntax() {
        return pvAddSyntax;
    }

    public String getPvRemoveSyntax() {
        return pvRemoveSyntax;
    }

    public String getPvListSyntax() {
        return pvListSyntax;
    }

    public String getPvClearSyntax() {
        return pvClearSyntax;
    }

    public String getClearedPv() {
        return clearedPv;
    }

    public String getExampleList() {
        return exampleList;
    }

    public String getUpdatedPvName() {
        return updatedPvName;
    }

    public String getSuccessRemoved() {
        return successRemoved;
    }

    public String getNoPvNamed() {
        return noPvNamed;
    }

    public String getListTitle() {
        return listTitle;
    }
}
