package info.cangshudoudou.msgbox.springmvc;

import java.io.IOException;

import info.cangshudoudou.msgbox.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {
        ex.printStackTrace();
        String error;
        if (ex instanceof BusinessException) {
            error = ((BusinessException) ex).getCode() + " - " + ex.getMessage();
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("error", error);
            return mav;
        }
        else {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        
    }

}
