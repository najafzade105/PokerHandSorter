package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.Card;

public abstract class EvaluationTest {

    protected Card[] preparePlayCardTestData(String... cards) {
        Card[] data = new Card[5];
        data[0] = new Card(cards[0]);
        data[1] = new Card(cards[1]);
        data[2] = new Card(cards[2]);
        data[3] = new Card(cards[3]);
        data[4] = new Card(cards[4]);
        return data;
    }
}
