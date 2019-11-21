package com.restaurant.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;


@Component
public class RestaurantBot extends TelegramLongPollingBot {


    private ArrayList<ChatIdResponse> listChatId=new ArrayList<  >();

    private String[] inicio={"Ingrese nombre del restaurante","Ingrese la direccion del restaurante"};
    private String[] messageRegisterRestaurant={"Ingrese nombre del restaurante","Ingrese la direccion del restaurante"};
    private String[] messageRegisterClient={"Ingrese nombre del restaurante","Ingrese la direccion del restaurante"};

    int startStep=0;

    @Override
    public void onUpdateReceived(Update update) {

        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        ChatIdResponse chatIdResponse=new ChatIdResponse(chat_id,message_text);
        //SendMessage response=new SendMessage();

        if(searchNewId(chatIdResponse)){
            listChatId.add(chatIdResponse) ;

            responseStart(chatIdResponse);

            /*if(message_text!="/Inicio"){
                        response.setChatId(chat_id);
                        response.setText("/Inicio para empezar");
                try {
                    execute(response); // Sending our message object to user
                }catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                System.out.println();
            }*/
        }else{
            /*
            if(message_text!="/Inicio" && startStep==0){
                        response.setChatId(chat_id);
                        response.setText("/Inicio para empezar");

            }else{
                startStep=1;
                response.setChatId(chat_id);
                response.setText("Desea ingresar como /Cliente o /Restaurante");
                messageUpdateChatId(new ChatIdResponse(update.getMessage().getChatId(),update.getMessage().getText()));
            }*/
        }

        }

        public void responseStart(ChatIdResponse chatIdResponse){

            SendMessage message=new SendMessage();
            switch (startStep){
                case 0:



                    break;
                case 1:

                    break;
            }



        }
    public boolean searchNewId(ChatIdResponse chatIdResponse){
        boolean newChat=false;
        for(int i=0;i<listChatId.size();i++) {

            if(listChatId.get(0).equals(chatIdResponse.chatId)) {
                newChat=false;
            }else {
                newChat = true;
            }
        }
        return newChat;
    }

    public void messageUpdateChatId(ChatIdResponse chatIdResponse){


        for (int i=0;i<listChatId.size();i++){
            if (listChatId.get(i).chatId== chatIdResponse.chatId){
                listChatId.get(i).messageReceived=chatIdResponse.messageReceived;
            }
        }

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