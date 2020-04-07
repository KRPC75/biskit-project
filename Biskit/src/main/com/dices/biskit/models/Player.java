package com.dices.biskit.models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Integer> jails;

    public Player(String name) {
        this.name = name;
        jails = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addJails(Integer digit) {
        jails.add(digit);
    }

    public Boolean hasJail(Integer digit) {
        return jails.contains(digit);
    }

    public List<Integer> getJails() {
        return jails;
    }
}
