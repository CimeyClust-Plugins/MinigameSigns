package com.cimeyclust.signs;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.plugin.Plugin;

import java.util.*;

public class Signs {
    private Plugin pluginInstance;
    private Map<BlockEntitySign, SignDetails> pluginSigns;
    public static List<Signs> signs;

    public Signs(Plugin pluginInstance) {
        this.pluginInstance = pluginInstance;
        this.pluginSigns = new LinkedHashMap<>();

        Signs.signs.add(this);
    }

    public static Signs getSigns(Plugin pluginInstance) {
        for (Signs sign : Signs.signs)
            if (sign.getPluginInstance().getName().equals(pluginInstance.getName())) return sign;

        return null;
    }

    public static Signs getSigns(BlockEntitySign blockSign) {
        for (Signs sign : Signs.signs)
            if (sign.getPluginSigns().keySet().contains(blockSign)) return sign;

        return null;
    }

    public Map<BlockEntitySign, SignDetails> getPluginSigns() {
        return pluginSigns;
    }

    public Plugin getPluginInstance() {
        return pluginInstance;
    }
}
