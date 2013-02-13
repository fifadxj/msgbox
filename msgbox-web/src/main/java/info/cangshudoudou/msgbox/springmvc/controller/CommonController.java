package info.cangshudoudou.msgbox.springmvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class CommonController extends BaseMsgboxController {

    private SessionLocaleResolver localeResolver;

    public void setLocaleResolver(SessionLocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    public ModelAndView language(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String language = request.getParameter("language");
        
        Locale locale = null;
        if ("zh".equalsIgnoreCase(language)) {
            locale = Locale.CHINESE;
        }
        else {
            locale = Locale.ENGLISH;
        }
        localeResolver.setLocale(request, response, locale);
        
        String referer = request.getParameter("referer");
        if (referer == null) {
            referer = request.getHeader("Referer");
        }
        ModelAndView result = new ModelAndView("redirect:" + referer);

        return result;
    }
}
