package com.dices.biskit.models;

import com.dices.biskit.models.dices.Dice;

import java.util.List;

public class Result {

    private List<Dice>  diceList;
    private Integer     total;

    public Result(List<Dice> diceList) {
        this.diceList = diceList;
    }

    public void throwDices() {
        total = 0;
        for (Dice dice : diceList) {
            total += dice.throwIt();
        }
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public void setDiceList(List<Dice> diceList) {
        this.diceList = diceList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
