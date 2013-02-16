package info.cangshudoudou.msgbox.ws.cxf;

import info.cangshudoudou.msgbox.MsgboxException;
import info.cangshudoudou.msgbox.utils.ErrorCodeConstants;
import info.cangshudoudou.msgbox.ws.utils.WsUtils;

import java.io.OutputStream;
import java.text.MessageFormat;

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

public class FaultInterceptor extends AbstractPhaseInterceptor<Message> {
    public FaultInterceptor() {
        super(Phase.PRE_LOGICAL);
    }

    public void handleMessage(Message message) throws Fault {
        Fault fault = (Fault) message.getExchange().get(Exception.class);
        OutputStream os = null;
        try {
            if (fault != null) {
                Throwable t = fault.getCause();
                if (t instanceof MsgboxException) {
                    fault = WsUtils.toFault(t);
                }
                HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
                        .get(AbstractHTTPDestination.HTTP_RESPONSE);
                response.setContentType(MediaType.APPLICATION_JSON);

                MessageFormat mf = new MessageFormat("\"code\":\"{0}\",\"message\":\"{1}\"");
                String output;

                String errorCode = !StringUtils.hasText(fault.getCode()) ? ErrorCodeConstants.ERR_UNKNOW_EXCEPTION : fault.getCode();
                output = "{\"error\":{" + mf.format(new Object[] { errorCode, errorCode }) + "}}";

                os = response.getOutputStream();
                os.write(output.getBytes());
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            message.getInterceptorChain().abort();

            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
