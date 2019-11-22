package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpPersonRepository  extends JpaRepository<Person,Integer> {

}
