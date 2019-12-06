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

    public static void main(String[] args) {
        ApiContextInitializer.init();

        SpringApplication.run(BotApplication.class, args);
    }
}
