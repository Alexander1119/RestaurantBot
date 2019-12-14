package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpTimeTableRepository extends JpaRepository<Timetable,Integer> {

}
