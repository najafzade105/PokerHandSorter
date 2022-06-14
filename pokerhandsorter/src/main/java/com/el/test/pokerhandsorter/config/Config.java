package com.el.test.pokerhandsorter.config;

import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import com.el.test.pokerhandsorter.service.*;
import com.el.test.pokerhandsorter.util.StandardReaderUtil;
import com.el.test.pokerhandsorter.util.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"com.el.test.pokerhandsorter"})
public class Config {

    @Bean
    public StandardReaderUtil standardReaderUtil(){
        return new StandardReaderUtil();
    }

    @Bean
    public Validator validator(){
        return new Validator();
    }

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
        return new PokerHandService(evaluationList,validator());
    }

    @Bean
    public PokerHandEvaluation pairEvaluation() {
        return new PairEvaluation() {
            @Override
            public PokerRankEnum evaluate(Card... cards) {
                return PairEvaluation.super.evaluate(cards);
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
            public PokerRankEnum evaluate(Card... cards) {
                return ThreeOfAKindEvaluation.super.evaluate(cards);
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
            public PokerRankEnum evaluate(Card... cards) {
                return StraightEvaluation.super.evaluate(cards);
            }
        };
    }

    @Bean
    public PokerHandEvaluation flushEvaluation() {
        return new FlushEvaluation() {
            @Override
            public PokerRankEnum evaluate(Card... cards) {
                return FlushEvaluation.super.evaluate(cards);
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
