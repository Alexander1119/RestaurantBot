package com.restaurant.bot.bot;

import com.restaurant.bot.bl.BotBl;

import com.restaurant.bot.dao.CpUSerRepository;
import com.restaurant.bot.domain.Cpuser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {


    BotBl botBl;
    Cpuser user;
    public MainBot(BotBl customerBl) {
        this.botBl = customerBl;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            int numbermessage = botBl.processUpdate(update);

            List<String> listResponses = new ArrayList<>();

            switch (numbermessage) {

                case 1:
                    listResponses.add("Bienvenido/nPrimero debes registrarte");
                    listResponses.add("Ingresa tu apellido");
                    break;
                case 2:
                    listResponses.add("Ingresa tus nombre");
                    break;

            }

            for (String messageText : listResponses) {
                SendMessage message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText(messageText);
                try {
                    this.execute(message);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public boolean SearchIfNewUser(Update update){
        boolean ifNewUser=false;
        CpUSerRepository cpUSerRepository = null;
        Long ChatId=update.getMessage().getChatId();
        if (cpUSerRepository.findByBotUserId(String.valueOf(ChatId))!=null)
            {
                ifNewUser=true;
            }
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
