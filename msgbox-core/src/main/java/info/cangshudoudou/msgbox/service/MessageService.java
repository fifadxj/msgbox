package info.cangshudoudou.msgbox.service;

import info.cangshudoudou.msgbox.vo.Message;
import info.cangshudoudou.msgbox.vo.MessageFilterCondition;
import info.cangshudoudou.msgbox.vo.Pagination;

import java.util.List;

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
