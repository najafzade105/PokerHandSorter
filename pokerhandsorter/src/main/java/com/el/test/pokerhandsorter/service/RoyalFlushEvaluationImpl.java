package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardValueEnum;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoyalFlushEvaluationImpl implements FlushEvaluation {
    @Override
    public PokerRankEnum evaluate(PlayCard... playCards) {
        PokerRankEnum result = FlushEvaluation.super.evaluate(playCards);

        if (result.equals(PokerRankEnum.FLUSH)) {
            Integer[] sortedCardRanks = Arrays.stream(playCards)
                    .map(PlayCard::getValue)
                    .map(CardValueEnum::getValue)
                    .sorted()
                    .toArray(Integer[]::new);

            boolean straight = true;
            for (int i=0; i< sortedCardRanks.length-1; i++) {
                straight = sortedCardRanks[i + 1].equals(sortedCardRanks[i] + 1) && straight;
            }
            if (sortedCardRanks[sortedCardRanks.length - 1] == CardValueEnum.ACE.getValue() &&
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
    public PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        //both hands have Royal flush rank and both wins
        PokerGameResult result = new PokerGameResult();
        result.incrementPlayer1Won();
        result.incrementPlayer2Won();
        return result;
    }
}
