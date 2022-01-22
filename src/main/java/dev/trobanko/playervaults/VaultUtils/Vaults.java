package dev.trobanko.playervaults.VaultUtils;

import dev.trobanko.playervaults.ConfigurationHandler.VaultConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vaults {

    private static HashMap<String, ItemStack[]> vault = new HashMap<>();
    private static HashMap<String, Integer> maxVaultsAllows = new HashMap<>();

    public static ItemStack[] getItemsInSpecficVault(Player player, int vaultNumber){
        return vault.get(player.getUniqueId() + "_" + vaultNumber);
    }

    public static void putItemsInVault(Player player, ItemStack[] items, int vaultNumber){
        vault.put(player.getUniqueId() + "_" + vaultNumber, items);
    }

    public static boolean playerHasVault(Player player, int vaultNumber){
        return vault.containsKey(player.getUniqueId() + "_" +vaultNumber);
    }

    public static void saveVaults(){
        for(Map.Entry<String, ItemStack[]> entry : vault.entrySet()){
            VaultConfig.get().set("players.vaults." + entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Integer> entry : maxVaultsAllows.entrySet()){
            VaultConfig.get().set("players.max_vaults_allowed." + entry.getKey(), entry.getValue());
        }

        VaultConfig.save();
    }


    public static int getMaxVaults(Player player){
        return maxVaultsAllows.get(player.getUniqueId().toString());
    }

    public static void setMaxVaults(Player player, int max){
        maxVaultsAllows.put(player.getUniqueId().toString(), max);
    }

    public static boolean hasMaxVaults(Player player){
        return maxVaultsAllows.containsKey(player.getUniqueId().toString());
    }

    public static void restoreVaults(){
        VaultConfig.get().getConfigurationSection("players.vaults").getKeys(false).forEach(key -> {
            @SuppressWarnings("unchecked")
            ItemStack[] vaultContents = ((List<ItemStack>) VaultConfig.get().get("players.vaults." + key)).toArray(new ItemStack[0]);
            vault.put(key, vaultContents);
        });

        VaultConfig.get().getConfigurationSection("players.max_vaults_allowed").getKeys(false).forEach(key -> {
            int value = VaultConfig.get().getInt("players.max_vaults_allowed." + key);
            maxVaultsAllows.put(key, value);
        });
    }

    public static void deleteVaults(){
        VaultConfig.get().set("players.vaults", null);
        VaultConfig.get().set("players.max_vaults_allowed", null);
        VaultConfig.save();
    }



}
