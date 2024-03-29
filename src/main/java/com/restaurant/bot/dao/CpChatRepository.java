package com.restaurant.bot.dao;

import com.restaurant.bot.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CpChatRepository extends JpaRepository<Chat,Integer> {

   @Query(value = "select * from chat where user_id=?1 order by chat_id desc LIMIT 1",nativeQuery = true)
    public Chat findLastChatByUserId(Integer user_id);

   @Query(value = "select * from chat where user_id=?1 and conversation_id=?2 and message_id=?3",nativeQuery = true)
    public Chat findMessageAndConversationByUserId(Integer user_id,Integer conversation,Integer message);
   /*
   @Query(value="update chat set message_id=? where user_id=?1 and chat_id=?3",nativeQuery = true)
    public void updateMessageId(Integer user_id,int num,Integer id_chat);

   @Query(value = "select * from chat where conversation_id=?2 and message_id=?1",nativeQuery = true)
    public Chat numberConversation(int message_id,int conversation_id);*/
}
