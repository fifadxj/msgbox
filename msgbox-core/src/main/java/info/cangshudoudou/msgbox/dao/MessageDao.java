package info.cangshudoudou.msgbox.dao;

import info.cangshudoudou.msgbox.vo.Message;
import info.cangshudoudou.msgbox.vo.MessageFilterCondition;
import info.cangshudoudou.msgbox.vo.Pagination;

import java.util.List;

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
