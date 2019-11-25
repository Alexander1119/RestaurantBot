package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CpChatRepository extends JpaRepository<Chat,Integer> {

   @Query(value = "select * from chat where user_id=?1 order by chat_id desc LIMIT 1",nativeQuery = true)
    public Chat findLastChatByUserId(Integer user_id);
}
