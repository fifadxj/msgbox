package com.cangshudoudou.msgbox.ws.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;

public class BaseEndpoint {

    @Context 
    private MessageContext context;
    
    public BaseEndpoint() {
        
    }
    
    protected HttpServletRequest getHttpservletRequest() {
        return context.getHttpServletRequest();
    }
    
    protected HttpServletResponse getHttpServletResponse() {
        return context.getHttpServletResponse();
    }

}
