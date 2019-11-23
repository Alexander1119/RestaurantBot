package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Cpuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpUSerRepository extends JpaRepository<Cpuser,Integer> {


    Cpuser findByBotUserId(String botUserId);

}
