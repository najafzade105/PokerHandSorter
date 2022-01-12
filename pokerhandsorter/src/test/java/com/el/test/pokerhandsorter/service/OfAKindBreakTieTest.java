package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class OfAKindBreakTieTest extends EvaluationTest {

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

    //Pair rank tests

    @Test
    public void shouldWinPlayerWithHigherRankPair_whenHandsTie() {
        PokerGameResult result = pairEvaluation.breakTie(
                preparePlayCardTestData("TD", "9H", "QH", "9S", "6S"),
                preparePlayCardTestData("AS", "KD", "7H", "7S", "TS"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithHigherRank_whenHandHaveSamePairs() {
        PokerGameResult result = pairEvaluation.breakTie(
                preparePlayCardTestData("TD", "9H", "QH", "9S", "6S"),
                preparePlayCardTestData("AS", "KD", "9D", "9C", "TS"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithSecondHigherRank_whenHandHaveSamePairsANdSameHigh() {
        PokerGameResult result = pairEvaluation.breakTie(
                preparePlayCardTestData("QH", "9S", "9C", "7H", "3D"),
                preparePlayCardTestData("QD", "9D", "9H", "8C", "2S" ));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithThirdHigherRank_whenHandHaveSamePairsANdSameHigh() {
        PokerGameResult result = pairEvaluation.breakTie(
                preparePlayCardTestData("QH", "9S", "9C", "7H", "3D"),
                preparePlayCardTestData("QD", "9D", "9H", "7C", "2S" ));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinBothPlayers_whenHandTie() {
        PokerGameResult result = pairEvaluation.breakTie(
                preparePlayCardTestData("QH", "9S", "9C", "7H", "2D"),
                preparePlayCardTestData("QD", "9D", "9H", "7C", "2S" ));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    //Two pairs rank test

    @Test
    public void shouldWinPlayerWithHigherRankInTwoPair_whenHandsTie() {
        PokerGameResult result = twoPairsEvaluation.breakTie(
                preparePlayCardTestData("QH", "TD", "TC", "9H", "9S"),
                preparePlayCardTestData("AS", "AD", "TS", "7H", "7S"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithHigherRankInTwoPair_whenSecondPairIsHigh() {
        PokerGameResult result = twoPairsEvaluation.breakTie(
                preparePlayCardTestData("QH", "9H", "9S", "8D", "8S"),
                preparePlayCardTestData("9C", "9D", "TS", "7H", "7S"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithHigherRank_whenHandHaveSameTwoPairs() {
        PokerGameResult result = twoPairsEvaluation.breakTie(
                preparePlayCardTestData("AH", "AC", "KD", "9H", "9S"),
                preparePlayCardTestData("AS", "AD", "9C", "9D", "3D"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    //Three of a kind tests

    @Test
    public void shouldWinPlayerWithHigherRankInThreeOfAKind_whenHandsTie() {
        PokerGameResult result = threeOfAKindEvaluation.breakTie(
                preparePlayCardTestData("QH", "TD", "TC", "TH", "9S"),
                preparePlayCardTestData("AS", "QD", "QS", "QC", "7S"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    //Four of a kind tests

    @Test
    public void shouldWinPlayerWithHigherRankInFourOfAKind_whenHandsTie() {
        PokerGameResult result = fourOfAKindEvaluation.breakTie(
                preparePlayCardTestData("KH", "TD", "TC", "TH", "TS"),
                preparePlayCardTestData("AS", "QD", "QS", "QC", "QH"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    //Full house test

    @Test
    public void shouldWinPlayerWithHigherRankInFullHouse_whenHandsTie() {
        PokerGameResult result = fullHouseEvaluation.breakTie(
                preparePlayCardTestData("KD", "KC", "KH", "8S", "8C"),
                preparePlayCardTestData("AS", "AD", "QS", "QC", "QH"));
        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldWinPlayerWithHigherRankInFullHouse_whenHandsTieAndTripleIsTheSame() {
        PokerGameResult result = fullHouseEvaluation.breakTie(
                preparePlayCardTestData("KD", "KC", "KH", "8S", "8C"),
                preparePlayCardTestData("AS", "AD", "KS", "KC", "KH"));
        Assert.assertEquals(0, result.getPlayer1GameWon());
        Assert.assertEquals(1, result.getPlayer2GameWon());
    }

    //
}
