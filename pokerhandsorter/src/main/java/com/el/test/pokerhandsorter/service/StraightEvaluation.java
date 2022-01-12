package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardValueEnum;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;

import java.util.Arrays;


public interface StraightEvaluation extends PokerHandEvaluation {
    @Override
    default PokerRankEnum evaluate(PlayCard... playCards) {
        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        Integer[] sortedCardRanks = Arrays.stream(playCards)
                .map(PlayCard::getValue)
                .map(CardValueEnum::getValue)
                .sorted()
                .toArray(Integer[]::new);
        for(int i=0; i<sortedCardRanks.length-1; i++){
            if (!sortedCardRanks[i+1].equals(sortedCardRanks[i]+1)){
                return result;
            }
        }
        result = PokerRankEnum.STRAIGHT;
        return result;
    }

    @Override
    default PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        PokerGameResult result = new PokerGameResult();
        Integer highestHand1 = Arrays.stream(hand1)
                .map(PlayCard::getValue)
                .map(CardValueEnum::getValue)
                .max(Integer::compareTo)
                .orElse(1);
        Integer highestHand2 = Arrays.stream(hand2)
                .map(PlayCard::getValue)
                .map(CardValueEnum::getValue)
                .max(Integer::compareTo)
                .orElse(1);

        int compare = highestHand1.compareTo(highestHand2);
        if (compare == 0) {
            //both hands have same rank
            result = PokerHandEvaluation.super.breakTie(hand1, hand2);
        } else if (compare > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }
        return result;
    }
}
