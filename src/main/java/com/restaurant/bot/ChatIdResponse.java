package com.restaurant.bot;

public class ChatIdResponse {

    long chatId;
    String messageReceived;

    public ChatIdResponse(long chatId, String messageReceived) {
        this.chatId = chatId;
        this.messageReceived = messageReceived;
    }


}
