package com.cangshudoudou.msgbox.ws.cxf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public class TestInterceptor extends AbstractPhaseInterceptor {

    public TestInterceptor() {
        super(Phase.PRE_LOGICAL);
    }

    public void handleMessage(Message message) throws Fault{
    	HttpServletRequest httpRequest = (HttpServletRequest) message.getExchange().getInMessage().get(AbstractHTTPDestination.HTTP_REQUEST);
        
        HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
                .get(AbstractHTTPDestination.HTTP_RESPONSE);
        
        response.getHeader("Set-Cookie");
        
        String headers = "";
        for (String h : response.getHeaderNames()) {
        	headers = headers + h + ":  " + response.getHeader(h) + "    \n";
        }
        System.out.println(headers);
        
        int i = 1;
        i = i * i;
        
    }

}
