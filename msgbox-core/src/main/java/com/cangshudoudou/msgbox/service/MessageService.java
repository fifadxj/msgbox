package com.cangshudoudou.msgbox.service;


import java.util.List;

import com.cangshudoudou.msgbox.vo.Message;
import com.cangshudoudou.msgbox.vo.MessageFilterCondition;
import com.cangshudoudou.msgbox.vo.Pagination;

public interface MessageService {
    Message createMessage(Message message);
    void deleteMessage(Long id);
    Message updateMessage(Message message);
    Message getMessage(Long id);
    List<Message> getMessagesByCategoryId(Long categoryId, Pagination pagination);
    Long getMessageCountByCategoryId(Long categoryId);
    List<Message> listMessages(MessageFilterCondition condition, Pagination pagination);
    void topMessage(Long id);
    void untopMessage(Long id);
    void disableMessage(Long id);
    void enableMessage(Long id);
}
