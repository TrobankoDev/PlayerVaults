package dev.trobanko.playervaults.Listeners;

import dev.trobanko.playervaults.VaultUtils.Vaults;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.apache.commons.lang.ArrayUtils;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        if (!e.getView().getTitle().contains(ChatColor.stripColor(player.getDisplayName() + "'s Vault"))) return;
        String vaultName = e.getView().getTitle();
        String[] split = vaultName.split("#");
        if(ArrayUtils.isEmpty(e.getInventory().getContents())) return;
        int vaultNumber = Integer.parseInt(split[1]);
        Vaults.putItemsInVault(player, e.getInventory().getContents(), vaultNumber);
    }

}
