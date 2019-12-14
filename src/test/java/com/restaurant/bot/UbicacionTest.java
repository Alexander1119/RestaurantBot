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
        final Double expected = 2603.566315959424;

        final Double actual = Ubicacion.distance(-16.52326,-68.11182, -16.47311, -68.15140);
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }
}