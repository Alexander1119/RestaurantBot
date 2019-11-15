package com.restaurant.bot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi=new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new RestaurantBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }
}
