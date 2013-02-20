package info.cangshudoudou.msgbox.ws.cxf;

import info.cangshudoudou.msgbox.utils.ErrorCodeConstants;
import info.cangshudoudou.msgbox.utils.SessionData;
import info.cangshudoudou.msgbox.ws.WsException;
import info.cangshudoudou.msgbox.ws.utils.WsUtils;
import info.cangshudoudou.msgbox.ws.vo.BaseRequest;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

public class AuthenticationInterceptor extends AbstractPhaseInterceptor<Message> {

    private SessionData sessionData;

    private List<String> secureUrls;

    private Boolean requireLogin;

    public void setRequireLogin(Boolean requireLogin) {
        this.requireLogin = requireLogin;
    }
    
    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    public void setSecureUrls(List<String> secureUrls) {
        this.secureUrls = secureUrls;
    }

    public AuthenticationInterceptor() {
        super(Phase.PRE_LOGICAL);
    }

    public void handleMessage(Message message) throws Fault {
        try {
            List params = message.getContent(List.class);
            if (params == null) {
                throw new WsException(ErrorCodeConstants.ERR_INVALID_REQUEST_FORMAT);
            }
            BaseRequest baseRequest = (BaseRequest) params.get(0);

            if (baseRequest == null) {
                throw new WsException(ErrorCodeConstants.ERR_INVALID_REQUEST_FORMAT);
            }

            // String path = (String) message.get(Message.PATH_INFO);
            HttpServletRequest httpRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
            httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            UrlPathHelper urlPathHelper = new UrlPathHelper();
            String lookupPath = urlPathHelper.getLookupPathForRequest(httpRequest);
            if (! requireLogin || secureUrls.contains(lookupPath)) {
                return;
            }

            if (sessionData.getAuthenticated() == null) {
                throw WsUtils.toFault(new WsException(ErrorCodeConstants.ERR_USER_NOT_LOGIN));
            }
        } catch (Exception e) {
            throw WsUtils.toFault(e);
        }
    }

}
