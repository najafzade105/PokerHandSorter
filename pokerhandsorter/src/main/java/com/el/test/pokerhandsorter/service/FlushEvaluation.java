package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public interface FlushEvaluation extends PokerHandEvaluation {
    @Override
    default PokerRankEnum evaluate(PlayCard... playCards) {
        PokerRankEnum result = PokerRankEnum.HIGH_CARD;
        long distinctSuit = Arrays.stream(playCards)
                .map(PlayCard::getSuit)
                .distinct()
                .count();
        if (distinctSuit == 1) {
            result = PokerRankEnum.FLUSH;
        }
        return result;
    }

}
