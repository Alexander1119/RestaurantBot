package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Person;
import com.restaurant.bot.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

public interface CpRestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Query(value = "select * from restaurant where person_id=?1 ",nativeQuery = true)
    public Restaurant findPersonId(Integer person_id);

    @Query(value = "Select * from restaurant where restaurant_id=1", nativeQuery = true)
    public Restaurant findAllBy();

    @Query(value = "select * from restaurant where time1?1 between opening_time and closing_time",nativeQuery = true)
    public List<Restaurant> findByRestaurantBetweenHora(Time time1);


    List <Restaurant> findByRestaurantId (int restaurantid);
}
