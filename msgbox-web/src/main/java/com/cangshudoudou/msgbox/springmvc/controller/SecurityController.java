package com.cangshudoudou.msgbox.springmvc.controller;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.cangshudoudou.msgbox.BusinessException;
import com.cangshudoudou.msgbox.service.SecurityService;
import com.cangshudoudou.msgbox.springmvc.MsgboxSessionLocaleResolver;
import com.cangshudoudou.msgbox.utils.SessionData;
import com.cangshudoudou.msgbox.vo.User;

public class SecurityController extends BaseMsgboxController {
    private SessionData sessionData;
    private SecurityService securityService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

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

        try {
            User resultUser = securityService.authenticateUser(user);

            sessionData.setAuthenticated(resultUser);
            String gt = request.getParameter("goto");
            if (StringUtils.hasText(gt)) {
                mav.setViewName("redirect:" + gt);
            } else {
                mav.setViewName("redirect:/web/home.html");
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
        } catch (BusinessException be) {
            mav.setViewName("/security/login");
            mav.addObject("errorcode", be.getCode());
            mav.addObject("user", user);
        }

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
