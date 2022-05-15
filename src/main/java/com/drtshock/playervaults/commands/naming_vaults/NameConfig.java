package com.drtshock.playervaults.commands.naming_vaults;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.util.ConfigurationFile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class NameConfig {

    private FileConfiguration config;
    private ConfigurationFile file;
    // Uuid - NamesPVs
    private Map<String, NamePv> uuidToNamePv;
    private final PlayerVaults plugin;

    public NameConfig(PlayerVaults plugin) {
        file = new ConfigurationFile(plugin, "name.yml");
        config = file.getCustomConfig();
        loadUuidToNamePv();
        setValues();
        this.plugin = plugin;
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
            namePv.addPvName(plugin, player, number, name);
            config.set(uuid, setNames(namePv));
        }else {
            NamePv namePv = new NamePv();
            namePv.addPvName(plugin, player, number, name);
            uuidToNamePv.put(uuid, namePv);
            config.set(uuid, setNames(namePv));
        }
        file.saveConfig();
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
            namePv.removePvName(plugin, player, name);
            config.set(uuid, setNames(namePv));
        }else {
            NamePv namePv = new NamePv();
            namePv.removePvName(plugin, player, name);
            config.set(uuid, setNames(namePv));
        }
        file.saveConfig();
    }

    public void removeUser(String uuid) {
        if(config.get(uuid) == null) return;
        config.set(uuid, null);
        uuidToNamePv.remove(uuid);
        file.saveConfig();
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
    private String pvNameAlreadyExist;
    private String successAdded;
    private String noPvNames;

    public void setValues() {
        ConfigurationSection section = config.getConfigurationSection("lang");

        listTitle = ChatColor.translateAlternateColorCodes('&', section.getString("list-title"));
        List<String> list = section.getStringList("help-message");
        helpMessage = new ArrayList<>();
        for(String i : list) {
            helpMessage.add(ChatColor.translateAlternateColorCodes('&',i));
        }
        noPermVault = ChatColor.translateAlternateColorCodes('&',section.getString("no-perm-vault"));
        noPerm = ChatColor.translateAlternateColorCodes('&',section.getString("no-perm"));
        pvAddSyntax = ChatColor.translateAlternateColorCodes('&',section.getString("pv-add-syntax"));
        pvRemoveSyntax = ChatColor.translateAlternateColorCodes('&',section.getString("pv-remove-syntax"));
        pvListSyntax = ChatColor.translateAlternateColorCodes('&',section.getString("pv-list-syntax"));
        pvClearSyntax = ChatColor.translateAlternateColorCodes('&',section.getString("pv-clear-syntax"));
        clearedPv = ChatColor.translateAlternateColorCodes('&',section.getString("cleared-pv"));
        exampleList = ChatColor.translateAlternateColorCodes('&',section.getString("example-list"));
        updatedPvName = ChatColor.translateAlternateColorCodes('&',section.getString("updated-pvname"));
        successRemoved = ChatColor.translateAlternateColorCodes('&',section.getString("successfully-remove"));
        noPvNamed = ChatColor.translateAlternateColorCodes('&',section.getString("no-pv-named"));
        pvNameAlreadyExist = ChatColor.translateAlternateColorCodes('&',section.getString("pv-name-already-exist"));
        successAdded = ChatColor.translateAlternateColorCodes('&',section.getString("success-added"));
        noPvNames = ChatColor.translateAlternateColorCodes('&', section.getString("no-pv-names"));
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

    public String getPvNameAlreadyExist() {
        return pvNameAlreadyExist;
    }

    public String getSuccessAdded() {
        return successAdded;
    }

    public String getNoPvNames() {
        return noPvNames;
    }
}
