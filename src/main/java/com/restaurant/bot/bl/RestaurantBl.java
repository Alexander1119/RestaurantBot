package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.dao.CpRestaurantRepository;
import com.restaurant.bot.domain.Restaurant;
import com.restaurant.bot.dto.RestaurantDto;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RestaurantBl {
    CpRestaurantRepository cpRestaurantRepository;


    public RestaurantBl(CpRestaurantRepository cpRestaurantRepository) {
        this.cpRestaurantRepository = cpRestaurantRepository;
    }


    public ResponsesReturn restaurantRegister(int numberMessage, String lastMessage, Update update){
        int step=0;
        ResponsesReturn responsesReturn=new ResponsesReturn();
        RestaurantDto restaurantDto=new RestaurantDto();
        Restaurant restaurant=new Restaurant();
        switch (step){
            case 1:
                responsesReturn.setResponses("Ingrese el nombre de su restaurante");
                responsesReturn.setStep(2);

            case 2:
                restaurant.setRestaurantName(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                responsesReturn.setStep(3);
            case 3:
                restaurant.setZone(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la calle en la que se encuentra su restaruante");
                responsesReturn.setStep(4);
            case 4:
                restaurant.setStreet(lastMessage);
                responsesReturn.setResponses("Ingrese la ubicacion del restaurnate");
                responsesReturn.setStep(5);
            case 5:
                restaurantDto.setLatitude("");
                restaurantDto.setLongitude("");
                responsesReturn.setResponses("Ingrese una imagen del restaurante");
                responsesReturn.setStep(6);
            case 6:
                restaurantDto.setImages("");
                responsesReturn.setResponses("Sus datos son los siguientes: ");
                responsesReturn.setStep(7);

        }

        return responsesReturn;
    }
}


