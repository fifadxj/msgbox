package com.cangshudoudou.msgbox.dao;

import java.util.List;

import com.cangshudoudou.msgbox.vo.User;

public interface UserDao {
    Long createUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    User getUser(Long id);
    User getUserByUsername(String username);
}
