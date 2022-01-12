package com.el.test.pokerhandsorter.util;

import com.el.test.pokerhandsorter.config.Config;
import com.el.test.pokerhandsorter.model.PlayCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class ValidatorTest {

    @Autowired
    Validator validator;

    @Test
    public void shouldReturnTrue_whenRawHandDataIsValid() {
        List<String> valid = generateRawHandData();
        Assert.assertEquals(true, validator.validateUnprocessedHand(valid));
    }

    @Test
    public void shouldReturnFalse_whenRawHandDataIsInvalid() {
        List<String> invalid = generateInvalidRawHandData();
        Assert.assertEquals(false, validator.validateUnprocessedHand(invalid));
    }

    @Test
    public void shouldValidateFalse_whenRawHandDataHasDuplicate() {
        PlayCard[] duplicate = generateDuplicateHandData();
        Assert.assertEquals(false, validator.validateDuplication(duplicate));
    }

    private List<String> generateRawHandData() {
        return List.of("6H 4D KC 8S 6D", "TS 5C AH 3D KC", "6H KD 5D KH AC");
    }

    private List<String> generateInvalidRawHandData() {
        return List.of("8D KS 2S QD ", "TD 7D 5S ", "6H ", "TS 5C AH 3D KC KC", " ", "TH JH");
    }

    private PlayCard[] generateDuplicateHandData() {
        PlayCard[] data = new PlayCard[5];
        data[0] = new PlayCard("8D");
        data[1] = new PlayCard("8D");
        data[2] = new PlayCard("2S");
        data[3] = new PlayCard("QD");
        data[4] = new PlayCard("TD");
        return data;
    }
}
