package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public class StraightFlushEvaluationImpl implements StraightEvaluation, FlushEvaluation {
    @Override
    public PokerRankEnum evaluate(Card... cards) {
        PokerRankEnum result = PokerRankEnum.HIGH_CARD;

        if (StraightEvaluation.super.evaluate(cards).equals(PokerRankEnum.STRAIGHT) &&
                FlushEvaluation.super.evaluate(cards).equals(PokerRankEnum.FLUSH)) {
            result = PokerRankEnum.STRAIGHT_FLUSH;
        }
        return result;
    }

    @Override
    public PokerGameResult breakTie(Card[] hand1, Card[] hand2) {
        return StraightEvaluation.super.breakTie(hand1, hand2);
    }
}

