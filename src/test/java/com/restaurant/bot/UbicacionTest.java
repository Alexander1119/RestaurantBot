package com.restaurant.bot;

import com.restaurant.bot.bl.Ubicacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbicacionTest {

    @Test
    public void distancia(){
        final boolean expected = true;

        final boolean actual = Ubicacion.distance(-16.497129,-68.128690, -16.523160, -68.112129);
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }
}