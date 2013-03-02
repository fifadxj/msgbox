package com.cangshudoudou.msgbox.dao;


import java.util.List;

import com.cangshudoudou.msgbox.vo.Message;
import com.cangshudoudou.msgbox.vo.MessageFilterCondition;
import com.cangshudoudou.msgbox.vo.Pagination;

public interface MessageDao {
    Long createMessage(Message message);
    void deleteMessage(Long id);
    void updateMessage(Message message);
    Message getMessage(Long id);
    List<Message> listMessages(MessageFilterCondition condition, Pagination pagination);
    List<Message> getMessagesByCategoryId(Long categoryId, Pagination pagination);
    Long getMessageCountByCategoryId(Long categoryId);
    List<Message> searchMessagesByKeyword(String key, Pagination pagination);
}
