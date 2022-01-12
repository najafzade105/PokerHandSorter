package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public class StraightFlushEvaluationImpl implements StraightEvaluation, FlushEvaluation {
    @Override
    public PokerRankEnum evaluate(PlayCard... playCards) {
        PokerRankEnum result = PokerRankEnum.HIGH_CARD;

        if (StraightEvaluation.super.evaluate(playCards).equals(PokerRankEnum.STRAIGHT) &&
                FlushEvaluation.super.evaluate(playCards).equals(PokerRankEnum.FLUSH)) {
            result = PokerRankEnum.STRAIGHT_FLUSH;
        }
        return result;
    }

    @Override
    public PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        return StraightEvaluation.super.breakTie(hand1, hand2);
    }
}

