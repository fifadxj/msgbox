package info.cangshudoudou.msgbox.dao.hibernate;

import info.cangshudoudou.msgbox.dao.MessageDao;
import info.cangshudoudou.msgbox.vo.Message;
import info.cangshudoudou.msgbox.vo.MessageFilterCondition;
import info.cangshudoudou.msgbox.vo.Pagination;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class MessageDaoImpl extends DaoSupport implements MessageDao {

    @Override
    public Long createMessage(Message message) {
        message.setCreationDate(new Date());
        message.setLastModifiedDate(new Date());
        getCurrentSession().save(message);
        
        return message.getId();
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = (Message) getCurrentSession().get(Message.class, id);
        getCurrentSession().delete(message);
    }

    @Override
    public Message getMessage(Long id) {
        Message message = (Message) getCurrentSession().get(Message.class, id);
        
        return message;
    }

    @Override
    public void updateMessage(Message message) {
        Message indb = getMessage(message.getId());
        message.setCreationDate(indb.getCreationDate());
        message.setLastModifiedDate(new Date());
        getCurrentSession().merge(message);
        
    }

    @Override
    public List<Message> listMessages(MessageFilterCondition condition, Pagination pagination) {
//        Query query = getCurrentSession().createQuery("from Message where disabled = :disabled");
//        query.setBoolean("disabled", true);
//        List<Message> messages = (List<Message>) query.list();
        
        Criteria criteria = getCurrentSession().createCriteria(Message.class, "m");
        if (condition.getDisabled() != null) {
            criteria.add(Restrictions.eq("disabled", condition.getDisabled()));
        }
        
        if (condition.getTop() != null) {
            criteria.add(Restrictions.eq("top", condition.getDisabled()));
        }
        
        if (condition.getCategoryId() != null && ! condition.getCategoryId().equals(-1L)) {
            criteria.createCriteria("category", "c").add(Restrictions.eqProperty("m.category.id", "c.id"));
            Criterion belongToParentCategory = Restrictions.eq("c.parent.id", condition.getCategoryId());
            Criterion belongToThisCategory = Restrictions.eq("m.category.id", condition.getCategoryId());
            criteria.add(Restrictions.or(belongToParentCategory, belongToThisCategory));
        }

        criteria.addOrder(Order.desc("rank"));
        List<Message>messages = criteria.list();
        
        return messages;
    }


    @Override
    public List<Message> getMessagesByCategoryId(Long categoryId, Pagination pagination) {
        Query query = getCurrentSession().createQuery("from Message where category.id = :categoryId");
        query.setLong("categoryId", categoryId);
        List<Message> messages = (List<Message>) query.list();
        
        return messages;
    }

    @Override
    public Long getMessageCountByCategoryId(Long categoryId) {
        Query query = getCurrentSession().createQuery("select count(*) from Message where category.id = :categoryId");
        query.setLong("categoryId", categoryId);
        Long count = (Long) query.uniqueResult();
        
        return count;
    }

    
    @Override
    public List<Message> searchMessagesByKeyword(String key, Pagination pagination) {
        // TODO Auto-generated method stub
        return null;
    }

}
