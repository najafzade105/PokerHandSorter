package com.el.test.pokerhandsorter.util;

import com.el.test.pokerhandsorter.model.Card;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Validator {

    private final static String CARD_VALUES = "23456789TJQKA";
    private final static String CARD_SUITS = "SHDC";

    public boolean validateCard(String card) {
        return !card.isBlank() &&
                card.trim().length() == 2 &&
                CARD_VALUES.contains(card.trim().substring(0, 1)) &&
                CARD_SUITS.contains(card.trim().substring(1));
    }

    public boolean validateHand(Card[] hand) {
        return hand != null && hand.length == 5 && validateDuplication(hand);
    }

    public boolean validateUnprocessedHand(List<String> rawHandLines) {
        return rawHandLines.stream()
                .map(l -> l.trim().split("\\s").length == 5)
                .reduce((a, b) -> a && b)
                .orElse(false);
    }

    public boolean validateDuplication(Card[] hand) {
        Set<String> tempSet = new HashSet<>();
        return Arrays.stream(hand).filter(h -> tempSet.add(h.toString())).count() == 5;
    }
}
