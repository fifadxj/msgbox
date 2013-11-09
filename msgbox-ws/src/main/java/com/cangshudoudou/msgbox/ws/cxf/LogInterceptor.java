package com.cangshudoudou.msgbox.ws.cxf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cangshudoudou.msgbox.ws.utils.WsConstants;

public class LogInterceptor extends AbstractPhaseInterceptor {

	private static Logger logger = LoggerFactory.getLogger(WsConstants.LOGGER_WS_MESSAGE);
	private static Logger exLogger = LoggerFactory.getLogger(WsConstants.LOGGER_WS_EXCEPTION);
    
    public LogInterceptor() {
        super(Phase.POST_STREAM);
    }

    public void handleMessage(Message message) throws Fault{
        try {
            if (MessageUtils.isOutbound(message)) {
                
            	HttpServletResponse response111 = (HttpServletResponse) message.getExchange().getInMessage()
                        .get(AbstractHTTPDestination.HTTP_RESPONSE);
            	
                CacheAndWriteOutputStream out = (CacheAndWriteOutputStream) message.getContent(OutputStream.class);
                String response = IOUtils.toString(out.getInputStream(), "UTF-8");
                
                String path = (String) message.getExchange().getInMessage().get(Message.PATH_INFO);
                logger.info("{} - Response - {}" ,new Object[]{path, response});
            } 
            else {
                InputStream in = message.getContent(InputStream.class);
                String request = IOUtils.toString(in, "UTF-8");
                InputStream newIn = new ByteArrayInputStream(request.getBytes("UTF-8"));
                message.setContent(InputStream.class, newIn);
                request = request.replaceAll("[\\s]*", "");
                
                String path = (String) message.get(Message.PATH_INFO);
                logger.info("{} - Request - {}" ,new Object[]{path, request});
            }
        } catch (Exception e) {
        	exLogger.warn("Cannot log message", e);
            if(e instanceof Fault){
                throw (Fault)e;
            }
        } finally {

        }
    }

}
