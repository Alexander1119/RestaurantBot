package com.restaurant.bot;

public class ResponsesReturn {
    String responses;
    int step;

    public ResponsesReturn() {
    }

    public ResponsesReturn(String responses, int step) {
        this.responses = responses;
        this.step = step;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
