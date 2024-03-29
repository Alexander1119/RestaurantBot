package com.restaurant.bot.dto;

public enum Status {
    ACTIVE(1), INACTIVE(0);

    Status(int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }
}
