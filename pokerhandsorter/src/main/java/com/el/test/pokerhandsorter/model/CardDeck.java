package com.el.test.pokerhandsorter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CardDeck {

    private List<Card> deck;

    public CardDeck() {
        deck = new ArrayList<>(56);
        IntStream.rangeClosed(1,4).forEach(suit -> {

            IntStream.rangeClosed(2,14).collect(rank -> new Card(rank,suit) );
        });
    }
}
