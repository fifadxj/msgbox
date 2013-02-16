package info.cangshudoudou.msgbox.springmvc;

import info.cangshudoudou.msgbox.utils.SessionData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private SessionData sessionData;

    private List<String> secureUrls;
    
    private Boolean requireLogin;

    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    public void setSecureUrls(List<String> secureUrls) {
        this.secureUrls = secureUrls;
    }

	public void setRequireLogin(Boolean requireLogin) {
		this.requireLogin = requireLogin;
	}

    private String getTargetUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        String path = request.getRequestURL().toString();

        String queryString = request.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            path = path + "?" + queryString;
        }
        
        return path;
    }
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        if (! requireLogin || secureUrls.contains(lookupPath)) {
            return true;
        }

        if (sessionData.getAuthenticated() == null) {
            response.sendRedirect(request.getContextPath() + "/web/security/loginForm.html?goto=" + URLEncoder.encode(getTargetUrl(request), "UTF-8"));
            return false;
        } else {
            return true;
        }
    }

    /**
     * This implementation is empty.
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * This implementation is empty.
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
    }
}
