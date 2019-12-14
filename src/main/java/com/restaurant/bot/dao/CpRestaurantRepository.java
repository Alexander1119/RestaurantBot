package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Person;
import com.restaurant.bot.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CpRestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Query(value = "select * from restaurant where person_id=?1 order by chat_id desc LIMIT 1",nativeQuery = true)
    public Person findPersonId(Integer person_id);
}
