package com.restaurant.bot;

import com.restaurant.bot.bl.Ubicacion;
import com.restaurant.bot.dao.CpRestaurantRepository;
import com.restaurant.bot.domain.Restaurant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbicacionTest {

    @Test
    public void distancia(){
        //Simulando Telegram
        User user = Mockito.mock(User.class);
        Mockito.doReturn(1234).when(user).getId();
        Mockito.doReturn("Carlos").when(user).getFirstName();
        Mockito.doReturn("Brans").when(user).getLastName();

        Message message = Mockito.mock(Message.class);
        Mockito.doReturn(user).when(message).getFrom();
        Mockito.doReturn("-16.497129").when(message).getLocation().getLatitude();
        Mockito.doReturn("-68.128690").when(message).getLocation().getLongitude();
        Mockito.doReturn(123456L).when(message).getChatId();

        Update update = Mockito.mock(Update.class);
        Mockito.doReturn(message).when(update).getMessage();

        // Simulando aceso a base de datos CpResturantRepository
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Mockito.doReturn("-16.523160").when(restaurant).getLatitude();
        Mockito.doReturn("-68.112129").when(restaurant).getLongitude();
        CpRestaurantRepository cpRestaurantRepository = Mockito.mock(CpRestaurantRepository.class);
        Mockito.doReturn(restaurant).when(cpRestaurantRepository).findByRestaurantId(1234);

        //Prueba Unitaria
        final String expected = "true";
        BigDecimal bd = null;
        double latp = Double.parseDouble(String.valueOf(update.getMessage().getLocation().getLatitude()));
        double lonp = Double.parseDouble(String.valueOf(update.getMessage().getLocation().getLongitude()));
        double latg = restaurant.getLatitude().doubleValue();
        double lon = restaurant.getLatitude().doubleValue();
        //final String actual = Ubicacion.distance(-16.497129,-68.128690, -16.523160, -68.112129);
        final String actual = Ubicacion.distance(latp,lonp,latg,lon);
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }
}