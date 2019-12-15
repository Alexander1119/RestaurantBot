package com.restaurant.bot.bot;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.bl.BotBl;

import com.restaurant.bot.dao.CpPersonRepository;
import com.restaurant.bot.dao.CpRestaurantRepository;
import com.restaurant.bot.dao.CpUSerRepository;
import com.restaurant.bot.domain.Cpuser;
import com.restaurant.bot.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class    MainBot extends TelegramLongPollingBot {

    private static final Logger LOGGER= LoggerFactory.getLogger(MainBot.class);
    int numberRegistro=0;
    BotBl botBl;
    String ubi;
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
        /*if(responses.getConversation()==4 && responses.getMessage()==1){
            replyKeyboardMarkup=menuDays();
        }
        if(responses.getConversation()==3 && responses.getMessage()==1){
            replyKeyboardMarkup=menuTimeTable();
        }*/

        //private static final Logger LOGGER= LoggerFactory.getLogger(MainBot.class);
/*
        if(responses.getConversation()==20 &&  responses.getMessage()==7 ){
            //replyKeyboardMarkup=menuInitialRestaurant();
            BigDecimal latitud = new BigDecimal(update.getMessage().getLocation().getLatitude());
            BigDecimal longitud=new BigDecimal(update.getMessage().getLocation().getLongitude());
            ubi=latitud+"@"+longitud;
            if(ubi!=null){
                LOGGER.info(update.getMessage().getText());
            }else{
                System.out.println("Ha caray" );
            }
        }*/
        if(responses.getConversation()==30 && responses.getMessage()==2 ){
            replyKeyboardMarkup=menuInitialRestaurant();
        }
        if((responses.getConversation()==10 || responses.getConversation()==20 || responses.getConversation()==30 || responses.getConversation()==40) && responses.getMessage()==1 ){
            replyKeyboardMarkup=menuInitialUser();
        }

         if(responses.getConversation()==10 && responses.getMessage()==2){
             replyKeyboardMarkup=menuInitialBusqueda();
         }
        if(responses.getConversation()==11 && responses.getMessage()==2){
            replyKeyboardMarkup=menuInitialComida();
        }


        LOGGER.info("numero de Registro es = "+numberRegistro);

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
        keyboardButtons.add("Opciones Cliente");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Ingresar restaurante");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Registrar restaurante");
        listKeyboard.add(keyboardButtons);

        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }

    //Lista de botones para un usuario que tiene registrado un restaurante
    private ReplyKeyboardMarkup menuInitialUserRestaurant(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Buscar restaurantes");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Ingresar Restaurante");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Opciones Cliente");
        listKeyboard.add(keyboardButtons);

        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }

    //Lista de botones para cuando ingresa un usuario en
    //modo restaurante
    private ReplyKeyboardMarkup menuInitialRestaurant(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Ver horario");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Ver menus");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Ver comidas");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Configuracion");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Volver: modo cliente");
        listKeyboard.add(keyboardButtons);

        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }

    private ReplyKeyboardMarkup menuTimeTable(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Registrar horario");
        listKeyboard.add(keyboardButtons);
        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }
    //Metodo con los botones de todos  los dias de la semana
    private ReplyKeyboardMarkup menuDays(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Lunes");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Martes");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Miercoles");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Jueves");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Viernes");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Sabado");
        listKeyboard.add(keyboardButtons);

        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }

    private ReplyKeyboardMarkup menuInitialBusqueda(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Buscar por Ubicacion");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Buscar Comida");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Horario de Atencion");
        listKeyboard.add(keyboardButtons);
        keyboard.setKeyboard(listKeyboard);

        return keyboard;
    }

    private ReplyKeyboardMarkup menuInitialComida(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> listKeyboard=new ArrayList<KeyboardRow>();

        KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Buscar por Ubicsdasfacion");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Buscar Comiddsasfa");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Horario de Atsadasdfasencion");
        listKeyboard.add(keyboardButtons);
        /*KeyboardRow keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Acompaniamientos");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Almuerzo");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Bebidas");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Bocadillos");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Cena");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Comida a la Olla");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Desayuno");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Ensalada");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Panaderia y Masitas");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Pizza");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Platillos Principales");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Sandwiches");
        listKeyboard.add(keyboardButtons);

        keyboardButtons=new KeyboardRow();
        keyboardButtons.add("Sopas");
        listKeyboard.add(keyboardButtons);*/

        keyboard.setKeyboard(listKeyboard);
        return keyboard;
    }

    //public String getBotUsername() { return "BotRestaurant_Bot"; }
    public String getBotUsername(){return "NefertitiBot";}


    @Override
    //public String getBotToken() { return "941260126:AAHg7GOLiBUxbFP14QsgcWENfu0Qt_dP7mc"; }
    public String getBotToken() {return "852637482:AAHRFn6er6MknsLoMnysO6rlTILVzON6ipE";}
}

