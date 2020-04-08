package com.dices.biskit.models.rules;

import com.dices.biskit.models.dices.ClassicDice;
import com.dices.biskit.models.dices.Dice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiceRuleTest {

    @Test
    public void applyItTest() {

        DiceRule diceRule = new DiceRule(4);
        List<Dice> diceList = new ArrayList<>();
        diceList.add(new ClassicDice());
        diceList.add(new ClassicDice());

        diceList.get(0).setValue(3);
        diceList.get(1).setValue(4);

        Assert.assertTrue(diceRule.doApplyIt(diceList));
    }

    @Test
    public void dontApplyItTest() {

        DiceRule diceRule = new DiceRule(5);
        List<Dice> diceList = new ArrayList<>();
        diceList.add(new ClassicDice());
        diceList.add(new ClassicDice());

        diceList.get(0).setValue(2);
        diceList.get(1).setValue(6);

        Assert.assertTrue(diceRule.doApplyIt(diceList));
    }

}
