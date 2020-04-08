package com.dices.biskit.controllers;

import com.dices.biskit.models.Player;
import com.dices.biskit.models.Result;
import com.dices.biskit.models.dices.Dice;
import com.dices.biskit.models.rules.Rule;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Turn {

    List<Dice> gameDices;
    List<Rule> gameRules;

    public void doTurn(Player player) {
        Result result = throwDices();
        List<Rule> rulesToApply = defineRulesToApply(result, player);
        applyRules(rulesToApply);
    }

    Result throwDices() {
        Result result = new Result(gameDices);
        result.throwDices();
        return result;
    }

    List<Rule> defineRulesToApply(Result result, Player player) {

        List<Rule> rulesToApply = new ArrayList<>();

        for (Rule rule : gameRules) {
            Boolean applyIt = false;
            switch (rule.getType()) {
                case DICE:
                    applyIt = rule.doApplyIt(result.getDiceList());
                    break;
                case VALUE:
                    applyIt = rule.doApplyIt(result.getTotal());
                    break;
                case PLAYER:
                    applyIt = rule.doApplyIt(player);
                    break;
                case DOUBLE:
                    applyIt = rule.doApplyIt(result.getDiceList());
                default:
                    break;
            }
            if (applyIt)
                rulesToApply.add(rule);
        }
        return rulesToApply;
    }

    void applyRules(List<Rule> rulesToApply) {
        for (Rule rule : rulesToApply) {
            rule.applyIt();
        }
    }

    public void setGameDices(List<Dice> gameDices) {
        this.gameDices = gameDices;
    }

    public void setGameRules(List<Rule> gameRules) {
        this.gameRules = gameRules;
    }
}
