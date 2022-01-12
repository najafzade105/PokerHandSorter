package com.el.test.pokerhandsorter.model;

import java.util.List;

public class PokerGame {
    private List<PlayHand> hands;
    private int winnerHand;
    private boolean tie;
    private PokerRankEnum tieRank;

    public PokerGame(List<PlayHand> hands) {
        this.hands = hands;
    }

    public List<PlayHand> getHands() {
        return hands;
    }

    public void setHands(List<PlayHand> hands) {
        this.hands = hands;
    }

    public int getWinnerHand() {
        return winnerHand;
    }

    public void setWinnerHand(int winnerHand) {
        this.winnerHand = winnerHand;
    }

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }

    public PokerRankEnum getTieRank() {
        return tieRank;
    }

    public void setTieRank(PokerRankEnum tieRank) {
        this.tieRank = tieRank;
    }
}
