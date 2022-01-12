package com.el.test.pokerhandsorter.model;

import com.el.test.pokerhandsorter.util.Validator;

import java.util.Arrays;

import static com.el.test.pokerhandsorter.service.PokerHandEvaluation.POKER_RANK_SIZE;


public class PlayHand {

    private boolean[] rank; //element 0 is ignored. Rank starts from index 1 for simplicity

    private PlayCard[] hand;

    private Validator validator = new Validator();

    public PlayHand(PlayCard[] hand) {
        if (validator.validateHand(hand)) {
            this.hand = hand;
        } else {
            throw new RuntimeException("Invalid Hand!");
        }
        rank = new boolean[POKER_RANK_SIZE];
    }

    public PlayCard[] getHand() {
        return hand;
    }

    public boolean[] getRank() {
        return rank;
    }

    public void setRank(PokerRankEnum rank) {
        this.rank[rank.getValue()] = true;
    }

    @Override
    public String toString() {
        return "PlayHand{" + Arrays.toString(hand) + "}";
    }
}
