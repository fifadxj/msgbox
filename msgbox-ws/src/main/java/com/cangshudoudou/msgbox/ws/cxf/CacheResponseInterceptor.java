package com.cangshudoudou.msgbox.ws.cxf;

import java.io.OutputStream;

import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cangshudoudou.msgbox.ws.utils.WsConstants;

public class CacheResponseInterceptor extends AbstractPhaseInterceptor {

	private static Logger exLogger = LoggerFactory.getLogger(WsConstants.LOGGER_WS_EXCEPTION);

    public CacheResponseInterceptor() {
        super(Phase.PRE_STREAM);
    }

    public void handleMessage(Message message) {
        try {
            OutputStream os = message.getContent(OutputStream.class);
            CacheAndWriteOutputStream out = new CacheAndWriteOutputStream(os);
            message.setContent(OutputStream.class, out);
        } catch (Exception e) {
        	exLogger.warn(e.getMessage(), e);
        }
    }
}
