package com.example.wifigo;

public class Pair {
    private Double x;
    private Double y;
    public Pair() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Pair(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getx() {
        return this.x;
    }

    public Double gety() {
        return this.y;
    }
}
