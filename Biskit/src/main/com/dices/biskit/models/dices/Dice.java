package com.dices.biskit.models.dices;

import java.util.List;
import java.util.Random;

public abstract class Dice {

    private List<Integer>   faces;
    private Integer         value = 0;

    protected Dice(List<Integer> faces) {
        this.faces = faces;
    }

    public Integer throwIt() {
        Random rand = new Random();
        value = faces.get(rand.nextInt(faces.size()));
        return value;
    }

}
