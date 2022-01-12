package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PlayCard;
import com.el.test.pokerhandsorter.model.PokerRankEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class FlushEvaluationTest extends EvaluationTest {

    private class FlushEvaluationImpl implements FlushEvaluation {
        @Override
        public PokerRankEnum evaluate(PlayCard... playCards) {
            return FlushEvaluation.super.evaluate(playCards);
        }
    }

    private FlushEvaluationImpl evaluation = new FlushEvaluationImpl();

    @Test
    public void shouldReturnTrueInRank6_whenCardsHaveTheSameSuit() {
        PlayCard[] straight = preparePlayCardTestData("9H", "7H", "JH", "3H", "KH");
        PokerRankEnum result = evaluation.evaluate(straight);
        Assert.assertEquals(PokerRankEnum.FLUSH, result);
    }
}