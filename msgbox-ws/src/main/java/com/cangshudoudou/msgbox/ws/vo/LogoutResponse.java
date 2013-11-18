package com.cangshudoudou.msgbox.ws.vo;

import com.cangshudoudou.msgbox.vo.User;

public class LogoutResponse extends BaseResponse {
    private Long duration;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
