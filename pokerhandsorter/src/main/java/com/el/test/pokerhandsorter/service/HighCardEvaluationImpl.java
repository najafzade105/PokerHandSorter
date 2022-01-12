package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public class HighCardEvaluationImpl implements PokerHandEvaluation {

    @Override
    public PokerRankEnum evaluate(PlayCard... playCards) {
        return PokerRankEnum.HIGH_CARD;
    }
}
