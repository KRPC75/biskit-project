package com.dices.biskit.models.rules;

import com.dices.biskit.enums.PlayerPosition;
import com.dices.biskit.enums.RuleType;
import com.dices.biskit.enums.SpecialEffect;
import com.dices.biskit.models.dices.Dice;
import com.dices.biskit.utils.DiceUtils;

import java.util.List;

public class DoubleRule extends Rule<List<Dice>> {

    private Integer doubleValue = 0;

    public DoubleRule(Integer doubleValue) {
        super(RuleType.DOUBLE);
        this.doubleValue = doubleValue;
    }

    public DoubleRule(Integer doubleValue, SpecialEffect specialEffect, String message) {
        super(RuleType.DOUBLE);
        this.doubleValue = doubleValue;
        this.specialEffect = specialEffect;
        this.message = message;
    }

    public DoubleRule(Integer doubleValue, SpecialEffect specialEffect, String message, PlayerPosition playerPosition) {
        super(RuleType.DOUBLE);
        this.doubleValue = doubleValue;
        this.specialEffect = specialEffect;
        this.message = message;
        this.playerPosition = playerPosition;
    }

    public Boolean doApplyIt(List<Dice> diceList) {
        return DiceUtils.isDouble(diceList, doubleValue);
    }

    public void applyIt() {

    }
}
