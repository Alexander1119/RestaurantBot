package com.restaurant.bot.bot;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.bl.BotBl;

import com.restaurant.bot.dao.CpUSerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

public class    MainBot extends TelegramLongPollingBot {

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

            List<ResponsesReturn> messages=botBl.processUpdate(update);
            ResponsesReturn responses=messages.get(0);

            responsesToChatUSer(update,responses,messages);
            /*for (ResponsesReturn messageText : messages) {
                SendMessage message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText(messageText.getResponses());
                try {
                    this.execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }


    //Control de los momentos en los que tiene que mostrar los botones
    private void responsesToChatUSer(Update update, ResponsesReturn responses,List<ResponsesReturn> listMessage){

        ReplyKeyboardMarkup replyKeyboardMarkup=null;

        //Condicion para mostrar los botones cuando la conversacion_id=1 y message_id=1
        if (responses.getConversation()==1 && responses.getMessage()==1){
            replyKeyboardMarkup=menuInitialUser();
        }

        //manda el mensaje de respuesta al usuario
        for (ResponsesReturn messageText : listMessage) {

            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(messageText.getResponses());
            if(replyKeyboardMarkup!=null){
                message.setReplyMarkup(replyKeyboardMarkup);
            }else{
                ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
                message.setReplyMarkup(keyboardMarkupRemove);
            }
            try {
                this.execute(message);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }


    //Metodo donde definimos el menu de botones para un usuario-cliente
    private ReplyKeyboardMarkup menuInitialUser(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Buscar restaurantes");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Registrar restaurante");
        listKeyboard.add(keyboardButtons);

        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }
    public String getBotUsername() { return "BotRestaurant_Bot"; }
    //public String getBotUsername(){return "NefertitiBot";}

    @Override
    public String getBotToken() { return "941260126:AAHg7GOLiBUxbFP14QsgcWENfu0Qt_dP7mc"; }
    //public String getBotToken() {return "852637482:AAHRFn6er6MknsLoMnysO6rlTILVzON6ipE";}
}

