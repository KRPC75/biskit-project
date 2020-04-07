package com.dices.biskit.models.rules;

import com.dices.biskit.enums.RuleType;

public class SituationRule extends Rule<String> {

    public SituationRule() {
        super(RuleType.SITUATION);
    }

    public Boolean applyIt(String obj) {
        return null;
    }
}
