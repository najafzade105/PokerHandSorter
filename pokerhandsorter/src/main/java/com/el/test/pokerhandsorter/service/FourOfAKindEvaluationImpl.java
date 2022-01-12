package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public class FourOfAKindEvaluationImpl implements OfAKindEvaluation {
    @Override
    public PokerRankEnum evaluate(PlayCard... playCards) {

        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        long pairs = OfAKindEvaluation.super.getTheSameKinds(playCards).entrySet().stream()
                .filter(e -> e.getValue() == 4)
                .count();
        if (pairs > 0) {
            result = PokerRankEnum.FOUR_OF_A_KIND;
        }
        return result;
    }

    @Override
    public PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        PokerGameResult result = new PokerGameResult();
        Integer rank1 = getRank(hand1, 4).orElseThrow();
        Integer rank2 = getRank(hand2, 4).orElseThrow();

        int compare = rank1.compareTo(rank2);
        //four-of-a-kind cannot have the same rank 8 cards, so compare ==0 is not a valid case
        if (compare > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }

        return result;
    }
}
