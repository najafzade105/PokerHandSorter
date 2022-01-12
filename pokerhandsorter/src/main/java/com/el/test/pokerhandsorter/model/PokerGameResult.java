package com.el.test.pokerhandsorter.model;

public class PokerGameResult {

    private int player1GameWon;
    private int player2GameWon;

    public int getPlayer1GameWon() {
        return player1GameWon;
    }

    public void setPlayer1GameWon(int player1GameWon) {
        this.player1GameWon = player1GameWon;
    }

    public void incrementPlayer1Won() {
        this.player1GameWon++;
    }

    public int getPlayer2GameWon() {
        return player2GameWon;
    }

    public void setPlayer2GameWon(int player2GameWon) {
        this.player2GameWon = player2GameWon;
    }

    public void incrementPlayer2Won() {
        this.player2GameWon++;
    }
}
