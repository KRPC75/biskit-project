package com.dices.biskit.models.rules;

import com.dices.biskit.enums.PlayerPosition;
import com.dices.biskit.enums.RuleType;
import com.dices.biskit.enums.SpecialEffect;

public class ValueRule extends Rule<Integer> {

    private Integer value;

    public ValueRule() {
        super(RuleType.VALUE);
    }

    public ValueRule(Integer value) {
        super(RuleType.VALUE);
        this.value = value;
    }

    public ValueRule(Integer value, SpecialEffect specialEffect, String message) {
        super(RuleType.VALUE);
        this.value = value;
        this.specialEffect = specialEffect;
        this.message = message;
    }

    public ValueRule(Integer value, SpecialEffect specialEffect, String message, PlayerPosition playerPosition) {
        super(RuleType.VALUE);
        this.value = value;
        this.specialEffect = specialEffect;
        this.message = message;
        this.playerPosition = playerPosition;
    }

    public Boolean doApplyIt(Integer value) {
        return value == this.value;
    }

    public void applyIt() {
        System.out.println(String.format("%d : %s", value, message));
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
