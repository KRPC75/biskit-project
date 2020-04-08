package com.dices.biskit.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public void addJail(Integer digit) {
        jails.add(digit);
    }

    public void removeJail(Integer digit) {
        Predicate<Integer> predicate = number -> number == digit;
        jails.removeIf(predicate);
    }

    public Boolean hasJail(Integer digit) {
        return jails.contains(digit);
    }

    public List<Integer> getJails() {
        return jails;
    }
}
