package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.Card;
import com.el.test.pokerhandsorter.model.PlayHand;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerPlayer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class PokerHandServiceTest extends EvaluationTest {

    @Autowired
    private PokerHandService service;

    @Test
    public void shouldEvaluatePokerHandForPokerPlayer() {
        PokerGameResult result = service.comparePokerHands(preparePlayCardTestData("2H", "AS", "TD", "2D", "6C"),
                preparePlayCardTestData("2C", "2S", "TC", "9H", "5D"));

        Assert.assertEquals(1, result.getPlayer1GameWon());
        Assert.assertEquals(0, result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreHighCard() {
        PokerPlayer testPlayer1 = generateTestPlayer("4S 6H JC QD AS", "8C TC JS KS AS");
        PokerPlayer testPlayer2 = generateTestPlayer("2D 3H TH JS QH", "2S 9S TD QC KH");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(2,result.getPlayer1GameWon());
        Assert.assertEquals(0,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsArePair() {
        PokerPlayer testPlayer1 = generateTestPlayer("9C 9D 8D 7C 3C", "QC JH AS 4H 8S");
        PokerPlayer testPlayer2 = generateTestPlayer("AD TC 7H 6S 2H", "6H 5C AD AC 2S");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(1,result.getPlayer1GameWon());
        Assert.assertEquals(1,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreTwoPairs() {
        PokerPlayer testPlayer1 = generateTestPlayer("2D 3C 7D TC AC", "2D 7D 9H JS KD");
        PokerPlayer testPlayer2 = generateTestPlayer("3S 4H 4D JS JD", "5C 5S 6S 6H TH");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(0,result.getPlayer1GameWon());
        Assert.assertEquals(2,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreThreeOfAKind() {
        PokerPlayer testPlayer1 = generateTestPlayer("5D 5H 5S 7S 9D", "5D 9S JD JH JC");
        PokerPlayer testPlayer2 = generateTestPlayer("4H 6D 6H QH AS", "2D 5H 5C 6C AD");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(2,result.getPlayer1GameWon());
        Assert.assertEquals(0,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreFourOfAKind() {
        PokerPlayer testPlayer1 = generateTestPlayer("9D 9S 9C 9H 3C", "QC QH QS QD 8S");
        PokerPlayer testPlayer2 = generateTestPlayer("AD TC 7H 6S 2H", "6H 5C AD 4C 2S");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(2,result.getPlayer1GameWon());
        Assert.assertEquals(0,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreFullHouse() {
        PokerPlayer testPlayer1 = generateTestPlayer("6C 6H QD QS QH");
        PokerPlayer testPlayer2 = generateTestPlayer("5D 8H TS KH AD");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(1,result.getPlayer1GameWon());
        Assert.assertEquals(0,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreFlush() {
        PokerPlayer testPlayer1 = generateTestPlayer("5D 9D 8D 7D 3D", "QC JH AS 4H 8S");
        PokerPlayer testPlayer2 = generateTestPlayer("AD TC 7H 6S 2H", "6H 5H AH 3H QH");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(1,result.getPlayer1GameWon());
        Assert.assertEquals(1,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreStraight() {
        PokerPlayer testPlayer1 = generateTestPlayer("9C 8D 7D 6C 5C", "QC JH AS 4H 8S");
        PokerPlayer testPlayer2 = generateTestPlayer("AD TC 7H 6S 2H", "KH QC JD AC TS");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(1,result.getPlayer1GameWon());
        Assert.assertEquals(1,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreStraightFlush() {
        PokerPlayer testPlayer1 = generateTestPlayer("9C 8C 7C 6C 5C", "QC JC AS 4H 8S");
        PokerPlayer testPlayer2 = generateTestPlayer("AD TC 7H 6S 2H", "KH QH JH AH TH");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(1,result.getPlayer1GameWon());
        Assert.assertEquals(1,result.getPlayer2GameWon());
    }

    @Test
    public void shouldEvaluatePokerGame_whenHandsAreRoyalFlush() {
        PokerPlayer testPlayer1 = generateTestPlayer("AH KH QH JH TH");
        PokerPlayer testPlayer2 = generateTestPlayer("AD TC 7H 6S 2H");
        PokerGameResult result = service.evaluatePokerGame(testPlayer1,testPlayer2);
        Assert.assertEquals(1,result.getPlayer1GameWon());
        Assert.assertEquals(0,result.getPlayer2GameWon());
    }

    @Test
    public void shouldGeneratePokerPlayerFromString() {
        List<String> sampleList = List.of("9C 9D 8D 7C 3C", "6C 5H AS 4H 7S");
        PokerPlayer player = service.generatePokerPlayer("TestPlayer", sampleList);

        Assert.assertEquals("TestPlayer", player.getPlayerName());
        List<PlayHand> hands = player.getHands();
        Card[] card = hands.get(0).getHand();
        Card[] card1 = hands.get(1).getHand();
        Assert.assertEquals("9C", card[0].getCard());
        Assert.assertEquals("9D", card[1].getCard());
        Assert.assertEquals("8D", card[2].getCard());
        Assert.assertEquals("7C", card[3].getCard());
        Assert.assertEquals("3C", card[4].getCard());
        Assert.assertEquals("6C", card1[0].getCard());
        Assert.assertEquals("5H", card1[1].getCard());
        Assert.assertEquals("AS", card1[2].getCard());
        Assert.assertEquals("4H", card1[3].getCard());
        Assert.assertEquals("7S", card1[4].getCard());
    }

    private PokerPlayer generateTestPlayer(String... lines) {
        PokerPlayer pokerPlayer = new PokerPlayer("TestPlayer");
        List<PlayHand> playerHands = new ArrayList<>();
        pokerPlayer.setHands(playerHands);
        Arrays.stream(lines)
                .map(l -> l.trim().split("\\s"))
                .forEach(array -> {
                    playerHands.add(new PlayHand(Arrays.stream(array).map(Card::new).toArray(Card[]::new)));
                });
        return pokerPlayer;
    }

}