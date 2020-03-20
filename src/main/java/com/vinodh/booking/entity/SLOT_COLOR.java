package com.vinodh.booking.entity;

/**
 * @author Vinodh Kumar T
 */
public enum SLOT_COLOR {
    AVAILABLE("primary"), BLOCKED("warn"), BOOKED("");

    private String color;

    SLOT_COLOR(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
