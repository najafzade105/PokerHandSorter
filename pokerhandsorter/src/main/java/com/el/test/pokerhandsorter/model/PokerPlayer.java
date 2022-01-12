package com.el.test.pokerhandsorter.model;

import java.util.ArrayList;
import java.util.List;


public class PokerPlayer {

    private String playerName;
    private List<PlayHand> hands;

    public PokerPlayer() {
        this.hands = new ArrayList<>();
    }

    public PokerPlayer(String playerName) {
        this.hands = new ArrayList<>();
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<PlayHand> getHands() {
        return hands;
    }

    public void setHands(List<PlayHand> hands) {
        this.hands = hands;
    }

    @Override
    public String toString() {
        return "PokerPlayer{" +
                "playerName='" + playerName + '\'' +
                ", hands=" + hands +
                '}';
    }


}
