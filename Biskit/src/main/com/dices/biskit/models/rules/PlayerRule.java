package com.dices.biskit.models.rules;

import com.dices.biskit.enums.RuleType;
import com.dices.biskit.models.Player;

public class PlayerRule extends Rule<Player> {

    Player player;

    public PlayerRule() {
        super(RuleType.PLAYER);
    }

    public Boolean applyIt(Player obj) {
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
