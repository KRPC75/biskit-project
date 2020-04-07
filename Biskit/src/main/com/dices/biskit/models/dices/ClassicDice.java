package com.dices.biskit.models.dices;

import com.dices.biskit.models.dices.Dice;

import java.util.Arrays;

public class ClassicDice extends Dice {

    public ClassicDice() {
        super(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}
