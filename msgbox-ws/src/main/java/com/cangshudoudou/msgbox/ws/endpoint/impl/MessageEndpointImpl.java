package com.cangshudoudou.msgbox.ws.endpoint.impl;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cangshudoudou.msgbox.service.MessageService;
import com.cangshudoudou.msgbox.utils.JedisHandler;
import com.cangshudoudou.msgbox.vo.Message;
import com.cangshudoudou.msgbox.vo.MessageFilterCondition;
import com.cangshudoudou.msgbox.ws.endpoint.BaseEndpoint;
import com.cangshudoudou.msgbox.ws.endpoint.MessageEndpoint;
import com.cangshudoudou.msgbox.ws.vo.MessageDetailsRequest;
import com.cangshudoudou.msgbox.ws.vo.MessageDetailsResponse;
import com.cangshudoudou.msgbox.ws.vo.MessageListRequest;
import com.cangshudoudou.msgbox.ws.vo.MessageListResponse;

@Path("/message")
public class MessageEndpointImpl extends BaseEndpoint implements MessageEndpoint {

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view")
    public MessageDetailsResponse getMessage(MessageDetailsRequest request) {
        
        MessageDetailsResponse response = new MessageDetailsResponse();
        
        Message result = new Message();
        JedisHandler jedisHandler = JedisHandler.getInstance();
        Boolean exist = jedisHandler.hexist("message:" + request.getId().toString(), "id");
        //System.out.println("exist: " + exist);
        if (! exist) {
        	Message message = messageService.getMessage(request.getId());
        	
        	jedisHandler.hset("message:" + message.getId(), "id", message.getId().toString());
        	jedisHandler.hset("message:" + message.getId(), "title", message.getTitle());
        	jedisHandler.hset("message:" + message.getId(), "content", message.getContent());
        	jedisHandler.hset("message:" + message.getId(), "rank", message.getRank().toString());
        	jedisHandler.hset("message:" + message.getId(), "source", message.getSource());

        	result.setId(message.getId());
        	result.setTitle(message.getTitle());
        	result.setContent(message.getContent());
        	result.setRank(message.getRank());
        	result.setSource(message.getSource());
        }
        else {
        	String title = jedisHandler.hget("message:" + request.getId(), "title");
        	String content = jedisHandler.hget("message:" + request.getId(), "content");
        	Integer rank = Integer.valueOf(jedisHandler.hget("message:" + request.getId(), "rank"));
        	String source = jedisHandler.hget("message:" + request.getId(), "source");

        	result.setId(request.getId());
        	result.setTitle(title);
        	result.setContent(content);
        	result.setRank(rank);
        	result.setSource(source);
        }
        
        response.setMessage(result);
        
        return response;
    }
    
    private void cache(Message message) {
        JedisHandler jedisHandler = JedisHandler.getInstance();
        String rank = jedisHandler.get(message.getId().toString());
        if (rank == null) {
        	rank = message.getRank().toString();
        }
        else {
        	rank = String.valueOf(Integer.parseInt(rank) + 1);
        }
        jedisHandler.set(message.getId().toString(), rank);
        message.setRank(Integer.parseInt(rank));
    }

}
