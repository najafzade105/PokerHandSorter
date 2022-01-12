package com.el.test.pokerhandsorter;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PokerGameResult;
import com.el.test.pokerhandsorter.model.PokerPlayer;
import com.el.test.pokerhandsorter.service.PokerHandService;
import com.el.test.pokerhandsorter.util.StandardReaderUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PokerHandService service = context.getBean(PokerHandService.class);
        StandardReaderUtil readerUtil = context.getBean(StandardReaderUtil.class);

        List<String> inputLines;


        if (args.length == 2 && args[0].equalsIgnoreCase("F")) {
            inputLines = readerUtil.read(Path.of(args[1]));
        } else {
            inputLines = readerUtil.read(System.in);
        }

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