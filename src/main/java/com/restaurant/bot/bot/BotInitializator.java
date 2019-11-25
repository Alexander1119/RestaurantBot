package com.restaurant.bot.bot;

import com.restaurant.bot.bl.BotBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator  {
    BotBl botBl;

    @Autowired
    public BotInitializator(BotBl botBl) {
        this.botBl = botBl;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init(){
        ApiContextInitializer.init();
        TelegramBotsApi botsApi=new TelegramBotsApi();

        try {
            botsApi.registerBot(new MainBot(botBl));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

}
