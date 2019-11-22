package com.restaurant.bot.dao;

import com.restaurant.bot.domain.RUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpUSerRepository extends JpaRepository<RUser,Integer> {

    RUser findByBotUserId(String botUserId);

}
