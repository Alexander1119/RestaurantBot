package com.restaurant.bot.bl;

import com.restaurant.bot.ResponsesReturn;
import com.restaurant.bot.dao.CpRestaurantRepository;
import com.restaurant.bot.dto.RestaurantDto;

public class RestaurantBl {
    CpRestaurantRepository cpRestaurantRepository;


    public RestaurantBl(CpRestaurantRepository cpRestaurantRepository) {
        this.cpRestaurantRepository = cpRestaurantRepository;
    }


    public ResponsesReturn restaurantResgister(int numberMessage, String lastMessage){
        int step=0;
        ResponsesReturn responsesReturn=new ResponsesReturn();
        RestaurantDto restaurantDto=new RestaurantDto();
        switch (step){
            case 1:

                responsesReturn.setResponses("Ingrese el nombre de su restaurante");
                responsesReturn.setStep(2);

            case 2:
                restaurantDto.setName(lastMessage);
                responsesReturn.setResponses("Ingrese la zona en la que se encuentra su restaruante");
                responsesReturn.setStep(3);
            case 3:
                restaurantDto.setZone(lastMessage);
                responsesReturn.setResponses("Ingrese la calle en la que se encuentra su restaruante");
                responsesReturn.setStep(4);
            case 4:
                restaurantDto.setStreet(lastMessage);
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
