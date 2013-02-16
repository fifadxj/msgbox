package info.cangshudoudou.msgbox.ws.endpoint;

import info.cangshudoudou.msgbox.ws.vo.MessageListRequest;
import info.cangshudoudou.msgbox.ws.vo.MessageListResponse;

public interface MessageEndpoint {
	MessageListResponse getMessageList(MessageListRequest request);
}
