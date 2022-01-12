package com.el.test.pokerhandsorter.service;

import com.el.test.pokerhandsorter.config.Config;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class PokerHandServiceTest {

    @Autowired
    private PokerHandService service;

    @Test
    @Ignore
    public void shouldEvaluatePokerHandForPokerPlayer() {

//        PokerPlayer player = service.generatePokerPlayer("Test Player",
//                List.of("AS KS QS JS TS", "3S AD 6D 2H 4H", "2H 9S 9C JD KH"));
//
//        service.evaluatePokerHand(player.getHands().get(1));

    }


}