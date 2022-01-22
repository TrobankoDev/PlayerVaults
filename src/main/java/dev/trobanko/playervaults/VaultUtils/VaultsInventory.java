package dev.trobanko.playervaults.VaultUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class VaultsInventory {

    public Inventory getPlayerVault(Player player, int vaultNumber){
        Inventory vault = Bukkit.createInventory(player, 54,ChatColor.RED + player.getDisplayName() + "'s Vault #" + vaultNumber);
        if(Vaults.playerHasVault(player, vaultNumber)) {
            vault.setContents(Vaults.getItemsInSpecficVault(player, vaultNumber));
        }
        return vault;
    }
}
