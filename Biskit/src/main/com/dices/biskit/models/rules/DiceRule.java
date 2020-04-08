package com.dices.biskit.models.rules;

import com.dices.biskit.enums.RuleType;
import com.dices.biskit.enums.SpecialEffect;
import com.dices.biskit.models.dices.Dice;

import java.util.List;

public class DiceRule extends Rule<List<Dice>> {

    private Integer diceValue;

    public DiceRule() {
        super(RuleType.DICE);
    }

    public DiceRule(Integer diceValue) {
        super(RuleType.DICE);
        this.diceValue = diceValue;
    }

    public DiceRule(Integer diceValue, SpecialEffect specialEffect, String message) {
        super(RuleType.DICE);
        this.diceValue = diceValue;
        this.specialEffect = specialEffect;
        this.message = message;
    }

    public Boolean doApplyIt(List<Dice> diceList) {
        return diceList
                .stream()
                .anyMatch(dice -> dice.getValue().equals(diceValue));
    }

    public void applyIt() {
        System.out.println(String.format("%d : %s", diceValue, message));
    }

    public Integer getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(Integer diceValue) {
        this.diceValue = diceValue;
    }
}
