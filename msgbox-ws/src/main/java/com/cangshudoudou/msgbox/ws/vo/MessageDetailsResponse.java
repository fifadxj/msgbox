package com.cangshudoudou.msgbox.ws.vo;

import com.cangshudoudou.msgbox.vo.Message;

public class MessageDetailsResponse extends BaseResponse {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
