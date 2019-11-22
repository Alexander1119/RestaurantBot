package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpRestaurantRepository extends JpaRepository<Restaurant,Integer> {

}
