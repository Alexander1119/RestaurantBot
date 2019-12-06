package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.dao.CpRestaurantRepository;
import com.restaurant.bot.domain.Person;
import com.restaurant.bot.domain.Restaurant;
import com.restaurant.bot.dto.RestaurantDto;
import com.restaurant.bot.dto.Status;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.util.Date;

public class RestaurantBl {
    CpRestaurantRepository cpRestaurantRepository;

    public RestaurantBl() {
    }

    public RestaurantBl(CpRestaurantRepository cpRestaurantRepository) {
        this.cpRestaurantRepository = cpRestaurantRepository;
    }


    public ResponsesReturn restaurantRegister(int numberConversation,int numberMessage, String lastMessage, Update update){
        int step=0;
        int person_id,status;
        String restaurant_name=null,zone=null,city = null,street=null,images=null;
        BigDecimal longitude,latitude;
        ResponsesReturn responsesReturn=new ResponsesReturn();
        switch (numberMessage){
            case 1:
                responsesReturn.setResponses("Ingrese el nombre de su restaurante");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(1);

            case 2:
                restaurant_name=(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                responsesReturn.setMessage(3);
                responsesReturn.setConversation(1);

            case 3:
                zone=(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la calle en la que se encuentra su restaruante");
                responsesReturn.setMessage(4);
                responsesReturn.setConversation(1);

            case 4:
                street=(lastMessage);
                responsesReturn.setResponses("Ingrese la ubicacion del restaurnate");
                responsesReturn.setMessage(5);
                responsesReturn.setConversation(1);

            case 5:

                responsesReturn.setResponses("Ingrese una imagen del restaurante");
                responsesReturn.setMessage(6);
                responsesReturn.setConversation(1);

            case 6:

              /*  Person person=new Person();
                person.setFirstName(update.getMessage().getFrom().getFirstName());
                person.setLastName(update.getMessage().getFrom().getLastName());
                person.setCellphoneNumber(123456789);
                person.setTxHost("loca lhost");
                person.setTxUser("admin");
                person.setTxDate(new Date());
                Restaurant restaurant=new Restaurant();
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
                cpRestaurantRepository.save(restaurant);*/
                responsesReturn.setResponses("Sus datos son los siguientes: ");
                responsesReturn.setMessage(7);
                responsesReturn.setConversation(2);

        }

        return responsesReturn;
    }
}


