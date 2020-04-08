package com.dices.biskit.utils;

import com.dices.biskit.enums.PlayerPosition;
import com.dices.biskit.enums.SpecialEffect;
import com.dices.biskit.models.rules.DoubleRule;
import com.dices.biskit.models.rules.Rule;
import com.dices.biskit.models.rules.ValueRule;

import java.util.ArrayList;
import java.util.List;

public class Biskit {

    public static List<Rule> getRules() {
        List<Rule> basicRules = new ArrayList<>();
        basicRules.addAll(makeValueRules());
        basicRules.addAll(makeDoubleRules());

        return basicRules;
    }

    static List<ValueRule>  makeValueRules() {
        List<ValueRule> valueRuleList = new ArrayList<>();

        // 3
        valueRuleList.add(makeThreeRule());

        // 4
        valueRuleList.add(makeFourRule());

        // 7
        valueRuleList.add(makeSevenRule());

        // 8
        valueRuleList.add(makeEightRule());

        // 9
        valueRuleList.add(makeNineRule());

        // 10
        valueRuleList.add(makeTenRule());

        // 11
        valueRuleList.add(makeElevenRule());

        // 12
        valueRuleList.add(makeTwelveRule());

        return valueRuleList;
    }

    public static ValueRule makeThreeRule() {
        return new ValueRule(3, SpecialEffect.DUEL, "Duel !");
    }

    public static ValueRule makeFourRule() {
        return new ValueRule(4, SpecialEffect.PASS, "Saut de tour");
    }

    public static ValueRule makeSevenRule() {
        return new ValueRule(7, SpecialEffect.TALK, "Biskit !");
    }

    public static ValueRule makeEightRule() {
        return new ValueRule(8, SpecialEffect.REVERT, "Changement de sens");
    }

    public static ValueRule makeNineRule() {
        return new ValueRule(9, SpecialEffect.DRINK, "La personne précédente boit", PlayerPosition.PREV);
    }

    public static ValueRule makeTenRule() {
        return new ValueRule(10, SpecialEffect.DRINK, "La personne jouant boit", PlayerPosition.CURRENT);
    }

    public static ValueRule makeElevenRule() {
        return new ValueRule(11, SpecialEffect.DRINK, "La personne suivante boit", PlayerPosition.NEXT);
    }

    public static ValueRule makeTwelveRule() {
        return new ValueRule(12, SpecialEffect.NEW, "Nouvelle règle");
    }

    public static List<DoubleRule> makeDoubleRules() {
        List<DoubleRule> doubleRuleList = new ArrayList<>();
        doubleRuleList.addAll(makeDrinkDoubleRules());
        doubleRuleList.add(makeDoubleThreeRule());
        doubleRuleList.add(makeDoubleFiveRule());

        return doubleRuleList;
    }

    public static List<DoubleRule> makeDrinkDoubleRules() {
        List<DoubleRule> doubleRuleList = new ArrayList<>();
        doubleRuleList.add(makeDoubleOneDrinkRule());
        doubleRuleList.add(makeDoubleTwoDrinkRule());
        doubleRuleList.add(makeDoubleThreeDrinkRule());
        doubleRuleList.add(makeDoubleFourDrinkRule());
        doubleRuleList.add(makeDoubleFiveDrinkRule());
        doubleRuleList.add(makeDoubleSixDrinkRule());

        return doubleRuleList;
    }

    public static DoubleRule makeDoubleOneDrinkRule() {
        return new DoubleRule(1, SpecialEffect.DRINK, "Donne 1 gorgée", PlayerPosition.TARGET);
    }

    public static DoubleRule makeDoubleTwoDrinkRule() {
        return new DoubleRule(2, SpecialEffect.DRINK, "Donne 2 gorgées", PlayerPosition.TARGET);
    }

    public static DoubleRule makeDoubleThreeDrinkRule() {
        return new DoubleRule(3, SpecialEffect.DRINK, "Donne 3 gorgées", PlayerPosition.TARGET);
    }

    public static DoubleRule makeDoubleFourDrinkRule() {
        return new DoubleRule(4, SpecialEffect.DRINK, "Donne 4 gorgées", PlayerPosition.TARGET);
    }

    public static DoubleRule makeDoubleFiveDrinkRule() {
        return new DoubleRule(5, SpecialEffect.DRINK, "Donne 5 gorgées", PlayerPosition.TARGET);
    }

    public static DoubleRule makeDoubleSixDrinkRule() {
        return new DoubleRule(6, SpecialEffect.DRINK, "Donne 6 gorgées", PlayerPosition.TARGET);
    }

    public static DoubleRule makeDoubleThreeRule() {
        return new DoubleRule(3, SpecialEffect.JAIL, "Prison de 3");
    }

    public static DoubleRule makeDoubleFiveRule() {
        return new DoubleRule(3, SpecialEffect.JAIL, "Prison de 3");
    }
}
