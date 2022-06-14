package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardRank;
import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoyalFlushEvaluationImpl implements FlushEvaluation {
    @Override
    public PokerRankEnum evaluate(Card... cards) {
        PokerRankEnum result = FlushEvaluation.super.evaluate(cards);

        if (result.equals(PokerRankEnum.FLUSH)) {
            Integer[] sortedCardRanks = Arrays.stream(cards)
                    .map(Card::getValue)
                    .map(CardRank::getValueFromDisplay)
                    .sorted()
                    .toArray(Integer[]::new);

            boolean straight = true;
            for (int i=0; i< sortedCardRanks.length-1; i++) {
                straight = sortedCardRanks[i + 1].equals(sortedCardRanks[i] + 1) && straight;
            }
            if (sortedCardRanks[sortedCardRanks.length - 1] == CardRank.ACE.getValue() &&
                   straight ) {
                result = PokerRankEnum.ROYAL_FLUSH;
            } else {
                result = PokerRankEnum.HIGH_CARD;
            }
        } else {
            result = PokerRankEnum.HIGH_CARD;
        }

        return result;
    }

    @Override
    public PokerGameResult breakTie(Card[] hand1, Card[] hand2) {
        //both hands have Royal flush rank and both wins
        PokerGameResult result = new PokerGameResult();
        result.incrementPlayer1Won();
        result.incrementPlayer2Won();
        return result;
    }
}
