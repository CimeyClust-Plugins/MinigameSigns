package com.cimeyclust.signs;

public class SignDetails {
    private String minigameName;
    private String interactCommand;
    private String additionalInformation;
    private int maxPlayers;
    private int playerCount;

    public SignDetails(String minigameName, String interactCommand, String additionalInformation, int maxPlayers, int playerCount) {
        this.minigameName = minigameName;
        this.interactCommand = interactCommand;
        this.additionalInformation = additionalInformation;
        this.maxPlayers = maxPlayers;
        this.playerCount = playerCount;
    }

    public String getMinigameName() {
        return minigameName;
    }

    public String getInteractCommand() {
        return interactCommand;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
