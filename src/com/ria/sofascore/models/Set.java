package com.ria.sofascore.models;

import java.util.ArrayList;

public class Set {

    private String setTitle;
    private ArrayList<SubSet> subSets = new ArrayList<>();

    public String getSetTitle() {
        return setTitle;
    }

    public void setSetTitle(String setTitle) {
        this.setTitle = setTitle;
    }

    public ArrayList<SubSet> getSubSets() {
        return subSets;
    }

    public void setSubSets(ArrayList<SubSet> subSets) {
        this.subSets = subSets;
    }
}
