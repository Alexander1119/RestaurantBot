package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CpChatRepository extends JpaRepository<Chat,Integer> {

 //  @Query(value = "select * from chat where ")
}
