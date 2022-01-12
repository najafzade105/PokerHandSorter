package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardValueEnum;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public interface PairEvaluation extends OfAKindEvaluation {

    @Override
    default PokerRankEnum evaluate(PlayCard... playCards) {

        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        long pairs = OfAKindEvaluation.super.getTheSameKinds(playCards).entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .count();
        if (pairs > 0) {
            result = PokerRankEnum.PAIR;
        }
        return result;
    }

    @Override
    default PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        PokerGameResult result = new PokerGameResult();
        Integer hand1PairRank = getRank(hand1, 2).orElseThrow();
        Integer hand2PairRank = getRank(hand2, 2).orElseThrow();
        int compare = hand1PairRank.compareTo(hand2PairRank);
        if (compare == 0) {
            //hand ties, so check for the highest card
            result = OfAKindEvaluation.super.breakTie(removeComparedElement(hand1, hand1PairRank),
                    removeComparedElement(hand2, hand2PairRank));
        } else if (compare > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }
        return result;
    }

    private PlayCard[] removeComparedElement(PlayCard[] hand, int remove) {
        return Arrays.stream(hand)
                .filter(card -> CardValueEnum.getValue(card.getValue()) != remove)
                .toArray(PlayCard[]::new);


    }
}

