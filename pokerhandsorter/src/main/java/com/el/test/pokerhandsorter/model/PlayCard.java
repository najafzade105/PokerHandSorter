package com.el.test.pokerhandsorter.model;

import com.el.test.pokerhandsorter.util.Validator;

import java.util.Arrays;


public class PlayCard {

    private char value;
    private char suit;

    private Validator validator = new Validator();

    public PlayCard(String card) {
        if (validator.validateCard(card)) {
            this.value = card.charAt(0);
            this.suit = card.charAt(1);
        } else {
            throw new RuntimeException("Invalid Card!");
        }

    }

    public PlayCard[] fromString(String card) {
        if (validator.validateCard(card)) {
            return Arrays.stream(card.trim().split("\\s"))
                    .map(c -> new PlayCard(c))
                    .toArray(PlayCard[]::new);
        } else {
            throw new RuntimeException("Invalid Card!");
        }
    }

    public char getValue() {
        return value;
    }

    public char getSuit() {
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
