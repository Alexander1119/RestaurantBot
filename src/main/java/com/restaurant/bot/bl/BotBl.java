package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.dao.CpChatRepository;
import com.restaurant.bot.dao.CpUSerRepository;
import com.restaurant.bot.dao.CpPersonRepository;
import com.restaurant.bot.dao.CpRestaurantRepository;

import com.restaurant.bot.domain.Chat;
import com.restaurant.bot.domain.Cpuser;
import com.restaurant.bot.domain.Person;
import com.restaurant.bot.domain.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;
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
    ArrayList<String> dataRestaurant=new ArrayList<>();

    @Autowired
    public BotBl(CpUSerRepository cpUSerRepository, CpPersonRepository cpPersonRepository, CpRestaurantRepository cpRestaurantRepository, CpChatRepository cpChatRepository) {
        this.cpUSerRepository = cpUSerRepository;
        this.cpPersonRepository = cpPersonRepository;
        this.cpRestaurantRepository = cpRestaurantRepository;
        this.cpChatRepository = cpChatRepository;
    }

    public BotBl() {

    }

    public List<String> processUpdate(Update update){

        LOGGER.info("Recibiendo update {}",update);
        List<String> chatResponse = new ArrayList<>();

       // System.out.println(update.getMessage().getFrom().getFirstName()+"  "+update.getMessage().getFrom().getLastName()+"    "+update.getMessage().getFrom().getId()+"       "+update.getMessage().getFrom().hashCode());
        Cpuser cpUser = initUser(update.getMessage().getFrom());

        continueChatWhitUser(update, cpUser, chatResponse);


        return chatResponse;
    }

    private void continueChatWhitUser(Update update, Cpuser user,List<String> chatResponses){
        Chat lastMessage=cpChatRepository.findLastChatByUserId(user.getUserId());
        ResponsesReturn responses = null;
        if (lastMessage==null){
            responses=listResponses(0,0,update.getMessage().getText(),update);
        }else{
                responses=listResponses(lastMessage.getConversationId(),lastMessage.getMessageId(),update.getMessage().getText(),update);
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), user.getUserId());
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
        cpChatRepository.save(chat);
        //LOGGER.info("THE MESSAGE SAVE AS: {}", chat.toString());
        chatResponses.add(responses.getResponses());
    }

    private ResponsesReturn listResponses(int conversation,int message, String messagereceived, Update update){
        ResponsesReturn responsesReturn=new ResponsesReturn();
        RestaurantBl restaurantBl;
        switch (conversation){
            case 0:
                        responsesReturn.setResponses("Bienvenido a RestaurantBot" +
                        "\nSus datos son los siguientes\n"+
                        update.getMessage().getFrom().getFirstName()+"  "+update.getMessage().getFrom().getLastName());
                        responsesReturn.setMessage(1);
                        responsesReturn.setConversation(1);
                break;
            case 1:

                Cpuser cpuser=cpUSerRepository.findByBotUserId(update.getMessage().getChatId().toString());
                //Restaurant restaurant=new Restaurant();
                switch (message){
                    case 1:
                        responsesReturn.setResponses("Ingrese el nombre de su restaurante");
                        responsesReturn.setMessage(2);
                        responsesReturn.setConversation(1);
                        LOGGER.info("El mensaje de incio de registro es : "+messagereceived);

                        break;
                    case 2:
                        dataRestaurant.add(messagereceived);
                        responsesReturn.setResponses("Ingrese la ciudad en la que se encuentra su restaruante");
                        responsesReturn.setMessage(3);
                        responsesReturn.setConversation(1);
                        LOGGER.info("El nombre del restaurante es: "+messagereceived);
                        break;
                    case 3:
                        dataRestaurant.add(messagereceived);
                        responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                        responsesReturn.setMessage(4);
                        responsesReturn.setConversation(1);
                       // LOGGER.info(city);
                        break;
                    case 4:
                        dataRestaurant.add(messagereceived);
                        responsesReturn.setResponses("Ingrese la calle en la que se encuentra su restaruante");
                        responsesReturn.setMessage(5);
                        responsesReturn.setConversation(1);
                       // LOGGER.info(zone);

                        break;

                    case 5:
                        dataRestaurant.add(messagereceived);
                        responsesReturn.setResponses("Ingrese la ubicacion del restaurnate");
                        responsesReturn.setMessage(6);
                        responsesReturn.setConversation(1);
                        //LOGGER.info(street);

                        break;

                    case 6:

                        responsesReturn.setResponses("Ingrese una imagen del restaurante");
                        responsesReturn.setMessage(7);
                        responsesReturn.setConversation(1);
                        break;

                    case 7:

                        dataRestaurant.add(messagereceived);
                        LOGGER.info("La cantidad de datos son: " +dataRestaurant.size());
                        Restaurant restaurant=null;
                        restaurant=returnRestaurant(dataRestaurant,cpuser);
                        cpRestaurantRepository.save(restaurant);
                        responsesReturn.setResponses("Sus datos son los siguientes: ");
                        responsesReturn.setMessage(8);
                        responsesReturn.setConversation(1);
                        break;
                    case 8:
                        responsesReturn.setResponses("Gracias!!!!!");
                        responsesReturn.setMessage(8);
                        responsesReturn.setConversation(1);
                }

                break;
        }
        return responsesReturn;
    }
    public Restaurant returnRestaurant(ArrayList<String> data,Cpuser cpuser){
        Restaurant restaurant=new Restaurant();

        for (int i=0;i<data.size();i++){
            LOGGER.info("numero: "+i+" dato: "+data.get(i));
        }
        restaurant.setRestaurantName(data.get(0));
        restaurant.setCity(data.get(1));
        restaurant.setZone(data.get(2));
        restaurant.setStreet(data.get(3));
        restaurant.setLatitude(new BigDecimal(123.123));
        restaurant.setLongitude(new BigDecimal(123.123));
        restaurant.setImages(data.get(4));
        restaurant.setStatus(1);
        restaurant.setTxUser("Admin");
        restaurant.setTxHost("localhost");
        restaurant.setTxDate(new Date());
        restaurant.setPersonId(cpuser.getPersonId());
        return restaurant;
    }
    public boolean SearchIfNewUser(Update update){
        boolean ifNewUser=false;
        CpUSerRepository cpUSerRepository = null;
        Long ChatId=update.getMessage().getChatId();
        if (cpUSerRepository.findByBotUserId(String.valueOf(ChatId))!=null)
        {
            ifNewUser=true;
        }
        return ifNewUser;
    }

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
}
