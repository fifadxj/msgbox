package com.cangshudoudou.msgbox.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.cangshudoudou.msgbox.dao.UserDao;
import com.cangshudoudou.msgbox.vo.User;

public class UserDaoImpl extends DaoSupport implements UserDao {

    @Override
    public Long createUser(User user) {
        getCurrentSession().save(user);
        return user.getId();
    }

    @Override
    public void deleteUser(Long id) {
        User user = (User) getCurrentSession().get(User.class, id);
        getCurrentSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        getCurrentSession().merge(user);
    }

    @Override
    public User getUser(Long id) {
        User user = (User) getCurrentSession().get(User.class, id);
        
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username).ignoreCase());
        
        return (User) criteria.uniqueResult();
    }

}
