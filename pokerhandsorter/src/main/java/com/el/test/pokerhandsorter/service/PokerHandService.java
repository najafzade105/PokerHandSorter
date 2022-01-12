package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokerHandService {

    private Map<PokerRankEnum, PokerHandEvaluation> evaluations;

    public PokerHandService(Map<PokerRankEnum, PokerHandEvaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public PokerPlayer generatePokerPlayer(String playerName, List<String> rawHandLines) {
        PokerPlayer pokerPlayer = new PokerPlayer(playerName);
        List<PlayHand> playerHands = new ArrayList<>();
        pokerPlayer.setHands(playerHands);
        rawHandLines.stream()
                .map(l -> l.trim().split("\\s"))
                .forEach(array -> {
                    playerHands.add(new PlayHand(Arrays.stream(array).map(PlayCard::new).toArray(PlayCard[]::new)));
                });
        return pokerPlayer;
    }

    public PokerGameResult evaluatePokerGame(PokerPlayer player1, PokerPlayer player2) {
        //todo:move to validation
        if (player1.getHands().size() != player2.getHands().size()) {
            throw new RuntimeException("Players have different number of hands!");
        }
        PokerGameResult pokerGameResult = new PokerGameResult();

        int numberOfGames = player1.getHands().size();
        System.out.println("number of games: "+ numberOfGames);
        for (int i = 0; i < numberOfGames; i++) {
            PokerGameResult current = comparePokerHands(player1.getHands().get(i).getHand(), player2.getHands().get(i).getHand());
            pokerGameResult.setPlayer1GameWon(pokerGameResult.getPlayer1GameWon() + current.getPlayer1GameWon());
            pokerGameResult.setPlayer2GameWon(pokerGameResult.getPlayer2GameWon() + current.getPlayer2GameWon());

            System.out.println("hand1: " + Arrays.toString(sort(player1.getHands().get(i).getHand())) +
                            " hand2: " + Arrays.toString(sort(player2.getHands().get(i).getHand())) +
                    " p1: " + pokerGameResult.getPlayer1GameWon() + " p2: " + pokerGameResult.getPlayer2GameWon());
        }

        return pokerGameResult;
    }

    private PlayCard[] sort(PlayCard[] hand) {
        return Arrays.stream(hand)
                .sorted(Comparator.comparingInt(c -> CardValueEnum.getValue(c.getValue())))
                .toArray(PlayCard[]::new);
    }

    public PokerGameResult comparePokerHands(PlayCard[] hand1, PlayCard[] hand2) {

        PokerGameResult result = new PokerGameResult();

        int evaluationIndex = 10;
        boolean foundWinner = false;
        while (!foundWinner) {

            PokerRankEnum currentRank = PokerRankEnum.getPokerRankEnum(evaluationIndex);
            PokerRankEnum res1 = evaluations.get(currentRank).evaluate(hand1);
            PokerRankEnum res2 = evaluations.get(currentRank).evaluate(hand2);

            if (res1.equals(currentRank) || res2.equals(currentRank)) {
                System.out.println("****" + currentRank + "****");
                foundWinner = true;
                if (res1.equals(currentRank) && !res2.equals(currentRank)) {
                    result.incrementPlayer1Won();
                } else if (!res1.equals(currentRank)) {
                    result.incrementPlayer2Won();
                } else {
                    //both have the same rank, so should break the tie
                    PokerGameResult breakTieResult = evaluations.get(currentRank)
                            .breakTie(hand1, hand2);
                    //if tie remains both players win
                    if (breakTieResult.getPlayer1GameWon() > 0) {
                        result.incrementPlayer1Won();
                    }
                    if (breakTieResult.getPlayer2GameWon() > 0) {
                        result.incrementPlayer2Won();
                    }
                }

            }
            evaluationIndex--;
        }


        return result;
    }


}
