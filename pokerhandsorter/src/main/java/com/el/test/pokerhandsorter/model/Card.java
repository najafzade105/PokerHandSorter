package com.el.test.pokerhandsorter.model;

import com.el.test.pokerhandsorter.util.Validator;

import java.util.Arrays;


public class Card {

    private int value;
    private int suit;

    private Validator validator = new Validator();

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(String card) {
        if (validator.validateCard(card)) {
            this.value = card.charAt(0);
            this.suit = card.charAt(1);
        } else {
            throw new RuntimeException("Invalid Card!");
        }

    }

    public Card[] fromString(String card) {
        if (validator.validateCard(card)) {
            return Arrays.stream(card.trim().split("\\s"))
                    .map(Card::new)
                    .toArray(Card[]::new);
        } else {
            throw new RuntimeException("Invalid Card!");
        }
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }



    public String getCard() {
        return value + String.valueOf(suit);
    }

    @Override
    public String toString() {
        return String.valueOf(value) + suit;
    }
}
