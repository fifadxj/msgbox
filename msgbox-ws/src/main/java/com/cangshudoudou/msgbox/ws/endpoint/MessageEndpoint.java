package com.cangshudoudou.msgbox.ws.endpoint;

import com.cangshudoudou.msgbox.ws.vo.MessageListRequest;
import com.cangshudoudou.msgbox.ws.vo.MessageListResponse;

public interface MessageEndpoint {
	MessageListResponse getMessageList(MessageListRequest request);
}
