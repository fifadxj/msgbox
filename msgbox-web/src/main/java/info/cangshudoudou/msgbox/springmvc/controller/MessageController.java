package info.cangshudoudou.msgbox.springmvc.controller;

import info.cangshudoudou.msgbox.BusinessException;
import info.cangshudoudou.msgbox.utils.CommonUtils;
import info.cangshudoudou.msgbox.vo.Category;
import info.cangshudoudou.msgbox.vo.Message;
import info.cangshudoudou.msgbox.vo.MessageFilterCondition;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ListUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

public class MessageController extends BaseMsgboxController {

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, MessageFilterCondition condition)
            throws Exception {

        ModelAndView result = new ModelAndView("/message/list");

        if (condition.getDisabled() == null) {
        	condition.setDisabled(false);
        }
        result.addObject("condition", condition);
        
        List<Category> categories = categoryService.listCategories();
        result.addObject("categories", categories);
        
        List<Message> messages = messageService.listMessages(condition, null);
        List<Message> toped = new ArrayList<Message>();
        List<Message> untoped = new ArrayList<Message>();
        for (Message message : messages) {
            if (message.isTop()) {
                toped.add(message);
            }
            else {
                untoped.add(message);
            }
        }
        result.addObject("topedMessages", toped);
        result.addObject("untopedMessages", untoped);
        
        
        return result;
    }

    public ModelAndView view(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        Message message = messageService.getMessage(Long.valueOf(id));

        ModelAndView result = new ModelAndView("/message/view");
        result.addObject("message", message);
        
        return result;
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        messageService.deleteMessage(Long.valueOf(id));

        String referer = CommonUtils.getRefererPage(request);
        ModelAndView mav = new ModelAndView("redirect:" + referer);

        return mav;
    }

    public ModelAndView createForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("/message/create");
        
        List<Category> categories = categoryService.listCategories();
        result.addObject("categories", categories);
        result.addObject("rankRanges", CommonUtils.getRankRanges());

        Message message = new Message();
        message.setCategory(new Category());
        result.addObject("command", message);
        
        return result;
    }
    
    public ModelAndView createSubmit(HttpServletRequest request, HttpServletResponse response,
            Message command) throws Exception {
        ModelAndView result = new ModelAndView();
        
        try {
            Message message = messageService.createMessage(command);
            result.setViewName("redirect:/web/message/view.html?id=" + message.getId());
        } catch (BusinessException be) {
            result.addObject("error", be.getCode() + ": " + be.getMessage());
            result.setViewName("/message/create");
            result.addObject("command", command);
            
            List<Category> categories = categoryService.listCategories();
            result.addObject("categories", categories);
            result.addObject("rankRanges", CommonUtils.getRankRanges());
        }
           
        return result;
    }
    
    public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("/message/edit");

        String id = request.getParameter("id");
        Message message = messageService.getMessage(Long.valueOf(id));
        result.addObject("command", message);
        
        List<Category> categories = categoryService.listCategories();
        result.addObject("categories", categories);
        result.addObject("rankRanges", CommonUtils.getRankRanges());
        
        return result;
    }
    
    public ModelAndView editSubmit(HttpServletRequest request, HttpServletResponse response,
            Message command) throws Exception {
        ModelAndView result = new ModelAndView();
        
        try {
            Message message = messageService.updateMessage(command);
            
            result.setViewName("redirect:/web/message/view.html?id=" + message.getId());
        } catch (BusinessException be) {
            result.setViewName("/message/edit");
            
            command = messageService.getMessage(command.getId());
            result.addObject("command", command);
            result.addObject("error", be.getCode() + ": " + be.getMessage());
           
            List<Category> categories = categoryService.listCategories();
            result.addObject("categories", categories);
            result.addObject("rankRanges", CommonUtils.getRankRanges());

        }
        
        return result;
    }
    
    public ModelAndView top(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        messageService.topMessage(Long.valueOf(id));

        String referer = CommonUtils.getRefererPage(request);
        ModelAndView mav = new ModelAndView("redirect:" + referer);

        return mav;
    }
    
    public ModelAndView untop(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        messageService.untopMessage(Long.valueOf(id));

        String referer = CommonUtils.getRefererPage(request);
        ModelAndView mav = new ModelAndView("redirect:" + referer);

        return mav;
    }
    
    public ModelAndView enable(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        messageService.enableMessage(Long.valueOf(id));

        String referer = CommonUtils.getRefererPage(request);
        ModelAndView mav = new ModelAndView("redirect:" + referer);

        return mav;
    }

    public ModelAndView disable(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        messageService.disableMessage(Long.valueOf(id));

        String referer = CommonUtils.getRefererPage(request);
        ModelAndView mav = new ModelAndView("redirect:" + referer);

        return mav;
    }
}
