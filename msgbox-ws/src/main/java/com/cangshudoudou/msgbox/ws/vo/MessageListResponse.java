package com.cangshudoudou.msgbox.ws.vo;


import java.util.List;

import com.cangshudoudou.msgbox.vo.Message;

public class MessageListResponse extends BaseResponse {
	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
