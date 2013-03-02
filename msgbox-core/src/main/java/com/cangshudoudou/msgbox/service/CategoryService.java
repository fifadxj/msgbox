package com.cangshudoudou.msgbox.service;


import java.util.List;

import com.cangshudoudou.msgbox.vo.Category;

public interface CategoryService {
    Category createCategory(Category category);
    void deleteCategory(Long id);
    Category updateCategory(Category category);
    Category getCategory(Long id);
    List<Category> listCategories();
    void addChildCategory(Long parentId, Long childId);
    void removeChildCategory(Long parentId, Long childId);
    List<Category> getParentCandidates(Long id);
}
