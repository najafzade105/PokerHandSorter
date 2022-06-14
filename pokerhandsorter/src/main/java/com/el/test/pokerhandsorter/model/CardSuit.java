package com.el.test.pokerhandsorter.model;

public enum CardSuit {
    SPADE(1,'S'),
    HEART(2,'H'),
    DIAMOND(3,'D'),
    CLUBS(4,'C');

    private int value;
    private char display;

    CardSuit(int value, char display) {
        this.value = value;
        this.display = display;
    }



}
