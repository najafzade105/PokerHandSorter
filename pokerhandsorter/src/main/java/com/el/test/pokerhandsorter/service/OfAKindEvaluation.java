package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.CardValueEnum;
import com.el.test.pokerhandsorter.model.PlayCard;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface OfAKindEvaluation extends PokerHandEvaluation {

    default Map<Character, Long> getTheSameKinds(PlayCard... playCards) {
        return Arrays.stream(playCards)
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(PlayCard::getValue, Collectors.counting()),
                        map -> {
                            map.values().removeIf(v -> v == 1);
                            return map;
                        }));
    }

    default Optional<Integer> getRank(PlayCard[] hand1, int occurrence) {
        return this.getTheSameKinds(hand1).entrySet().stream()
                .filter(e -> e.getValue() == occurrence)
                .map(Map.Entry::getKey)
                .map(CardValueEnum::getValue)
                .findFirst();

    }
}
