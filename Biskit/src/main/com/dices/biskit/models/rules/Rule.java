package com.dices.biskit.models.rules;

import com.dices.biskit.enums.RuleType;

public abstract class Rule<T> {

    private RuleType    type;
    private String      message;

    public Rule(RuleType type) {
        this.type = type;
    }

    abstract public Boolean applyIt(T obj);

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
