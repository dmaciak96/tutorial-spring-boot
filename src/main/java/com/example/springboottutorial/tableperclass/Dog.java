package com.example.springboottutorial.tableperclass;

import jakarta.persistence.Entity;

@Entity
public class Dog extends Mammal {

    private String bread;

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }
}
