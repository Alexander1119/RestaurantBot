package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.dao.*;

import com.restaurant.bot.domain.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BotBl {


    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    private CpUSerRepository cpUSerRepository;
    private CpPersonRepository cpPersonRepository;
    private CpRestaurantRepository cpRestaurantRepository;
    private CpChatRepository cpChatRepository;
    private CpTimeTableRepository cpTimeTableRepository;

    @Autowired
    public BotBl(CpUSerRepository cpUSerRepository, CpPersonRepository cpPersonRepository, CpRestaurantRepository cpRestaurantRepository, CpChatRepository cpChatRepository,CpTimeTableRepository cpTimeTableRepository) {
        this.cpUSerRepository = cpUSerRepository;
        this.cpPersonRepository = cpPersonRepository;
        this.cpRestaurantRepository = cpRestaurantRepository;
        this.cpChatRepository = cpChatRepository;
        this.cpTimeTableRepository=cpTimeTableRepository;
    }

    public BotBl() {

    }

    public List<ResponsesReturn> processUpdate(Update update){

        LOGGER.info("Recibiendo update {}",update);
        List<ResponsesReturn> chatResponse = new ArrayList<>();

        //Si el usuario de telegram no esta registrado se registra como user
        // con los datos que tiene registrados en telegram
        //Y si el usuario ya esta registrado saca los datos de la base de datos
        Cpuser cpUser = initUser(update.getMessage().getFrom());

        //Es el metodo donde recepciona los mensajes del usuario
        //Tambien controla las respuestas y donde los mensajes se guardan en base de datos
        continueChatWhitUser(update, cpUser, chatResponse);

        return chatResponse;
    }

    //Es el metodo donde recepciona los mensajes del usuario
    //Tambien controla las respuestas y donde los mensajes se guardan en base de datos
    private void continueChatWhitUser(Update update, Cpuser user,List<ResponsesReturn> chatResponses){
        //Saca el ultimo mensaje del usuario que llega en el update

        Chat lastMessage=cpChatRepository.findLastChatByUserId(user.getUserId());

        ResponsesReturn responses = null;

        if (lastMessage==null){
            //Si no tiene mensajes guarda su primer mensaje en base de datos
            //  if(update.getMessage().getLocation().getLatitude()==floa)
            responses=listResponses(0,0,update.getMessage().getText(),update);
        }else{
            //Si el usuario ya tiene mensajes controla las respuestas que
            // debe tener dependiendo del mensaje recibido

                switch(update.getMessage().getText()){
                    case "Buscar restaurantes":
                        responses = listResponses(10, lastMessage.getMessageId(), update.getMessage().getText(), update);
                        break;

                    case "Registrar restaurante":
                        responses = listResponses(20, lastMessage.getMessageId(), update.getMessage().getText(), update);

                        break;
                    case "Ingresar Restaurante":
                       // responses = new ResponsesReturn("Ingresaste como restaruante",20,1);
                        responses = listResponses(30, lastMessage.getMessageId(), update.getMessage().getText(), update);

                        break;
                    case "Opciones Cliente":
                        responses = listResponses(40, lastMessage.getMessageId(), update.getMessage().getText(), update);
                        break;

                    case "Volver: modo cliente":
                        responses = listResponses(30, lastMessage.getMessageId(), update.getMessage().getText(), update);
                        break;

                    case "Registrar horario":
                        responses = listResponses(31, 1, update.getMessage().getText(), update);
                        break;

                    default:
                        responses = listResponses(lastMessage.getConversationId(), lastMessage.getMessageId(), update.getMessage().getText(), update);
                        break;
                }

           /* if(update.getMessage().getText()=="Registrar restaurante"){
                responses = listResponses(1, lastMessage.getMessageId(), update.getMessage().getText(), update);
            }if (update.getMessage().getText()=="Buscar restaurantes"){
                responses = listResponses(5, lastMessage.getMessageId(), update.getMessage().getText(), update);
            }else{
                responses = listResponses(lastMessage.getConversationId(), lastMessage.getMessageId(), update.getMessage().getText(), update);

            }*/
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), user.getUserId());

        //Se obtiene los datos del update que manda el usuario y los datos que tiene
        //la variable responses para control de conversation, id_message y respuesta
        //que realiza el bot
        Chat chat=new Chat();
        chat.setUserId(user);
        chat.setConversationId(responses.getConversation());
        chat.setMessageId(responses.getMessage());
        chat.setInMessage(update.getMessage().getText());
        chat.setOutMessage(responses.getResponses());
        chat.setMessageDate(new Date());
        chat.setTxDate(new Date());
        chat.setTxUser(user.getUserId().toString());
        chat.setTxHost(update.getMessage().getChatId().toString());
        //Se guarda el chat en la base de datos
        cpChatRepository.save(chat);
        chatResponses.add(responses);
    }

    //El siguiente metodo es el que controla las conversation_id
    private ResponsesReturn listResponses(int conversation,int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();
        RestaurantBl restaurantBl;
        Cpuser cpuser = cpUSerRepository.findByBotUserId(update.getMessage().getChatId().toString());

        switch (conversation){
            case 0:
                //Conversacion inicial para un usuario nuevo en el bot
                responsesReturn.setResponses("Bienvenido a RestaurantBot" +
                        "\nSus datos son los siguientes\n"+
                        update.getMessage().getFrom().getFirstName()+"  "+update.getMessage().getFrom().getLastName());
                responsesReturn.setMessage(1);
                responsesReturn.setConversation(10);
                break;
            case 10:
                responsesReturn=switchMenuBuscar(message,messagereceived,update);

                break;
            case 20:

                //LOGGER.info("Ingresando a la conversacion: "+conversation);

                //Conversacion para el usuario que desea registrar un restaurante

                //Se obtiene el person de la tabla user con el Chat_id que llega del update, para guardar
                //en la tabla restaurant
                if(existsRestaurant(cpuser)){
                    responsesReturn.setConversation(30);
                    responsesReturn.setMessage(1);
                    responsesReturn.setResponses("Usted ya tiene registrado un restaurante");
                    LOGGER.info("El usuario: "+update.getMessage().getChatId()+" Si tiene registrado un restaurante   En la conversacion:"+conversation);

                }else{
                    responsesReturn = switchRegisterRestaurant(conversation, message, messagereceived, update, cpuser);
                }
                break;
            case 30:
                LOGGER.info("Ingresando a la conversacion: "+conversation);
                if (existsRestaurant(cpuser)){
                    LOGGER.info("El usuario: "+update.getMessage().getChatId()+" Si tiene registrado un restaurante En la conversacion: "+conversation);
                    responsesReturn=switchMenuIngresarRestaurant(conversation,message,messagereceived,update);
                }else{
                    LOGGER.info("El usuario: "+update.getMessage().getChatId()+" NO tiene registrado un restaurante");

                    responsesReturn.setConversation(conversation);
                    responsesReturn.setMessage(1);
                    responsesReturn.setResponses("Usted no tiene un restaurante registrado");
                }
                break;
            case 31:
                responsesReturn=switchTimeTable(31,message,messagereceived,update,cpuser);
                break;
            case 32:
                if (existsRestaurant(cpuser)){
                    LOGGER.info("El usuario: "+update.getMessage().getChatId()+" Si tiene registrado un restaurante En la conversacion: "+conversation);
                    responsesReturn=switchMenuIngresarRestaurant(32,message,messagereceived,update);
                }else{
                    LOGGER.info("El usuario: "+update.getMessage().getChatId()+" NO tiene registrado un restaurante");

                    responsesReturn.setConversation(conversation);
                    responsesReturn.setMessage(1);
                    responsesReturn.setResponses("Usted no tiene un restaurante registrado");
                }                break;
            case 40:
                LOGGER.info("Ingresando a la conversacion: "+conversation);

                responsesReturn=switchMenuConfiguracion(message,messagereceived,update);

                break;
            /*case 50:
                Cpuser cpuser2 = cpUSerRepository.findByBotUserId(update.getMessage().getChatId().toString());
                responsesReturn=switchTimeTable(conversation,message,messagereceived,update,cpuser2);
                break;
            case 60:
                break;
            case 70:
                break;*/
        }
        return responsesReturn;
    }


    private ResponsesReturn switchOpcionesHorario(int conversation,int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();

        switch (message){
            case 1:
                responsesReturn.setResponses("Opciones de horario");
                responsesReturn.setConversation(31);
                responsesReturn.setMessage(2);
                break;

        }
        return responsesReturn;
    }

    private ResponsesReturn switchMenuIngresarRestaurant(int conversation,int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();

        switch (message){
            case 1:
                responsesReturn.setResponses("Ingresaste a tu restaurante");
                responsesReturn.setConversation(30);
                responsesReturn.setMessage(2);
                break;
            case 2:
                responsesReturn.setResponses(update.getMessage().getText());
                responsesReturn.setMessage(1);
                responsesReturn.setConversation(31);
                break;
        }
        return responsesReturn;
    }

    private ResponsesReturn switchMenuConfiguracion(int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();

        switch (message){
            case 1:
                responsesReturn.setResponses("Ingresaste a configuracion");
                responsesReturn.setConversation(30);
                responsesReturn.setMessage(1);
                break;
        }
        return responsesReturn;
    }
    private ResponsesReturn switchMenuBuscar(int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();

        switch (message){
            case 1:
                responsesReturn.setResponses("Ingresaste Buscar restaurante");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(10);
                break;
            case 2:
                responsesReturn.setResponses(update.getMessage().getText());
                responsesReturn.setConversation(10);
                responsesReturn.setMessage(2);
                break;
        }
        return responsesReturn;
    }
    private ResponsesReturn switchMenuRestaurant(int conversation,int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();

        switch (message){

            case 1:
                responsesReturn.setResponses("Ingresaste como restaurante");
                responsesReturn.setConversation(3);
                responsesReturn.setMessage(1);
                break;
                    }
        return responsesReturn;
    }

    private ResponsesReturn switchMenuTimeTable(int conversation,int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();

        switch (message){
            case 1:
                responsesReturn.setResponses("Horario");
                responsesReturn.setConversation(4);
                responsesReturn.setMessage(1);
                break;
        }
        return responsesReturn;
    }

    //Control de respuestas y mensajes que devuelve el usuario qeu desea registrar un restaurante
    private ResponsesReturn switchRegisterRestaurant(int conversation,int message, String messagereceived, Update update,Cpuser cpuser){
        ResponsesReturn responsesReturn=new ResponsesReturn();
        switch (message){
            case 1:
                responsesReturn.setResponses("Ingrese el nombre de su restaurante");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(conversation);

                break;
            case 2:
                responsesReturn.setResponses("Ingrese la ciudad en la que se encuentra su restaruante");
                responsesReturn.setMessage(3);
                responsesReturn.setConversation(conversation);
                break;
            case 3:
                responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                responsesReturn.setMessage(4);
                responsesReturn.setConversation(conversation);
                break;
            case 4:
                responsesReturn.setResponses("Ingrese la calle en la que se encuentra su restaruante");
                responsesReturn.setMessage(5);
                responsesReturn.setConversation(conversation);
                break;

            case 5:
                responsesReturn.setResponses("Ingrese la ubicacion del restaurnate");
                responsesReturn.setMessage(6);
                responsesReturn.setConversation(conversation);
                break;

            case 6:
                responsesReturn.setResponses("Ingrese una imagen del restaurante");
                responsesReturn.setMessage(7);
                responsesReturn.setConversation(conversation);
                break;

            case 7:

                responsesReturn.setResponses("GRACIAS!!!!! \n Los datos se guardaron correctamente");
                responsesReturn.setMessage(1);
                responsesReturn.setConversation(30);
                Restaurant restaurant=null;
                restaurant=returnRestaurant(conversation,cpuser,messagereceived);
                cpRestaurantRepository.save(restaurant);
                break;
        }
        return  responsesReturn;
    }


    private ResponsesReturn switchTimeTable(int conversation,int message, String messagereceived, Update update,Cpuser cpuser){
        ResponsesReturn responsesReturn=new ResponsesReturn();
        Restaurant restaurant=new Restaurant();
        switch (message){
            case 1:
                responsesReturn.setResponses("Ingrese el dia");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(31);
                break;
            case 2:
                responsesReturn.setResponses("Ingrese la hora de apertura hh:mm");
                responsesReturn.setMessage(3);
                responsesReturn.setConversation(31);
                break;
            case 3:
                responsesReturn.setResponses("Ingrese la hora de cierre hh:mm");
                responsesReturn.setMessage(4);
                responsesReturn.setConversation(31);
               /* */
                break;
            case 4:
                responsesReturn.setResponses("El horario del restaurante se guardo");
                responsesReturn.setMessage(1);
                responsesReturn.setConversation(32);
                Timetable timetable=null;
                timetable=returnTimeTable(cpuser,messagereceived);
                cpTimeTableRepository.save(timetable);
            break;
        }
        return  responsesReturn;
    }

    private Timetable returnTimeTable(Cpuser cpuser, String lastmessage){
        Timetable timetable=new Timetable();
        ArrayList<Chat> listRegisterTimeTable=new ArrayList<>();
        Restaurant restaurant2=cpRestaurantRepository.findPersonId(cpuser.getPersonId().getPersonId());

        for (int i=0;i<3;i++){
            Chat chat=cpChatRepository.findMessageAndConversationByUserId(cpuser.getUserId(),31,i+3);
            //LOGGER.info("El user: "+cpuser+ "  conversation: "+31+"  message: "+(i+3)+ " inmessage: "+chat.getInMessage());
            listRegisterTimeTable.add(chat);
        }

        LOGGER.info(listRegisterTimeTable.get(0).getInMessage());
        LOGGER.info(listRegisterTimeTable.get(1).getInMessage());

        String open=listRegisterTimeTable.get(1).getInMessage();
        String[] vopen=open.split(":");

        String close=lastmessage;
        String[] vclose=close.split(":");
        timetable.setDay(listRegisterTimeTable.get(0).getInMessage());
        timetable.setOpeningTime(new Time(Integer.parseInt(vopen[0]), Integer.parseInt(vopen[1]),00));
        timetable.setClosingTime(new Time(Integer.parseInt(vclose[0]), Integer.parseInt(vclose[1]),00));
        timetable.setRestaurantId(restaurant2);
        return timetable;
    }

    //En el siguiente metodo se sacan los mensajes de la base de datos para registrar un restaurante
    //Se controla con el in_message y message_id de la tabla chat
    private Restaurant returnRestaurant(int conversation,Cpuser cpuser,String lastmessage){
        Restaurant restaurant=new Restaurant();
        ArrayList<Chat> listRegisterRestaurant=new ArrayList<>();

        for (int i=0;i<9;i++){
            Chat chat=cpChatRepository.findMessageAndConversationByUserId(cpuser.getUserId(),conversation,i+3);
            listRegisterRestaurant.add(chat);
        }

        LOGGER.info(listRegisterRestaurant.get(0).getInMessage());
        LOGGER.info(listRegisterRestaurant.get(1).getInMessage());
        LOGGER.info(listRegisterRestaurant.get(2).getInMessage());
        LOGGER.info(listRegisterRestaurant.get(3).getInMessage());
        LOGGER.info(listRegisterRestaurant.get(4).getInMessage());

        restaurant.setRestaurantName(listRegisterRestaurant.get(0).getInMessage());
        restaurant.setCity(listRegisterRestaurant.get(1).getInMessage());
        restaurant.setZone(listRegisterRestaurant.get(2).getInMessage());
        restaurant.setStreet(listRegisterRestaurant.get(3).getInMessage());
        restaurant.setLatitude(new BigDecimal(123.123));
        restaurant.setLongitude(new BigDecimal(123.123));
        restaurant.setImages(lastmessage);
        restaurant.setStatus(1);
        restaurant.setTxUser("Admin");
        restaurant.setTxHost("localhost");
        restaurant.setTxDate(new Date());
        restaurant.setPersonId(cpuser.getPersonId());
        return restaurant;
    }

    //Metodo donde busca si el usuario de telegram esta registrado o no
    private Cpuser initUser(User user) {
        System.out.printf("ID DEL BOT USER ES " + user.getId());
        Cpuser cpuser = null;

        cpuser = cpUSerRepository.findByBotUserId(user.getId().toString());

        if (cpuser==null){
            Person person=new Person();
            person.setFirstName(user.getFirstName());
            person.setLastName(user.getLastName());
            person.setCellphoneNumber(123456789);
            person.setTxHost("localhost");
            person.setTxUser("admin");
            person.setTxDate(new Date());
            cpPersonRepository.save(person);

            cpuser =new Cpuser();
            cpuser.setBotUserId(user.getId().toString());
            cpuser.setPersonId(person);
            cpuser.setTxHost("localhost");
            cpuser.setTxUser("admin");
            cpuser.setTxDate(new Date());
            cpUSerRepository.save(cpuser);
        }else{
            System.out.println("EL USUAIO  FUE ENCONTRADO" + cpuser.toString());
        }
        return cpuser;
    }

    private boolean existsRestaurant(Cpuser cpuser){

        if (cpRestaurantRepository.findPersonId(cpuser.getPersonId().getPersonId())!=null){
            return true;
        }else{
            return false;
        }

    }



}
