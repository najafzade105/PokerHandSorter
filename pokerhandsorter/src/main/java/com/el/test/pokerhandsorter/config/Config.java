package com.el.test.pokerhandsorter.config;

import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import com.el.test.pokerhandsorter.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"com.el.test.pokerhandsorter"})
public class Config {

    @Bean
    public PokerHandService pokerHandService() {
        Map<PokerRankEnum, PokerHandEvaluation> evaluationList = new HashMap<>();
        evaluationList.put(PokerRankEnum.HIGH_CARD, highCardEvaluationImpl());
        evaluationList.put(PokerRankEnum.PAIR, pairEvaluation());
        evaluationList.put(PokerRankEnum.TWO_PAIRS, twoPairsEvaluationImpl());
        evaluationList.put(PokerRankEnum.THREE_OF_A_KIND, threeOfAKindEvaluation());
        evaluationList.put(PokerRankEnum.STRAIGHT, straightEvaluation());
        evaluationList.put(PokerRankEnum.FLUSH, flushEvaluation());
        evaluationList.put(PokerRankEnum.FULL_HOUSE, fullHouseEvaluationImpl());
        evaluationList.put(PokerRankEnum.FOUR_OF_A_KIND, fourOfAKindEvaluationImpl());
        evaluationList.put(PokerRankEnum.STRAIGHT_FLUSH, straightFlushEvaluationImpl());
        evaluationList.put(PokerRankEnum.ROYAL_FLUSH, royalFlushEvaluationImpl());
        return new PokerHandService(evaluationList);
    }

    @Bean
    public PokerHandEvaluation pairEvaluation() {
        return new PairEvaluation() {
            @Override
            public PokerRankEnum evaluate(PlayCard... playCards) {
                return PairEvaluation.super.evaluate(playCards);
            }
        };
    }

    @Bean
    public PokerHandEvaluation twoPairsEvaluationImpl() {
        return new TwoPairsEvaluationImpl();
    }

    @Bean
    public PokerHandEvaluation threeOfAKindEvaluation() {
        return new ThreeOfAKindEvaluation() {
            @Override
            public PokerRankEnum evaluate(PlayCard... playCards) {
                return ThreeOfAKindEvaluation.super.evaluate(playCards);
            }
        };
    }

    @Bean
    public PokerHandEvaluation fullHouseEvaluationImpl() {
        return new FullHouseEvaluationImpl();
    }

    @Bean
    public PokerHandEvaluation fourOfAKindEvaluationImpl() {
        return new FourOfAKindEvaluationImpl();
    }

    @Bean
    public PokerHandEvaluation straightEvaluation() {
        return new StraightEvaluation() {
            @Override
            public PokerRankEnum evaluate(PlayCard... playCards) {
                return StraightEvaluation.super.evaluate(playCards);
            }
        };
    }

    @Bean
    public PokerHandEvaluation flushEvaluation() {
        return new FlushEvaluation() {
            @Override
            public PokerRankEnum evaluate(PlayCard... playCards) {
                return FlushEvaluation.super.evaluate(playCards);
            }
        };
    }

    @Bean
    public PokerHandEvaluation royalFlushEvaluationImpl() {
        return new RoyalFlushEvaluationImpl();
    }

    @Bean
    public PokerHandEvaluation straightFlushEvaluationImpl() {
        return new StraightFlushEvaluationImpl();
    }

    @Bean
    public PokerHandEvaluation highCardEvaluationImpl() {
        return new HighCardEvaluationImpl();
    }
}
