package com.el.test.pokerhandsorter.model;

import com.el.test.pokerhandsorter.util.Validator;

import java.util.Arrays;

public class PlayHand {

    private Card[] hand;

    private Validator validator = new Validator();

    public PlayHand(Card[] hand) {
        if (validator.validateHand(hand)) {
            this.hand = hand;
        } else {
            throw new RuntimeException("Invalid Hand!");
        }
    }

    public Card[] getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "PlayHand{" + Arrays.toString(hand) + "}";
    }
}
