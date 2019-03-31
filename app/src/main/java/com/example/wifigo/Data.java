package com.example.wifigo;

public class Data {
    public Double x;
    public Double y;
    public int strength;
    public String SSID;
    public int rate;

    public Data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Data(Double x, Double y, int strength, String SSID, int rate) {
        this.x = x;
        this.y = y;
        this.strength = strength;
        this.SSID = SSID;
        this.rate = rate;
    }
}
