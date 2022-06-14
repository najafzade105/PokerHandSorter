package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public class HighCardEvaluationImpl implements PokerHandEvaluation {

    @Override
    public PokerRankEnum evaluate(Card... cards) {
        return PokerRankEnum.HIGH_CARD;
    }
}
