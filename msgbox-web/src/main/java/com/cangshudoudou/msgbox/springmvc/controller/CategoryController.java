package com.cangshudoudou.msgbox.springmvc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.cangshudoudou.msgbox.BusinessException;
import com.cangshudoudou.msgbox.springmvc.utils.CommonUtils;
import com.cangshudoudou.msgbox.vo.Category;

public class CategoryController extends BaseMsgboxController {

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Category> categories = categoryService.listCategories();
        
        ModelAndView result = new ModelAndView("/category/list");
        result.addObject("categories", categories);
        
        return result;
    }

    public ModelAndView view(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        Category category = categoryService.getCategory(Long.valueOf(id));

        ModelAndView result = new ModelAndView("/category/view");
        result.addObject("category", category);
        
        return result;
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        categoryService.deleteCategory(Long.valueOf(id));

        String referer = CommonUtils.getRefererPage(request);
        ModelAndView mav = new ModelAndView("redirect:" + referer);

        return mav;
    }

    public ModelAndView createForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("/category/create");
        
        List<Category> categories = categoryService.listCategories();
        result.addObject("categories", categories);
        Category category = new Category();
        result.addObject("command", category);
        
        return result;
    }
    
    public ModelAndView createSubmit(HttpServletRequest request, HttpServletResponse response,
            Category command) throws Exception {
        ModelAndView result = new ModelAndView();
        
        if (StringUtils.hasLength(command.getParentId())) {
            Category parent = new Category();
            parent.setId(Long.valueOf(command.getParentId()));
            command.setParent(parent);
        } else {
            command.setParent(null);
        }
        
        try {
            categoryService.createCategory(command);
            result.setViewName("redirect:/web/category/view.html?id=" + command.getId());
        } catch (BusinessException be) {
            result.setViewName("/category/create");
            
            result.addObject("error", be.getCode());
            result.addObject("command", command);
            List<Category> categories = categoryService.listCategories();
            result.addObject("categories", categories);
        }
        
        
        return result;
    }
    
    public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView result = new ModelAndView("/category/edit");

        String id = request.getParameter("id");
        Category command = categoryService.getCategory(Long.valueOf(id));

        if (command.getParent() != null) {
            command.setParentId(command.getParent().getId().toString());
        }
        else {
            command.setParentId("");
        }
        
        result.addObject("command", command);
        result.addObject("categories", getParentCandidatesSortedByPinyin(command.getId()));
        
        return result;
    }
    
    public ModelAndView editSubmit(HttpServletRequest request, HttpServletResponse response,
            Category command) throws Exception {
        ModelAndView result = new ModelAndView();

        if (StringUtils.hasLength(command.getParentId())) {
            Category parent = new Category();
            parent.setId(Long.valueOf(command.getParentId()));
            command.setParent(parent);
        } else {
            command.setParent(null);
        }
        
        try {
            categoryService.updateCategory(command);
            
            result.setViewName("redirect:/web/category/view.html?id=" + command.getId());
        } catch (BusinessException be) {
            result.setViewName("/category/edit");
                        
            result.addObject("error", be.getCode());
            result.addObject("command", command);
            result.addObject("categories", getParentCandidatesSortedByPinyin(command.getId()));
        }        
        
        return result;
    }
    
    private List<Category> getParentCandidatesSortedByPinyin(Long id) throws Exception {
        List<Category> candidates = categoryService.getParentCandidates(id);
        candidates = CommonUtils.sortCategoriesByPinyin(candidates);
        
        return candidates;
    }
    

}
