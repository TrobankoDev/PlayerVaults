package dev.trobanko.playervaults.commands;

import dev.trobanko.playervaults.VaultUtils.Vaults;
import dev.trobanko.playervaults.VaultUtils.VaultsInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerVaultCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if(!player.hasPermission("playervaults.openvault")){
            player.sendMessage(ChatColor.RED + "You do not have permission to open vaults.");
            return true;
        }

        VaultsInventory playerVault = new VaultsInventory();

        if(args.length < 1) {
            player.sendMessage(ChatColor.GREEN + "Opening vault...");


            player.openInventory(playerVault.getPlayerVault(player, 1));

            return true;
        }
        else {
            int vaultToOpen = 0;
            try {
                vaultToOpen = Integer.parseInt(args[0]);
            }catch (Exception e){
                player.sendMessage(ChatColor.RED + "Invalid Vault!");
                return true;
            }

            if(player.hasPermission("playervaults.openany")){
                player.sendMessage(ChatColor.GREEN + "Opening vault...");
                player.openInventory(playerVault.getPlayerVault(player, vaultToOpen));
                return true;
            }

            if(!Vaults.hasMaxVaults(player)){
                if (vaultToOpen == 1){
                    player.sendMessage(ChatColor.GREEN + "Opening vault...");
                    player.openInventory(playerVault.getPlayerVault(player, 1));
                } else
                player.sendMessage(ChatColor.RED + "You do not have access to vault #" + vaultToOpen);
                return true;
            }

            if(Vaults.getMaxVaults(player) < vaultToOpen){
                player.sendMessage(ChatColor.RED + "You do not have access to vault #" + vaultToOpen);
                return true;
            }
            player.sendMessage(ChatColor.GREEN + "Opening vault...");
            player.openInventory(playerVault.getPlayerVault(player, vaultToOpen));
        }
        return true;
    }
}
