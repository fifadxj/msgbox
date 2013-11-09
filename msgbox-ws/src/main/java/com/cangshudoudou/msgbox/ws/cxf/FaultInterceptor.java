package com.cangshudoudou.msgbox.ws.cxf;


import java.io.OutputStream;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cangshudoudou.msgbox.MsgboxException;
import com.cangshudoudou.msgbox.utils.ErrorCodeConstants;
import com.cangshudoudou.msgbox.ws.utils.WsConstants;
import com.cangshudoudou.msgbox.ws.utils.WsUtils;

public class FaultInterceptor extends AbstractPhaseInterceptor<Message> {
    private static Logger exLogger = LoggerFactory.getLogger(WsConstants.LOGGER_WS_EXCEPTION);
	private static Logger messageLogger = LoggerFactory.getLogger(WsConstants.LOGGER_WS_MESSAGE);
	
    public FaultInterceptor() {
        super(Phase.PRE_LOGICAL);
    }

    public void handleMessage(Message message) throws Fault {
        Fault fault = (Fault) message.getExchange().get(Exception.class);
        OutputStream os = null;
        String errorCode = null;
        try {
            if (fault != null) {
                Throwable t = fault.getCause();
                
                if (t != null) {
                    if (t instanceof MsgboxException) {
                        MsgboxException me = (MsgboxException) t;
                        exLogger.error(me.getCode());
                        errorCode = me.getCode();
                    } else {
                        exLogger.error(fault.getMessage(), fault);
                        errorCode = ErrorCodeConstants.ERR_UNKNOW_EXCEPTION;
                    }
                } else {
                    exLogger.error(fault.getMessage(), fault);
                    errorCode = ErrorCodeConstants.ERR_UNKNOW_EXCEPTION;
                }
                
                HttpServletRequest httpRequest = (HttpServletRequest) message.getExchange().getInMessage().get(AbstractHTTPDestination.HTTP_REQUEST);
                
                HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
                        .get(AbstractHTTPDestination.HTTP_RESPONSE);
                
                
                response.setContentType(MediaType.APPLICATION_JSON);

                MessageFormat mf = new MessageFormat("\"code\":\"{0}\",\"message\":\"{1}\"");
                String output;

                
                output = "{\"error\":{" + mf.format(new Object[] { errorCode, errorCode }) + "}}";
                
                String path = (String) message.getExchange().getInMessage().get(Message.PATH_INFO);
                messageLogger.info("{} - Response - {}" ,new Object[]{path, output});
                
                os = response.getOutputStream();
                os.write(output.getBytes());
                os.flush();
            }
        } catch (Exception e) {
            exLogger.error(e.getMessage(), e);
        } finally {
            message.getInterceptorChain().abort();

            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                exLogger.error(e.getMessage(), e);
            }
        }
    }
}
