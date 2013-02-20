package info.cangshudoudou.msgbox.ws.endpoint.impl;

import info.cangshudoudou.msgbox.MsgboxException;
import info.cangshudoudou.msgbox.service.MessageService;
import info.cangshudoudou.msgbox.vo.Message;
import info.cangshudoudou.msgbox.vo.MessageFilterCondition;
import info.cangshudoudou.msgbox.ws.endpoint.MessageEndpoint;
import info.cangshudoudou.msgbox.ws.utils.WsUtils;
import info.cangshudoudou.msgbox.ws.vo.MessageListRequest;
import info.cangshudoudou.msgbox.ws.vo.MessageListResponse;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/message")
public class MessageEndpointImpl implements MessageEndpoint {

    private MessageService messageService;

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public MessageListResponse getMessageList(MessageListRequest request) {
        MessageListResponse response = new MessageListResponse();
        
            if (request.getCondition() == null) {
                request.setCondition(new MessageFilterCondition());
            }
            List<Message> messages = messageService.listMessages(request.getCondition(), null);

            response.setMessages(messages);

        return response;
    }

}
