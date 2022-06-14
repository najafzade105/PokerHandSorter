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
public class StraightFlushEvaluationImplTest extends EvaluationTest {
    @Autowired
    private StraightFlushEvaluationImpl evaluation;

    @Test
    public void shouldReturnStraightFlush_whenHandsHasFlushAndStraight() {
        Card[] straight = preparePlayCardTestData("9H", "TH", "JH", "QH", "KH");
        PokerRankEnum result = evaluation.evaluate(straight);
        Assert.assertEquals(PokerRankEnum.STRAIGHT_FLUSH, result);
    }

    @Test
    public void shouldNotReturnStraightFlush_whenHandsIsNotFlushAndStraight() {
        Card[] straight = preparePlayCardTestData("5D", "5H", "7S", "8C", "9H");
        PokerRankEnum result = evaluation.evaluate(straight);
        Assert.assertEquals(PokerRankEnum.HIGH_CARD, result);
    }

    @Test
    public void shouldWinPlayerWithHigherHand_whenBothStraightFlush() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("TH", "9H", "8H", "7H", "6H"),
                preparePlayCardTestData("KS", "QS", "JS", "TS", "9S"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

}