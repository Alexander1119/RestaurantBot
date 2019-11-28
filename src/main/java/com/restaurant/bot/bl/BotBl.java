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

import java.time.Period;
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

    private String[] list={"-","Bienvenido a RestaurantBot\n" +
                            "Sus datos son los siguientes: \n Desea cambiar o guardar "};

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
            responses=listResponses(0,"-",update.getMessage().getFrom());
        }else{
                int lastMessageInt=Integer.parseInt(lastMessage.getOutMessage());
                responses=listResponses(lastMessageInt,lastMessage.getInMessage(),update.getMessage().getFrom());
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), user.getUserId());
        Chat chat=new Chat();
        chat.setUserId(user);
        chat.setInMessage(update.getMessage().getText());
        chat.setOutMessage(String.valueOf(responses.getStep()));
        chat.setMessageDate(new Date());
        chat.setTxDate(new Date());
        chat.setTxUser(user.getUserId().toString());
        chat.setTxHost(update.getMessage().getChatId().toString());
        cpChatRepository.save(chat);
        //LOGGER.info("THE MESSAGE SAVE AS: {}", chat.toString());

        chatResponses.add(responses.getResponses());
    }

    private ResponsesReturn listResponses(int numberout, String messagereceived, User user){
        String responses=null;
        ResponsesReturn responsesReturn=new ResponsesReturn();
        switch (numberout){
            case 0:
                        responsesReturn.setResponses("Bienvenido a RestaurantBot" +
                        "\nSus datos son los siguientes\n"+
                        user.getFirstName()+"  "+user.getLastName()+"\n"+
                                "Son correctos /Si /No");
                        responsesReturn.setStep(1);

                break;
            case 1:
                if (messagereceived==("Si") || messagereceived==("/Si") || messagereceived.equals("si") || messagereceived.equals("/si")) {
                    responsesReturn.setResponses("Los datos se guardaran  " + user.getFirstName());
                    responsesReturn.setStep(2);
                    break;
                }if (messagereceived.equals("No") || messagereceived.equals("/No")){
                    responsesReturn.setResponses("Los datos se cambiaran"+ user.getFirstName());
                    responsesReturn.setStep(2);
                    break;
                }else{
                    responsesReturn.setResponses("Debe elegir entre si o no "+ user.getFirstName());
                    responsesReturn.setStep(1);
                    break;
                }

            case 2:
                responsesReturn.setResponses("Este es el tercer mensaje" +user.getFirstName());
                responsesReturn.setStep(2);
                break;

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
                 person.setCellPhoneNum(123456789);
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
