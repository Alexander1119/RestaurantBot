package com.restaurant.bot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

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
