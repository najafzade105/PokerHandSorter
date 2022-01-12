package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class StraightEvaluationImplTest extends EvaluationTest {

    private class StraightEvaluationImpl implements StraightEvaluation {
        @Override
        public PokerRankEnum evaluate(PlayCard... playCards) {
            return StraightEvaluation.super.evaluate(playCards);
        }
    }

    private StraightEvaluation evaluation = new StraightEvaluationImpl();

    @Test
    public void shouldReturnTrueInRank5_whenHandsHasConsecutiveOrder() {
        PlayCard[] straight = preparePlayCardTestData("9H", "TC", "JS", "QS", "KD");
        PokerRankEnum result = evaluation.evaluate(straight);
        Assert.assertEquals(PokerRankEnum.STRAIGHT, result);
    }

    @Test
    public void shouldNotReturnStraight_whenHandsIsNotConsecutiveOrder() {
        PlayCard[] straight = preparePlayCardTestData("5D", "5H", "7S", "8C", "9H");
        PokerRankEnum result = evaluation.evaluate(straight);
        Assert.assertEquals(PokerRankEnum.HIGH_CARD, result);
    }

    @Test
    public void shouldWinHigherHand_whenBothHandsHaveStraight() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("KH", "QD", "JC", "TH", "9D"),
                preparePlayCardTestData("QS", "JH", "TC", "9H", "8D"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }



}