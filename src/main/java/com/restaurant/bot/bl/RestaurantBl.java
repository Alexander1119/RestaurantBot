package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.bot.MainBot;
import com.restaurant.bot.dao.CpPersonRepository;
import com.restaurant.bot.dao.CpRestaurantRepository;
import com.restaurant.bot.dao.CpUSerRepository;
import com.restaurant.bot.domain.Cpuser;
import com.restaurant.bot.domain.Cpuser_;
import com.restaurant.bot.domain.Person;
import com.restaurant.bot.domain.Restaurant;
import com.restaurant.bot.dto.RestaurantDto;
import com.restaurant.bot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class RestaurantBl {
    private static final Logger LOGGER= LoggerFactory.getLogger(RestaurantBl.class);

    private CpRestaurantRepository cpRestaurantRepository;
    private CpPersonRepository cpPersonRepository;
    private CpUSerRepository cpUSerRepository;

    @Autowired
    public RestaurantBl(CpRestaurantRepository cpRestaurantRepository, CpPersonRepository cpPersonRepository, CpUSerRepository cpUSerRepository) {
        this.cpRestaurantRepository = cpRestaurantRepository;
        this.cpPersonRepository = cpPersonRepository;
        this.cpUSerRepository = cpUSerRepository;
    }



    public RestaurantBl() {
    }

    public ResponsesReturn restaurantRegister(int numberConversation,int numberMessage, String lastMessage, Update update){
        int step=0;
        int person_id,status;
        String restaurant_name="",zone= "",city = "",street="",images="";
        BigDecimal longitude,latitude;
        ResponsesReturn responsesReturn=new ResponsesReturn();
        //Cpuser cpuser=cpUSerRepository.findByBotUserId(update.getMessage().getChatId().toString());
        //LOGGER.info(cpuser.getPersonId().getFirstName()+" "+cpuser.getPersonId().getLastName());
       // System.out.println("El user es: "+cpuser.getBotUserId());
        /*
        Person person=new Person();
        person.setFirstName(update.getMessage().getFrom().getFirstName());
        person.setLastName(update.getMessage().getFrom().getLastName());
        person.setCellphoneNumber(123456789);
        person.setTxHost("localhost");
        person.setTxUser("admin");
        person.setTxDate(new Date());
        LOGGER.info("Los datos ingresados de la persona en el RestaurantBl son: "+person.getFirstName()+" "+person.getLastName());
        cpPersonRepository.save(person);*/
        switch (numberMessage){
            case 1:
                responsesReturn.setResponses("Ingrese el nombre de su restaurante");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(1);
                break;
            case 2:
                restaurant_name=restaurant_name+update.getMessage().getText();
                responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                responsesReturn.setMessage(3);
                responsesReturn.setConversation(1);
                LOGGER.info(restaurant_name);
                break;
            case 3:
                city=city+update.getMessage().getText();
                responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                responsesReturn.setMessage(4);
                responsesReturn.setConversation(1);
                LOGGER.info(city);
                break;
            case 4:
                zone=zone+(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la calle en la que se encuentra su restaruante");
                responsesReturn.setMessage(5);
                responsesReturn.setConversation(1);
                LOGGER.info(zone);

                break;

            case 5:
                street=street+(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la ubicacion del restaurnate");
                responsesReturn.setMessage(6);
                responsesReturn.setConversation(1);
                LOGGER.info(street);

                break;

            case 6:
                responsesReturn.setResponses("Ingrese una imagen del restaurante");
                responsesReturn.setMessage(7);
                responsesReturn.setConversation(1);
                break;

            case 7:
                /*Restaurant restaurant=new Restaurant();
                restaurant.setPersonId(person);
                restaurant.setRestaurantName(restaurant_name);
                restaurant.setCity(city);
                restaurant.setZone(zone);
                restaurant.setStreet(street);
                restaurant.setImages(images);
                restaurant.setLongitude(new BigDecimal(123.12));
                restaurant.setLatitude(new BigDecimal(123.12));
                restaurant.setStatus(1);
                restaurant.setTxUser(update.getMessage().getChatId().toString());
                restaurant.setTxHost("localhost");
                restaurant.setTxDate(new Date());

                LOGGER.info("Los datos son los siguientes: " +restaurant.getRestaurantName());
                //cpRestaurantRepository.save(restaurant);*/
                responsesReturn.setResponses("Sus datos son los siguientes: ");
                responsesReturn.setMessage(7);
                responsesReturn.setConversation(2);
                break;
        }
        return responsesReturn;
    }
}


