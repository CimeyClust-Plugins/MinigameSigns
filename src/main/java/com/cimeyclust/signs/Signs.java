package com.cimeyclust.signs;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

public class Signs {
    private Plugin pluginInstance;
    private Map<BlockEntitySign, SignDetails> pluginSigns;
    public static List<Signs> signs;

    public Signs(Plugin pluginInstance, Map<BlockEntitySign, SignDetails> signs) {
        this.pluginInstance = pluginInstance;
        this.pluginSigns = signs;

        Signs.signs.add(this);
    }

    public static Signs getSigns(Plugin pluginInstance) {
        for (Signs sign : Signs.signs)
            if (sign.getPluginInstance().getName().equals(pluginInstance.getName())) return sign;

        return null;
    }

    public Map<BlockEntitySign, SignDetails> getPluginSigns() {
        return pluginSigns;
    }

    public Plugin getPluginInstance() {
        return pluginInstance;
    }
}
