package com.cangshudoudou.msgbox.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cangshudoudou.msgbox.BusinessException;
import com.cangshudoudou.msgbox.dao.UserDao;
import com.cangshudoudou.msgbox.service.SecurityService;
import com.cangshudoudou.msgbox.utils.CommonUtils;
import com.cangshudoudou.msgbox.utils.ErrorCodeConstants;
import com.cangshudoudou.msgbox.vo.User;

public class SecurityServiceImpl implements SecurityService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional
    public User authenticateUser(User user) {
        User existingUser = userDao.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            String hashedPassword = CommonUtils.sha1Base64(user.getPassword());
            if (hashedPassword.equals(existingUser.getPassword())) {
                return existingUser;
            }
        }

        throw new BusinessException(ErrorCodeConstants.ERR_SECURITY_INVALID_CREDENTIAL);
    }

}
