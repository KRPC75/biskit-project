package com.dices.biskit.controllers;

import com.dices.biskit.models.Player;
import com.dices.biskit.models.Result;
import com.dices.biskit.models.dices.Dice;
import com.dices.biskit.models.rules.Rule;

import java.util.ArrayList;
import java.util.List;

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
                    applyIt = rule.applyIt(result.getDiceList());
                    break;
                case VALUE:
                    applyIt = rule.applyIt(result.getTotal());
                    break;
                case PLAYER:
                    applyIt = rule.applyIt(player);
                    break;
                default:
                    break;
            }
            if (applyIt)
                rulesToApply.add(rule);
        }
        return rulesToApply;
    }

    void applyRules(List<Rule> rulesToApply) {

    }

    public void setGameDices(List<Dice> gameDices) {
        this.gameDices = gameDices;
    }
}
