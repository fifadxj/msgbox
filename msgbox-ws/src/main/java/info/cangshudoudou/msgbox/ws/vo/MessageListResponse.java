package info.cangshudoudou.msgbox.ws.vo;

import info.cangshudoudou.msgbox.vo.Message;

import java.util.List;

public class MessageListResponse extends BaseResponse {
	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
