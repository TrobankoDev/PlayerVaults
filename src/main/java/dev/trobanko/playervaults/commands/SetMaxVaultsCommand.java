package dev.trobanko.playervaults.commands;

import dev.trobanko.playervaults.VaultUtils.Vaults;
import dev.trobanko.playervaults.VaultUtils.VaultsInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMaxVaultsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("playervaults.setmaxvaults")){
            player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
            return true;
        }
        if(args.length < 2){
            player.sendMessage(ChatColor.RED + "Incorrect usage. /setmaxvaults [player:amount]");
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null){
            player.sendMessage(ChatColor.RED + "Player " + args[0] + " does not exist!");
            return true;
        }
        int maxVaults = 0;
        try {
            maxVaults = Integer.parseInt(args[1]);
        }catch (Exception e){
            player.sendMessage(ChatColor.RED + "Incorrect usage. /setmaxvaults [player:amount]");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Set maximum allowed vaults for " + target.getDisplayName() + " to " + maxVaults);
        Vaults.setMaxVaults(target, maxVaults);


        return true;
    }
}
