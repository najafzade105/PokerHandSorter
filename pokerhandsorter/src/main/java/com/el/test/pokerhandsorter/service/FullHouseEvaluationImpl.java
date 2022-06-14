package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

@Service
public class FullHouseEvaluationImpl implements PairEvaluation, ThreeOfAKindEvaluation {
    @Override
    public PokerRankEnum evaluate(Card... cards) {

        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        if (PairEvaluation.super.evaluate(cards).equals(PokerRankEnum.PAIR) &&
                ThreeOfAKindEvaluation.super.evaluate(cards).equals(PokerRankEnum.THREE_OF_A_KIND)) {
            result = PokerRankEnum.FULL_HOUSE;
        }
        return result;
    }

    @Override
    public PokerGameResult breakTie(Card[] hand1, Card[] hand2) {
        PokerGameResult result = new PokerGameResult();
        Integer rank1 = getRank(hand1, 3).orElseThrow();
        Integer rank2 = getRank(hand2, 3).orElseThrow();

        int compare = rank1.compareTo(rank2);
        if (compare == 0) {
            //Triples are the same, so pairs will be compared
            Integer pairRank1 = getRank(hand1, 2).orElseThrow();
            Integer pairRank2 = getRank(hand2, 2).orElseThrow();
            int pairCompare = pairRank1.compareTo(pairRank2);

            if (pairCompare == 0) {
                //hand is tie, both wins
                result.incrementPlayer1Won();
                result.incrementPlayer2Won();
            } else if (pairCompare > 0) {
                result.incrementPlayer1Won();
            } else {
                result.incrementPlayer2Won();
            }
        } else if (compare > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }
        return result;
    }


}
