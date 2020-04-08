package com.dices.biskit.utils;

import com.dices.biskit.models.dices.Dice;

import java.util.List;

public class DiceUtils {

    public static Boolean isDouble(List<Dice> diceList, Integer doubleValue) {
        return diceList.stream().allMatch(dice -> dice.getValue() == doubleValue);
    }

}
