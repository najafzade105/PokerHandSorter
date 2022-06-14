package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardRank;
import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TwoPairsEvaluationImpl implements OfAKindEvaluation {
    @Override
    public PokerRankEnum evaluate(Card... cards) {

        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        long pairs = OfAKindEvaluation.super.getTheSameKinds(cards).entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .count();
        if (pairs == 2) {
            result = PokerRankEnum.TWO_PAIRS;
        }
        return result;
    }

    @Override
    public PokerGameResult breakTie(Card[] hand1, Card[] hand2) {
        PokerGameResult result = new PokerGameResult();
        Integer greaterPairOfHand1 = getStreamOfRank(hand1).max(Integer::compareTo).orElseThrow();
        Integer greaterPairOfHand2 = getStreamOfRank(hand2).max(Integer::compareTo).orElseThrow();

        int compareGreaterPair = greaterPairOfHand1.compareTo(greaterPairOfHand2);
        if (compareGreaterPair == 0) {
            //compare the other pair
            Integer lowerPairOfHand1 = getStreamOfRank(hand1).min(Integer::compareTo).orElseThrow();
            Integer lowerPairOfHand2 = getStreamOfRank(hand2).min(Integer::compareTo).orElseThrow();
            int compareLowerPair = greaterPairOfHand1.compareTo(greaterPairOfHand2);
            if (compareLowerPair == 0) {
                //compare the fifth card
                Integer hand1Fifth = getTheFifthCard(hand1, greaterPairOfHand1, lowerPairOfHand1).orElseThrow();
                Integer hand2Fifth = getTheFifthCard(hand2, greaterPairOfHand2, lowerPairOfHand2).orElseThrow();

                int fifthCompare = hand1Fifth.compareTo(hand2Fifth);

                if (fifthCompare == 0) {
                    //hand ties, both win
                    result.incrementPlayer1Won();
                    result.incrementPlayer2Won();
                    return result;
                } else if (fifthCompare > 0) {
                    result.incrementPlayer1Won();
                } else {
                    result.incrementPlayer2Won();
                }
            } else if (compareLowerPair > 0) {
                result.incrementPlayer1Won();
            } else {
                result.incrementPlayer2Won();
            }
        } else if (compareGreaterPair > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }

        return result;
    }

    private Stream<Integer> getStreamOfRank(Card[] hand) {
        return this.getTheSameKinds(hand).entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .map(Map.Entry::getKey)
                .map(CardRank::getValueFromDisplay);

    }

    private Optional<Integer> getTheFifthCard(Card[] hand, int greaterPair, int lowerPair) {
        return Arrays.stream(hand)
                .map(Card::getValue)
                .map(CardRank::getValueFromDisplay)
                .filter(v -> !v.equals(greaterPair) && !v.equals(lowerPair))
                .findFirst();
    }
}
