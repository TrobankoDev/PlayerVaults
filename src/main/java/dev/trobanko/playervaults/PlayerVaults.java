package dev.trobanko.playervaults;

import dev.trobanko.playervaults.ConfigurationHandler.VaultConfig;
import dev.trobanko.playervaults.Listeners.InventoryCloseListener;
import dev.trobanko.playervaults.VaultUtils.Vaults;
import dev.trobanko.playervaults.commands.PlayerVaultCommand;
import dev.trobanko.playervaults.commands.SetMaxVaultsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerVaults extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("vault").setExecutor(new PlayerVaultCommand());
        getCommand("setmaxvaults").setExecutor(new SetMaxVaultsCommand());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
        VaultConfig.setup(this);
        VaultConfig.save();

        if(VaultConfig.configExists()) {
            Vaults.restoreVaults();
            Vaults.deleteVaults();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Vaults.saveVaults();

    }
}
