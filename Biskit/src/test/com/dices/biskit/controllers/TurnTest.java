package com.dices.biskit.controllers;

import com.dices.biskit.models.Player;
import com.dices.biskit.models.Result;
import com.dices.biskit.models.dices.ClassicDice;
import com.dices.biskit.models.dices.Dice;
import com.dices.biskit.models.rules.DiceRule;
import com.dices.biskit.models.rules.Rule;
import com.dices.biskit.models.rules.ValueRule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnTest {

    @Autowired
    private Turn turn;

    @Test
    public void throwDicesTest() {

        List<Dice> gameDices = new ArrayList<>();
        gameDices.add(new ClassicDice());
        gameDices.add(new ClassicDice());

        turn.setGameDices(gameDices);
        Result result = turn.throwDices();

        Assert.assertNotEquals(new Integer(0), result.getTotal());
        Assert.assertFalse(result.getDiceList().isEmpty());
        Assert.assertTrue(result.getDiceList().get(0).getValue() >= 1 &&
                result.getDiceList().get(0).getValue() <= 6);
        Assert.assertTrue(result.getDiceList().get(1).getValue() >= 1 &&
                result.getDiceList().get(1).getValue() <= 6);
    }

    @Test
    public void defineRulesToApplyTest() {

        List<Dice> diceList = new ArrayList<>();
        ClassicDice classicDice1 = new ClassicDice();
        classicDice1.setValue(4);
        ClassicDice classicDice2 = new ClassicDice();
        classicDice2.setValue(6);
        diceList.add(classicDice1);
        diceList.add(classicDice2);

        Result result = new Result(diceList);
        result.setTotal(10);

        Player player = new Player("Name");

        List<Rule> ruleList = new ArrayList<>();
        ruleList.add(new DiceRule(6));
        ruleList.add(new DiceRule(5));
        ruleList.add(new DiceRule(4));
        ruleList.add(new ValueRule(10));
        ruleList.add(new ValueRule(12));

        turn.setGameRules(ruleList);
        List<Rule> finalRules = turn.defineRulesToApply(result, player);

        Assert.assertEquals(3, finalRules.size());
    }

}
