package com.cangshudoudou.msgbox.ws.utils;


import javax.xml.namespace.QName;

import org.apache.cxf.common.i18n.Message;
import org.apache.cxf.interceptor.Fault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cangshudoudou.msgbox.MsgboxException;
import com.cangshudoudou.msgbox.utils.ErrorCodeConstants;

public class WsUtils {
    
    public static Fault toFault(Throwable t) throws Fault {
        
        Fault fault = null;

        if( t instanceof Fault ) {
            fault = (Fault)t;
        } else {
            fault = new Fault(t);
        }

        return fault;
    }
}
