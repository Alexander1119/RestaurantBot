package com.restaurant.bot.bot;

import com.restaurant.bot.bl.BotBl;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.swing.*;

public class MainBot extends TelegramLongPollingBot {

    BotBl botBl;

    public MainBot(BotBl customerBl) {
        this.botBl = customerBl;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

        }



    }
/*
    public String Responses(){

        int startStep =0;

        switch (startStep){
            case 1:


                break;
            case 2:
                break;
        }
    }*/


    public boolean SearchIfNewUser(Update update){
        boolean ifNewUser=false;




        return ifNewUser;
    }
    @Override
    public String getBotUsername() {
        return "BotRestaurant_Bot";
    }

    @Override
    public String getBotToken() {
        return "941260126:AAHg7GOLiBUxbFP14QsgcWENfu0Qt_dP7mc";
    }


}
