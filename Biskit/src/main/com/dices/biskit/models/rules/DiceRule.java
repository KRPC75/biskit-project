package com.dices.biskit.models.rules;

import com.dices.biskit.enums.RuleType;

import java.util.List;

public class DiceRule extends Rule<List<Integer>> {

    private Integer diceValue;

    public DiceRule() {
        super(RuleType.DICE);
    }

    public Boolean applyIt(List<Integer> dicesValues) {
        return dicesValues.contains(diceValue);
    }

    public Integer getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(Integer diceValue) {
        this.diceValue = diceValue;
    }
}
