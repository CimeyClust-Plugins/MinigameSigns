package com.cimeyclust.signs.api;

import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.Plugin;
import com.cimeyclust.signs.MinigameSigns;
import com.cimeyclust.signs.SignDetails;
import com.cimeyclust.signs.Signs;

import java.util.ArrayList;
import java.util.HashMap;

public class SignsAPI {
    public static BlockEntitySign createMinigameSign(Plugin yourPluginInstance, Location location,
                                                     String minigameName, String onInteractCommand, int playerCount,
                                                     int maxPlayerCount) {
        return createMinigameSign(yourPluginInstance, location, minigameName,
                onInteractCommand, playerCount, maxPlayerCount, "");
    }

    public static BlockEntitySign createMinigameSign(Plugin yourPluginInstance, Location location,
                                                     String minigameName, String onInteractCommand, int playerCount,
                                                     int maxPlayerCount, String additionalText) {
        BlockEntitySign sign = (BlockEntitySign) location.getLevel().getBlockEntity(location);

        if (sign == null)
            throw new IllegalArgumentException("§cThe location given, doesn't contain a sign!");

        Signs signs = Signs.getSigns(yourPluginInstance);
        if (signs == null) signs = new Signs(yourPluginInstance);

        signs.getPluginSigns().put(sign, new SignDetails(minigameName, onInteractCommand,
                additionalText, maxPlayerCount, playerCount));

        MinigameSigns.getInstance().getConfigManager().addSign(yourPluginInstance.getName(), signs.getPluginSigns().size(),
                location, minigameName, onInteractCommand, additionalText, maxPlayerCount);


        // Edit sign to the wanted values and create the text fort hat reason
        String[] text = {
                minigameName,
                "§g[§3" + playerCount + " §g/ §3" + maxPlayerCount + "§g]",
                additionalText
        };

        sign.setText(text);

        return sign;
    }

    public static void updateMinigameSignName(Plugin yourPluginInstance, BlockEntitySign sign, String minigameName) {
        SignDetails signDetails = Signs.getSigns(yourPluginInstance).getPluginSigns().get(sign);

        updateMinigameSign(
                yourPluginInstance,
                sign,
                minigameName,
                signDetails.getInteractCommand(),
                signDetails.getPlayerCount(),
                signDetails.getMaxPlayers(),
                signDetails.getAdditionalInformation()
        );
    }

    public static void updateMinigameSignCommand(Plugin yourPluginInstance, BlockEntitySign sign, String command) {
        SignDetails signDetails = Signs.getSigns(yourPluginInstance).getPluginSigns().get(sign);

        updateMinigameSign(
                yourPluginInstance,
                sign,
                signDetails.getMinigameName(),
                command,
                signDetails.getPlayerCount(),
                signDetails.getMaxPlayers(),
                signDetails.getAdditionalInformation()
        );
    }

    public static void updateMinigameSignPlayerCount(Plugin yourPluginInstance, BlockEntitySign sign, int playerCount) {
        SignDetails signDetails = Signs.getSigns(yourPluginInstance).getPluginSigns().get(sign);

        updateMinigameSign(
                yourPluginInstance,
                sign,
                signDetails.getMinigameName(),
                signDetails.getInteractCommand(),
                playerCount,
                signDetails.getMaxPlayers(),
                signDetails.getAdditionalInformation()
        );
    }

    public static void updateMinigameSignMaxPlayerCount(Plugin yourPluginInstance, BlockEntitySign sign, int maxPlayerCount) {
        SignDetails signDetails = Signs.getSigns(yourPluginInstance).getPluginSigns().get(sign);

        updateMinigameSign(
                yourPluginInstance,
                sign,
                signDetails.getMinigameName(),
                signDetails.getInteractCommand(),
                signDetails.getPlayerCount(),
                maxPlayerCount,
                signDetails.getAdditionalInformation()
        );
    }

    public static void updateMinigameSignAdditionalText(Plugin yourPluginInstance, BlockEntitySign sign, String additionalText) {
        SignDetails signDetails = Signs.getSigns(yourPluginInstance).getPluginSigns().get(sign);

        updateMinigameSign(
                yourPluginInstance,
                sign,
                signDetails.getMinigameName(),
                signDetails.getInteractCommand(),
                signDetails.getPlayerCount(),
                signDetails.getMaxPlayers(),
                additionalText
        );
    }

    public static void updateMinigameSign(Plugin yourPluginInstance, BlockEntitySign sign, String minigameName,
                                          String command, int playerCount, int maxPlayerCount, String additional) {

        if (sign == null)
            throw new IllegalArgumentException("§cThe given sign, doesn't exist!");

        Signs signs = Signs.getSigns(yourPluginInstance);

        // Edit Sign Details
        SignDetails signDetails = signs.getPluginSigns().get(sign);

        signDetails
                .setMinigameName(minigameName)
                .setInteractCommand(command)
                .setPlayerCount(playerCount)
                .setMaxPlayers(maxPlayerCount)
                .setAdditionalInformation(additional);

        String[] text = {
                minigameName,
                "§g[§3" + playerCount + " §g/ §3" + maxPlayerCount + "§g]",
                additional
        };

        sign.setText(text);

        // Save Sign Details in Config
        // Name
        MinigameSigns.getInstance().getConfigManager().updateSign(
                yourPluginInstance.getName(),
                signs.getPluginSigns().size(),
                "name",
                minigameName
        );

        // Command
        MinigameSigns.getInstance().getConfigManager().updateSign(
                yourPluginInstance.getName(),
                signs.getPluginSigns().size(),
                "command",
                command
        );

        // Additional text
        MinigameSigns.getInstance().getConfigManager().updateSign(
                yourPluginInstance.getName(),
                signs.getPluginSigns().size(),
                "text",
                additional
        );

        // Max player count
        MinigameSigns.getInstance().getConfigManager().updateSign(
                yourPluginInstance.getName(),
                signs.getPluginSigns().size(),
                "max-players",
                maxPlayerCount
        );
    }
}
