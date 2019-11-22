package com.restaurant.bot.bot;

import com.restaurant.bot.bl.BotBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotInitializator  {
    BotBl botBl;

    @Autowired
    public BotInitializator(BotBl botBl) {
        this.botBl = botBl;
    }

    public BotInitializator() {
    }

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
