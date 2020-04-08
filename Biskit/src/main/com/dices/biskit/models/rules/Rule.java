package com.dices.biskit.models.rules;

import com.dices.biskit.enums.PlayerPosition;
import com.dices.biskit.enums.RuleType;
import com.dices.biskit.enums.SpecialEffect;

public abstract class Rule<T> {

    private RuleType            type;
    protected SpecialEffect     specialEffect = SpecialEffect.NONE;
    protected String            message;
    protected PlayerPosition    playerPosition = PlayerPosition.NONE;

    public Rule(RuleType type) {
        this.type = type;
    }

    abstract public Boolean doApplyIt(T obj);

    abstract public void applyIt();

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public Boolean hasSpecialEffect() {
        return specialEffect != SpecialEffect.NONE;
    }

    public SpecialEffect getSpecialEffect() {
        return specialEffect;
    }

    public void setSpecialEffect(SpecialEffect specialEffect) {
        this.specialEffect = specialEffect;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
