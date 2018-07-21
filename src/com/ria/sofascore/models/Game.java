package com.ria.sofascore.models;

import java.util.ArrayList;

public class Game {

    private String gameURL;
    private String categoy;
    private String standing;
    private ArrayList<Player> players = new ArrayList<>();

    public String getCategoy() {
        return categoy;
    }

    public void setCategoy(String categoy) {
        this.categoy = categoy;
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
