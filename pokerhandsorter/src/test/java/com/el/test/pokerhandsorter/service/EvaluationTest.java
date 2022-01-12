package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.PlayCard;

public abstract class EvaluationTest {

    protected PlayCard[] preparePlayCardTestData(String... cards) {
        PlayCard[] data = new PlayCard[5];
        data[0] = new PlayCard(cards[0]);
        data[1] = new PlayCard(cards[1]);
        data[2] = new PlayCard(cards[2]);
        data[3] = new PlayCard(cards[3]);
        data[4] = new PlayCard(cards[4]);
        return data;
    }
}
