package com.restaurant.bot.bl;

import com.restaurant.bot.dao.CpUSerRepository;
import com.restaurant.bot.dao.CpPersonRepository;
import com.restaurant.bot.dao.CpRestaurantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotBl {


    private static final Logger LOGGER= LoggerFactory.getLogger(BotBl.class);

    private CpUSerRepository cpUSerRepository;
    private CpPersonRepository cpPersonRepository;
    private CpRestaurantRepository cpRestaurantRepository;

    @Autowired
    public BotBl(CpUSerRepository cpUSerRepository, CpPersonRepository cpPersonRepository, CpRestaurantRepository cpRestaurantRepository) {
        this.cpUSerRepository = cpUSerRepository;
        this.cpPersonRepository = cpPersonRepository;
        this.cpRestaurantRepository = cpRestaurantRepository;
    }

    public List<String> processUpdate(Update update){

        LOGGER.info("Recibiendo update {}",update);
        List<String> chatResponses=new ArrayList<>();
        return null;

    }


}