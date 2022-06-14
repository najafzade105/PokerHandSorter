package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public interface FlushEvaluation extends PokerHandEvaluation {
    @Override
    default PokerRankEnum evaluate(Card... cards) {
        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        long distinctSuit = Arrays.stream(cards)
                .map(Card::getSuit)
                .distinct()
                .count();
        if (distinctSuit == 1) {
            result = PokerRankEnum.FLUSH;
        }
        return result;
    }

}
