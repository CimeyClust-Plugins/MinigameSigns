package com.cimeyclust.signs.storage.yaml;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.utils.Config;
import com.cimeyclust.signs.MinigameSigns;
import com.cimeyclust.signs.SignDetails;
import com.cimeyclust.signs.Signs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigManager {
    private MinigameSigns plugin;
    private File signsFile;
    private Config signs;

    public ConfigManager(MinigameSigns plugin) {
        this.plugin = plugin;

        // Save resource if not there
        this.plugin.saveResource("signs.yml", false);

        // Open config file
        this.signsFile = new File(this.plugin.getDataFolder() + "/signs.yml");
        this.signs = new Config(this.signsFile, Config.YAML);
    }

    public void loadSigns() {
        for (String key : this.signs.getKeys()) {
            this.loadSign(key);
        }
    }

    private void loadSign(String key) {

        if (key.split("\\.").length != 3) return;

        // Plugin instance
        String pluginKey = key.split("\\.")[1];
        Plugin pluginInstance = this.plugin.getServer().getPluginManager().getPlugin(pluginKey);

        // Signs
        String signKey = key.split("\\.")[2];
        Location signLocation = new Location();
        signLocation
                .setLevel(this.plugin.getServer().getLevelByName(this.signs.getString(key + ".location.level")))
                .setX(this.signs.getDouble(key + ".location.x"))
                .setY(this.signs.getDouble(key + ".location.y"))
                .setZ(this.signs.getDouble(key + ".location.z"));

        SignDetails signDetails = new SignDetails(
                this.signs.getString(key + ".name"),
                this.signs.getString(key + ".command"),
                this.signs.getString(key + ".text"),
                this.signs.getInt(key + ".max-players"),
                0
        );



        Signs signs = Signs.getSigns(pluginInstance);
        if (signs == null) signs = new Signs(pluginInstance, new HashMap<>());

        signs.getPluginSigns().put((BlockEntitySign) signLocation.getLevel().getBlockEntity(signLocation), signDetails);
    }

    public void addPlugin(String pluginName) {
        this.signs.set("signs." + pluginName, "");
        this.signs.save(this.signsFile);
    }

    public void addSign(String pluginName, Location signLocation, int maxPlayers) {
        // Location
        this.signs.set("signs." + pluginName + "location.x", signLocation.getX());
        this.signs.set("signs." + pluginName + "location.y", signLocation.getY());
        this.signs.set("signs." + pluginName + "location.z", signLocation.getZ());
        this.signs.set("signs." + pluginName + "location.level", signLocation.getLevel().getName());

        // 
        this.signs.save(this.signsFile);
    }
}
