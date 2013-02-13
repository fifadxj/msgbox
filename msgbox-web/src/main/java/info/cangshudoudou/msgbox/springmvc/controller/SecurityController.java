package info.cangshudoudou.msgbox.springmvc.controller;

import java.util.Locale;

import info.cangshudoudou.msgbox.springmvc.MsgboxSessionLocaleResolver;
import info.cangshudoudou.msgbox.springmvc.SessionData;
import info.cangshudoudou.msgbox.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class SecurityController extends BaseMsgboxController {
    private SessionData sessionData;

    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    private MsgboxSessionLocaleResolver localeResolver;

    public void setLocaleResolver(MsgboxSessionLocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }
    
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("/security/login");
        User user = new User();
        user.setLanguage(localeResolver.getDefaultLocale().toString());
        mav.addObject("user", user);

        return mav;
    }

    public ModelAndView loginSubmit(HttpServletRequest request, HttpServletResponse response, User user)
            throws Exception {
        ModelAndView mav = new ModelAndView();

        String passcode = user.getPasscode();

        if (passcode.equals("terry")) {
            sessionData.setAuthenticated(new Object());
            String gt = request.getParameter("goto");
            if (StringUtils.hasText(gt)) {
                mav.setViewName("redirect:" + gt);
            } else {
                mav.setViewName("redirect:/web/home.html");
            }
        } else {
            mav.addObject("errorcode", "invalid.passcode");
            mav.addObject("user", user);
            mav.setViewName("/security/login");
        }

        String language = user.getLanguage();
        
        Locale locale = null;
        if ("zh".equalsIgnoreCase(language)) {
            locale = Locale.CHINESE;
        }
        else {
            locale = Locale.ENGLISH;
        }
        localeResolver.setLocale(request, response, locale);
        
        return mav;
    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("redirect:/web/home.html");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return mav;
    }
}
