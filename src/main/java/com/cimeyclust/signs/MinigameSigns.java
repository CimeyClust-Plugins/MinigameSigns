package com.cimeyclust.signs;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.plugin.PluginBase;
import com.cimeyclust.signs.storage.yaml.ConfigManager;

import java.util.ArrayList;
import java.util.Map;

public class MinigameSigns extends PluginBase {
    private Map<PluginBase, BlockEntitySign> signs;
    private static MinigameSigns plugin;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        Signs.signs = new ArrayList<>();
        this.configManager = new ConfigManager(this);

        this.configManager.loadSigns();

        getLogger().info("§aMinigameSignsAPI has been enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cMinigameSignsAPI has been disabled successfully!");
    }

    public static synchronized MinigameSigns getInstance( ) {
        if (plugin == null) plugin = new MinigameSigns();
        return plugin;
    }
}
