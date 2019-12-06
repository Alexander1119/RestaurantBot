package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.dao.CpChatRepository;
import com.restaurant.bot.dao.CpUSerRepository;
import com.restaurant.bot.dao.CpPersonRepository;
import com.restaurant.bot.dao.CpRestaurantRepository;

import com.restaurant.bot.domain.Chat;
import com.restaurant.bot.domain.Cpuser;
import com.restaurant.bot.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

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
            responses=listResponses(1,1,"-",update);
        }else{
                responses=listResponses(lastMessage.getConversationId(),lastMessage.getMessageId(),lastMessage.getOutMessage(),update);
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

                break;
            case 1:
                    restaurantBl=new RestaurantBl();
                    responsesReturn=restaurantBl.restaurantRegister(conversation,message,messagereceived,update);
                    LOGGER.info(responsesReturn.getResponses()+" "+responsesReturn.getConversation()+"  "+responsesReturn.getMessage());
        }
        return responsesReturn;
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
                 person.setTxHost("loca lhost");
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
