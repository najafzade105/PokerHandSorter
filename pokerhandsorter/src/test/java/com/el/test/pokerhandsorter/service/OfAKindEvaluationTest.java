package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class OfAKindEvaluationTest extends EvaluationTest {

    @Autowired
    private PairEvaluation pairEvaluation;

    @Autowired
    private TwoPairsEvaluationImpl twoPairsEvaluation;

    @Autowired
    private ThreeOfAKindEvaluation threeOfAKindEvaluation;

    @Autowired
    private FourOfAKindEvaluationImpl fourOfAKindEvaluation;

    @Autowired
    private FullHouseEvaluationImpl fullHouseEvaluation;

    @Test
    public void shouldReturnPair_whenThereIsTwoOfTheSameRank() {
        PlayCard[] onePairs = preparePlayCardTestData("4H", "4C", "6S", "7S", "KD");
        PokerRankEnum result = pairEvaluation.evaluate(onePairs);
        Assert.assertEquals(PokerRankEnum.PAIR, result);
    }

    @Test
    public void shouldReturnTwoPairs_whenThereIsTwoPairsOfTheSameRank() {
        PlayCard[] twoPairs = preparePlayCardTestData("4H", "4C", "7S", "7H", "KD");
        PokerRankEnum result = twoPairsEvaluation.evaluate(twoPairs);
        Assert.assertEquals(PokerRankEnum.TWO_PAIRS, result);
    }

    @Test
    public void shouldReturnThreeOfAKind_whenThereIsThreeOfTheSameRank() {
        PlayCard[] triples = preparePlayCardTestData("4H", "4C", "4S", "7S", "KD");
        PokerRankEnum result = threeOfAKindEvaluation.evaluate(triples);
        Assert.assertEquals(PokerRankEnum.THREE_OF_A_KIND, result);
    }

    @Test
    public void shouldReturnTFourOfAKind_whenThereIsFourOfTheSameRank() {
        PlayCard[] four = preparePlayCardTestData("4H", "6C", "6S", "6H", "6D");
        PokerRankEnum result = fourOfAKindEvaluation.evaluate(four);
        Assert.assertEquals(PokerRankEnum.FOUR_OF_A_KIND, result);
    }

    @Test
    public void shouldReturnFullHouse_whenThereIsAPairAndThreeOfAKind() {
        PlayCard[] fullHouse = preparePlayCardTestData("4H", "4C", "6S", "6C", "6D");
        PokerRankEnum result = fullHouseEvaluation.evaluate(fullHouse);
        Assert.assertEquals(PokerRankEnum.FULL_HOUSE, result);
    }

}