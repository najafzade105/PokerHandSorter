package com.el.test.pokerhandsorter;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerPlayer;
import com.el.test.pokerhandsorter.service.PokerHandService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PokerHandService service = context.getBean(PokerHandService.class);


        /* reading from the input*/
        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();
        String currentLine;
        while (!(currentLine = scanner.nextLine()).isBlank()) {
            inputLines.add(currentLine);
        }

        /* applying the logic*/

        PokerPlayer player1 = service.generatePokerPlayer("player1",
                inputLines.stream().map(l -> l.substring(0, 14)).collect(Collectors.toList()));
        PokerPlayer player2 = service.generatePokerPlayer("player2",
                inputLines.stream().map(l -> l.substring(14)).collect(Collectors.toList()));


        PokerGameResult result = service.evaluatePokerGame(player1, player2);

        //writing out the result
        System.out.println("player1: " + result.getPlayer1GameWon() + " hands");
        System.out.println("player2: " + result.getPlayer2GameWon() + " hands");
    }
}