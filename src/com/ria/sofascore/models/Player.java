package com.ria.sofascore.models;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Set> sets = new ArrayList<>();

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
