package com.cimeyclust.signs;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.plugin.PluginBase;
import com.cimeyclust.signs.listeners.SignInteractListener;
import com.cimeyclust.signs.storage.yaml.ConfigManager;

import java.util.ArrayList;
import java.util.Map;

public class MinigameSigns extends PluginBase {
    private Map<PluginBase, BlockEntitySign> signs;
    private static MinigameSigns plugin;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;
        Signs.signs = new ArrayList<>();
        this.configManager = new ConfigManager(this);

        // Register listener
        this.getServer().getPluginManager().registerEvents(new SignInteractListener(this), this);

        this.getServer().getScheduler().scheduleDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                getLogger().info("§aLoading minigame signs...");
                configManager.loadSigns();
            }
        }, 2);

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

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
