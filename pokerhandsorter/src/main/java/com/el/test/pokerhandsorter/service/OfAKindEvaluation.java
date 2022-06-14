package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardRank;
import com.el.test.pokerhandsorter.model.Card;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface OfAKindEvaluation extends PokerHandEvaluation {

    default Map<Character, Long> getTheSameKinds(Card... cards) {
        return Arrays.stream(cards)
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(Card::getValue, Collectors.counting()),
                        map -> {
                            map.values().removeIf(v -> v == 1);
                            return map;
                        }));
    }

    default Optional<Integer> getRank(Card[] hand1, int occurrence) {
        return this.getTheSameKinds(hand1).entrySet().stream()
                .filter(e -> e.getValue() == occurrence)
                .map(Map.Entry::getKey)
                .map(CardRank::getValueFromDisplay)
                .findFirst();

    }
}
