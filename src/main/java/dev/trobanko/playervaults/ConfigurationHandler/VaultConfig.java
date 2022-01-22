package dev.trobanko.playervaults.ConfigurationHandler;

import dev.trobanko.playervaults.PlayerVaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class VaultConfig {

    private static File file;
    private static FileConfiguration vaultFile;
    PlayerVaults plugin;

    public VaultConfig(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    public static void setup(PlayerVaults plugin){
        file = new File(plugin.getDataFolder(), "vaultData.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        vaultFile = YamlConfiguration.loadConfiguration(file);

    }

    public static void save(){
        try{
            vaultFile.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static FileConfiguration get(){
        return vaultFile;
    }

    public static boolean configExists(){
        return file.exists() && file.length() != 0;
    }

}
