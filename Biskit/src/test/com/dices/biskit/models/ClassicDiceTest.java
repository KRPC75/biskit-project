package com.dices.biskit.models;

import com.dices.biskit.models.dices.ClassicDice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassicDiceTest {

    @Test
    public void throwItTest() {
        ClassicDice classicDice = new ClassicDice();
        Integer result = classicDice.throwIt();
        Assert.assertTrue(result >= 1 && result <= 6);
    }

}
