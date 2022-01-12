package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public interface ThreeOfAKindEvaluation extends OfAKindEvaluation {
    @Override
    default PokerRankEnum evaluate(PlayCard... playCards) {

        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        long triples = OfAKindEvaluation.super.getTheSameKinds(playCards).entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .count();
        if (triples > 0) {
            result = PokerRankEnum.THREE_OF_A_KIND;
        }
        return result;
    }

    @Override
    default PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        PokerGameResult result = new PokerGameResult();

        Integer rank1 = getRank(hand1, 3).orElseThrow();
        Integer rank2 = getRank(hand2, 3).orElseThrow();
        int compare = rank1.compareTo(rank2);
        //Triple of a kind cannot have 6 cards with the same kind, so compare == 0 is not valid
        if (compare > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }
        return OfAKindEvaluation.super.breakTie(hand1, hand2);
    }
}
