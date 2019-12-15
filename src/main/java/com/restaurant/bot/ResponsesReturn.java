package com.restaurant.bot;

public class ResponsesReturn {
    String responses;
    int conversation;
    int message;

    public ResponsesReturn() {
    }

    public ResponsesReturn(String responses, int step) {
        this.responses = responses;
        this.message = step;
    }

    public ResponsesReturn(String responses, int conversation, int message) {
        this.responses = responses;
        this.conversation = conversation;
        this.message = message;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int step) {
        this.message = step;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }
}
