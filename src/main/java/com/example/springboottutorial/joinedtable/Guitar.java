package com.example.springboottutorial.joinedtable;

import jakarta.persistence.Entity;

@Entity
public class Guitar extends Instrument {

    private String numberOfStrings;

    public String getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(String numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }
}
