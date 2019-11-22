package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpClientRepository extends JpaRepository<Client,Integer> {

    Client findByBotUserId(String botUserId);

}
