package com.dices.biskit.core;

import com.dices.biskit.enums.GameAction;
import com.dices.biskit.enums.SpecialEffect;
import com.dices.biskit.models.Player;
import com.dices.biskit.models.rules.DiceRule;
import com.dices.biskit.models.rules.DoubleRule;
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
    public void applyPassRuleTest() {

        List<Rule> ruleList = new ArrayList<>();
        ruleList.add(new DiceRule(3, SpecialEffect.NONE, "random rule"));
        ruleList.add(new ValueRule(4, SpecialEffect.PASS,  "Prochain joueur passé"));

        Player player = new Player("Test");

        GameAction action = turn.applyRules(ruleList, new ArrayList<>(), player, new ArrayList<>());
        Assert.assertEquals(GameAction.PASS, action);
    }

    @Test
    public void applyRevertRuleTest() {

        List<Rule> ruleList = new ArrayList<>();
        ruleList.add(new DiceRule(4, SpecialEffect.NONE, "random rule"));
        ruleList.add(new ValueRule(8, SpecialEffect.REVERT,  "Changement de sens"));

        Player player = new Player("Test");

        GameAction action = turn.applyRules(ruleList, new ArrayList<>(), player, new ArrayList<>());
        Assert.assertEquals(GameAction.REVERT, action);
    }

    @Test
    public void manageJailTest() {

        DoubleRule doubleRule = new DoubleRule(3, SpecialEffect.JAIL, "Prison de 3");
        Player player = new Player("Test");
        List<Rule> ruleList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();

        turn.manageJail(doubleRule, player, ruleList, playerList);

        Assert.assertFalse(ruleList.isEmpty());
    }

    @Test
    public void manageJailwithExistingTest() {

        DoubleRule doubleRule = new DoubleRule(5, SpecialEffect.JAIL, "Prison de 5");
        Player player = new Player("Test");
        List<Rule> ruleList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();

        DiceRule fiveRule = new DiceRule(5, SpecialEffect.DRINK, "Prison de 5");
        ruleList.add(fiveRule);

        Player jailed = new Player("Jailed");
        jailed.addJail(5);
        playerList.add(jailed);


        turn.manageJail(doubleRule, player, ruleList, playerList);

        Assert.assertEquals(1, ruleList.size());
    }

}
