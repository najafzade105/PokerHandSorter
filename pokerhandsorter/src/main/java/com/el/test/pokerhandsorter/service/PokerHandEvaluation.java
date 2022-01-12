package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardValueEnum;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public interface PokerHandEvaluation {

    int POKER_RANK_SIZE = 11;

    PokerRankEnum evaluate(PlayCard... playCards);

    default PokerGameResult breakTie(PlayCard[] hand1, PlayCard[] hand2) {
        return breakTieRecursive(hand1,hand2);
    }

    private PokerGameResult breakTieRecursive(PlayCard[] hand1, PlayCard[] hand2){
        PokerGameResult result = new PokerGameResult();
        Optional<Integer> highestHand1 = getHighest(hand1);
        Optional<Integer> highestHand2 = getHighest(hand2);
        if (highestHand1.isEmpty() || highestHand2.isEmpty()) {
            //both hands have same rank
            result.incrementPlayer1Won();
            result.incrementPlayer2Won();
            return result;
        }
        int compare = highestHand1.get().compareTo(highestHand2.get());
        if (compare == 0) {
            Comparator<PlayCard> cardComparator = Comparator.comparing(PlayCard::getValue);
            PlayCard[] c1 = Arrays.stream(hand1).sorted(cardComparator).limit(hand1.length - 1).toArray(PlayCard[]::new);
            PlayCard[] c2 = Arrays.stream(hand2).sorted(cardComparator).limit(hand2.length - 1).toArray(PlayCard[]::new);
            result = breakTieRecursive(c1, c2);
        } else if (compare > 0) {
            result.incrementPlayer1Won();
        } else {
            result.incrementPlayer2Won();
        }
        return result;
    }

    private Optional<Integer> getHighest(PlayCard[] hand) {
        return Arrays.stream(hand)
                .map(PlayCard::getValue)
                .map(CardValueEnum::getValue)
                .max(Integer::compareTo);
    }

}
