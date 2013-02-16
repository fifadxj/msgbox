package info.cangshudoudou.msgbox.ws.utils;

import info.cangshudoudou.msgbox.MsgboxException;
import info.cangshudoudou.msgbox.utils.ErrorCodeConstants;

import javax.xml.namespace.QName;

import org.apache.cxf.common.i18n.Message;
import org.apache.cxf.interceptor.Fault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class WsUtils {
    private static Logger logger = LoggerFactory.getLogger(WsUtils.class);
    public static Fault toFault(Throwable t) throws Fault {
        logger.error(t.getMessage(), t);
        Fault fault = null;

        if( t instanceof Fault ) {
            fault = (Fault)t;
        } else if( t instanceof MsgboxException ) {
            MsgboxException be = (MsgboxException)t;
             if(StringUtils.hasText(be.getCode()) ) {
                 fault = new Fault( new Message(be.getCode(), java.util.logging.Logger.getAnonymousLogger()), new QName(be.getCode()));
                 fault.setMessage(be.getMessage());
             } else {
                 fault = new Fault( new Message(ErrorCodeConstants.ERR_UNKNOW_EXCEPTION, java.util.logging.Logger.getAnonymousLogger()), new QName(ErrorCodeConstants.ERR_UNKNOW_EXCEPTION));
             }
        } else {
           fault = new Fault( new Message(ErrorCodeConstants.ERR_UNKNOW_EXCEPTION, java.util.logging.Logger.getAnonymousLogger()), new QName(ErrorCodeConstants.ERR_UNKNOW_EXCEPTION));
        }

        return fault;
    }
}
