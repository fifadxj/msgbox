package com.cangshudoudou.msgbox.ws.vo;

import com.cangshudoudou.msgbox.vo.MessageFilterCondition;

public class MessageListRequest extends BaseRequest {
	private MessageFilterCondition condition;

	public MessageFilterCondition getCondition() {
		return condition;
	}

	public void setCondition(MessageFilterCondition condition) {
		this.condition = condition;
	}
}
