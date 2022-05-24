package com.cimeyclust.signs.api;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.Plugin;
import com.cimeyclust.signs.SignDetails;
import com.cimeyclust.signs.Signs;

import java.util.ArrayList;
import java.util.HashMap;

public class SignsAPI {
    public static BlockEntitySign createMinigameSign(Plugin yourPluginInstance, Location location,
                                                     String minigameName, String onInteractCommand, int playerCount,
                                                     int maxPlayerCount, String additionalText) {
        return createMinigameSign(yourPluginInstance, location, minigameName,
                onInteractCommand, playerCount, maxPlayerCount, "");
    }

    public static BlockEntitySign createMinigameSign(Plugin yourPluginInstance, Location location,
                                                     String minigameName, String onInteractCommand, int playerCount,
                                                     int maxPlayerCount) {
        BlockEntitySign sign = (BlockEntitySign) location.getLevel().getBlockEntity(location);

        if (sign == null) {
            throw new IllegalArgumentException("§cThe location given, doesn't contain a sign!");
            return null;
        }

        Signs signs = Signs.getSigns(yourPluginInstance);
        if (signs == null) signs = new Signs(yourPluginInstance, new HashMap<>());

        signs.getPluginSigns().put(sign, new SignDetails(minigameName, onInteractCommand,
                "", maxPlayerCount, 0));




        // Edit sign to the wanted values and create the text fort hat reason

    }
}
