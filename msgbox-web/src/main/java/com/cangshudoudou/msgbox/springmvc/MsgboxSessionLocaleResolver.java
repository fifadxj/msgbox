package com.cangshudoudou.msgbox.springmvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

public class MsgboxSessionLocaleResolver extends SessionLocaleResolver {
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale == null) {
            locale = determineDefaultLocale(request);
        }
        WebUtils.setSessionAttribute(request, "localeSuffix", locale.toString());
        return locale;
    }
    
    public Locale getDefaultLocale() {
        return super.getDefaultLocale();
    }
}
