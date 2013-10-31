package com.cangshudoudou.msgbox.ws.vo;

import com.cangshudoudou.msgbox.vo.User;

public class LoginResponse extends BaseResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
