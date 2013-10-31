package com.cangshudoudou.msgbox.ws.endpoint.impl;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cangshudoudou.msgbox.MsgboxException;
import com.cangshudoudou.msgbox.service.MessageService;
import com.cangshudoudou.msgbox.vo.Message;
import com.cangshudoudou.msgbox.vo.MessageFilterCondition;
import com.cangshudoudou.msgbox.ws.endpoint.MessageEndpoint;
import com.cangshudoudou.msgbox.ws.utils.WsUtils;
import com.cangshudoudou.msgbox.ws.vo.MessageListRequest;
import com.cangshudoudou.msgbox.ws.vo.MessageListResponse;

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
