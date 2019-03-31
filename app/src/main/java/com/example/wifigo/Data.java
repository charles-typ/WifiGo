package com.example.wifigo;

public class Data {
    public Double x;
    public Double y;
    public Integer strength;
    public String SSID;
    public Integer rate;

    public Data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Data(Double x, Double y, Integer strength, String SSID, Integer rate) {
        this.x = x;
        this.y = y;
        this.strength = strength;
        this.SSID = SSID;
        this.rate = rate;
    }
}
