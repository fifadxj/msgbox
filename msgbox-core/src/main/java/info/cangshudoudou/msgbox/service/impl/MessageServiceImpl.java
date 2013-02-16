package info.cangshudoudou.msgbox.service.impl;

import info.cangshudoudou.msgbox.BusinessException;
import info.cangshudoudou.msgbox.dao.CategoryDao;
import info.cangshudoudou.msgbox.dao.MessageDao;
import info.cangshudoudou.msgbox.service.MessageService;
import info.cangshudoudou.msgbox.utils.ErrorCodeConstants;
import info.cangshudoudou.msgbox.vo.Category;
import info.cangshudoudou.msgbox.vo.Message;
import info.cangshudoudou.msgbox.vo.MessageFilterCondition;
import info.cangshudoudou.msgbox.vo.Pagination;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao;
    
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Message createMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException();
        }
        
        if (! StringUtils.hasText(message.getContent())) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_CONTENT_REQUIRED);
        }
        
        if (StringUtils.hasText(message.getSource()) && message.getSource().length() > 50) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_SOURCE_TOO_LONG);
        }
        
        if (message.getRank() == null || message.getRank() < 1 || message.getRank() > 5) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_RANK_OUT_OF_RANGE);
        }
        
        if (message.getCategory() == null || message.getCategory().getId() == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_CATEGORY_REQUIRED);
        }
        
        Category category = categoryDao.getCategory(message.getCategory().getId());
        if (category == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NOT_EXISTED);
        }
        
        messageDao.createMessage(message);
        
        return message;
    }

    @Override
    public void deleteMessage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Message message = messageDao.getMessage(id);
        if (message == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_NOT_EXISTED);
        }
        
        messageDao.deleteMessage(id);
    }

    @Override
    public Message updateMessage(Message message) {
        if (message == null || message.getId() == null) {
            throw new IllegalArgumentException();
        }
        
        if (! StringUtils.hasText(message.getContent())) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_CONTENT_REQUIRED);
        }
        
        if (StringUtils.hasText(message.getSource()) && message.getSource().length() > 50) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_SOURCE_TOO_LONG);
        }
        
        if (message.getRank() == null || message.getRank() < 0 || message.getRank() > 5) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_RANK_OUT_OF_RANGE);
        }
        
        if (message.getCategory() == null || message.getCategory().getId() == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_CATEGORY_REQUIRED);
        }
        
        Message indb = messageDao.getMessage(message.getId());
        
        if (indb == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_NOT_EXISTED);
        }
        if (indb.isDisabled()) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_ALREADY_DISABLED);
        }
        
        indb.setCategory(message.getCategory());
        indb.setContent(message.getContent());
        indb.setRank(message.getRank());
        indb.setSource(message.getSource());

        messageDao.updateMessage(indb);
        
        
        return indb;
    }

    @Override
    public Message getMessage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Message message = messageDao.getMessage(id);
        
        return message;
    }

    @Override
    public void topMessage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Message message = messageDao.getMessage(id);

        if (message == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_NOT_EXISTED);
        }
        
        if (message.isDisabled()) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_ALREADY_DISABLED);
        }
        
        message.setTop(true);
        messageDao.updateMessage(message);
    }

    @Override
    public void untopMessage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Message message = messageDao.getMessage(id);
        
        if (message == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_NOT_EXISTED);
        }
        
        if (message.isDisabled()) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_ALREADY_DISABLED);
        }
        
        message.setTop(false);
        messageDao.updateMessage(message);
        
    }

    @Override
    public void disableMessage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Message message = messageDao.getMessage(id);
        if (message == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_NOT_EXISTED);
        }
        
        if (message.isDisabled()) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_ALREADY_DISABLED);
        }
        
        message.setDisabled(true);
        messageDao.updateMessage(message);
    }

    @Override
    public void enableMessage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Message message = messageDao.getMessage(id);
        if (message == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_NOT_EXISTED);
        }
        
        if (! message.isDisabled()) {
            throw new BusinessException(ErrorCodeConstants.ERR_MESSAGE_ALREADY_ENABLED);
        }
        
        message.setDisabled(false);
        messageDao.updateMessage(message);
    }

    @Override
    public List<Message> getMessagesByCategoryId(Long categoryId, Pagination pagination) {
        List<Message> messages = messageDao.getMessagesByCategoryId(categoryId, null);
        
        return messages;
    }

    @Override
    public Long getMessageCountByCategoryId(Long categoryId) {
        return messageDao.getMessageCountByCategoryId(categoryId);
    }

    @Override
    public List<Message> listMessages(MessageFilterCondition condition, Pagination pagination) {
    	if (condition == null) {
    		throw new IllegalArgumentException();
    	}
        List<Message> messages = messageDao.listMessages(condition, pagination);
        
        return messages;
    }

}
