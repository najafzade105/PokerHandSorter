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
public class RoyalFlushEvaluationImplTest extends EvaluationTest {

    @Autowired
    private RoyalFlushEvaluationImpl evaluation;

    @Test
    public void shouldReturnTrueInRank10_whenCardsAreFlushANDHighRankFromACE() {
        Card[] straight = preparePlayCardTestData("AC", "KC", "QC", "JC", "TC");
        PokerRankEnum result = evaluation.evaluate(straight);
        Assert.assertEquals(PokerRankEnum.ROYAL_FLUSH, result);
    }

    @Test
    public void shouldWinBothHandsWithRoyalFlush() {
        PokerGameResult result = evaluation.breakTie(preparePlayCardTestData("AH", "KH", "QH", "JH", "TH"),
                preparePlayCardTestData("AS", "KS", "QS", "JS", "TS"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }
}