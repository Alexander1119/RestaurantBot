package com.restaurant.bot.dao;

import com.restaurant.bot.domain.FoodList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CpFoodListRepository extends JpaRepository<FoodList,Integer> {
    public List<FoodList> findBy();
}
