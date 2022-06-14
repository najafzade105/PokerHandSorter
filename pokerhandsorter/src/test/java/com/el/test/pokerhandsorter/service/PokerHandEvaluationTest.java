package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class PokerHandEvaluationTest extends EvaluationTest {

    private PokerHandEvaluationImpl evaluation = new PokerHandEvaluationImpl();

    @Autowired
    private HighCardEvaluationImpl highCardEvaluation;

    @Test
    public void shouldWinPlayerWithHighHand_whenHandsTie() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("2H", "3C", "8D", "9H", "KC"),
                preparePlayCardTestData("3H", "6S", "7C", "JH", "QC"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinBoth_WhenHandsTieAndBothHaveSameRank() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("2H", "3C", "8D", "9H", "KC"),
                preparePlayCardTestData("2S", "3S", "8C", "9D", "KH"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithSecondHighHand_WhenHandsTieAndBothHaveSameHighHand() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("2H", "3C", "8D", "9H", "KC"),
                preparePlayCardTestData("2S", "7S", "8C", "TD", "KH"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithThirdHighHand_WhenHandsTieAndBothHaveSameHighHand() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("2H", "3C", "6D", "TH", "KC"),
                preparePlayCardTestData("2S", "7S", "8C", "TD", "KH"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithForthHighHand_WhenHandsTieAndBothHaveSameHighHand() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("2H", "7C", "8D", "9H", "KC"),
                preparePlayCardTestData("2S", "3S", "8C", "9D", "KH"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithFifthHighHand_WhenHandsTieAndBothHaveSameHighHand() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("2H", "7C", "8D", "9H", "KC"),
                preparePlayCardTestData("3S", "7S", "8C", "9D", "KH"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldReturnHighCardEvaluation_whenHandsIsTieOrHighCard() {
        PokerRankEnum result = highCardEvaluation.evaluate(preparePlayCardTestData("2S", "4D", "KD", "QH", "AS"));
        Assert.assertEquals(PokerRankEnum.HIGH_CARD, result);
    }

    private class PokerHandEvaluationImpl implements PokerHandEvaluation {

        @Override
        public PokerRankEnum evaluate(Card... cards) {
            return null;
        }
    }


}