package com.dices.biskit.models.rules;

import com.dices.biskit.enums.RuleType;

public class ValueRule extends Rule<Integer> {

    private Integer value;

    public ValueRule() {
        super(RuleType.VALUE);
    }

    public Boolean applyIt(Integer obj) {
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
