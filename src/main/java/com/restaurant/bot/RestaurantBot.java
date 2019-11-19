package com.restaurant.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;


@Component
public class RestaurantBot extends TelegramLongPollingBot {


    private ArrayList<Long> listChatId=new ArrayList<  >();

    private String[] inicio={"Ingrese nombre del restaurante","Ingrese la direccion del restaurante"};
    private String[] messageRegisterRestaurant={"Ingrese nombre del restaurante","Ingrese la direccion del restaurante"};
    private String[] messageRegisterClient={"Ingrese nombre del restaurante","Ingrese la direccion del restaurante"};


    @Override
    public void onUpdateReceived(Update update) {

        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        if(searchNewId(chat_id)){
            listChatId.add(chat_id);
        }else{


        }
            SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                         .setText("   ////   ");

            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    public boolean searchNewId(Long Id){
        boolean newChat=false;
        for(int i=0;i<listChatId.size();i++) {

            if(listChatId.get(0) ==Id) {
                newChat=false;
            }else {
                newChat = true;
            }
        }
        return newChat;
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