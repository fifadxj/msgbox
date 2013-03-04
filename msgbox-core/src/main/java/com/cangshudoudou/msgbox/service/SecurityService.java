package com.cangshudoudou.msgbox.service;

import com.cangshudoudou.msgbox.vo.User;

public interface SecurityService {
    User authenticateUser(User user);
}
