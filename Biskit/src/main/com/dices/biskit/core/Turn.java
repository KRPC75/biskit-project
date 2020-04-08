package com.dices.biskit.core;

import com.dices.biskit.enums.RuleType;
import com.dices.biskit.enums.SpecialEffect;
import com.dices.biskit.models.Player;
import com.dices.biskit.models.Result;
import com.dices.biskit.models.dices.Dice;
import com.dices.biskit.models.rules.DiceRule;
import com.dices.biskit.models.rules.DoubleRule;
import com.dices.biskit.models.rules.PlayerRule;
import com.dices.biskit.models.rules.Rule;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class Turn {

    public void doTurn(Player player, List<Dice> gameDices, List<Rule> gameRules, List<Player> playerList) {
        Result result = throwDices(gameDices);
        List<Rule> rulesToApply = defineRulesToApply(result, player, gameRules);
        applyRules(rulesToApply, gameRules, player, playerList);
    }

    Result throwDices(List<Dice> diceList) {
        Result result = new Result(diceList);
        result.throwDices();
        return result;
    }

    List<Rule> defineRulesToApply(Result result, Player player, List<Rule> ruleList) {

        List<Rule> rulesToApply = new ArrayList<>();

        for (Rule rule : ruleList) {
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

    void applyRules(List<Rule> rulesToApply, List<Rule> rules, Player player, List<Player> playerList) {
        List<SpecialEffect> specialEffects = new ArrayList<>();
        for (Rule rule : rulesToApply) {
            switch (rule.getSpecialEffect()) {
                case JAIL:
                    manageJail(rule, player, rules, playerList);
                    break;
                case DUEL:
                    break;
                case REVERT:
                    break;
                case NEW:
                    break;
                default:
                    break;
            }
            rule.applyIt();
        }
    }

    void manageJail(Rule rule, Player player, List<Rule> ruleList, List<Player> playerList) {
        DoubleRule doubleRule = (DoubleRule) rule;

        Player jailedPlayer = playerList.stream()
                .filter(jPlayer -> jPlayer.hasJail(doubleRule.getDoubleValue()))
                .findFirst().orElse(null);


        if (null != jailedPlayer) {
            jailedPlayer.removeJail(doubleRule.getDoubleValue());
            Predicate<Rule> predicate = toEraseRule -> toEraseRule.getType() == RuleType.DICE &&
                    rule.getSpecialEffect() == SpecialEffect.DRINK &&
                    ((DiceRule) rule).getDiceValue() == doubleRule.getDoubleValue();
            ruleList.removeIf(predicate);
        }

        player.addJail(doubleRule.getDoubleValue());
        ruleList.add(makeJail(doubleRule.getDoubleValue()));
    }

    DiceRule makeJail(Integer digit) {
        return new DiceRule(digit, SpecialEffect.DRINK, String.format("Prison de %d Le joueur doit boire une gorgée", digit));
    }
}
