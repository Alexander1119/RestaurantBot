package com.restaurant.bot;

import com.restaurant.bot.bl.BotBl;
import com.restaurant.bot.bot.MainBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


@SpringBootApplication
public class BotApplication {


    static BotBl botBl=new BotBl();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi=new TelegramBotsApi();

        try {
            botsApi.registerBot(new MainBot(botBl));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
