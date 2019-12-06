package com.restaurant.bot.bot;

import com.restaurant.bot.bl.BotBl;

import com.restaurant.bot.dao.CpUSerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class MainBot extends TelegramLongPollingBot {

    private static final Logger LOGGER= LoggerFactory.getLogger(MainBot.class);

    BotBl botBl;
    public MainBot(BotBl customerBl) {
        this.botBl = customerBl;
    }
    public MainBot() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        LOGGER.info(String.valueOf(update));
        update.getMessage().getFrom().getId();
        if (update.hasMessage() && update.getMessage().hasText()) {

            List<String> messages=botBl.processUpdate(update);

            LOGGER.info(String.valueOf(messages));
            for (String messageText : messages) {
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

