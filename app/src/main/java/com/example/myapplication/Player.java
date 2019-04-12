package com.example.myapplication;

public class Player {
    String playerId;
    String playername;
    String teamName;
    String playerType;
    String image;

    public Player(String playerId, String playername, String teamName, String playerType, String image) {
        this.playerId = playerId;
        this.playername = playername;
        this.teamName = teamName;
        this.playerType = playerType;
        this.image = image;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
