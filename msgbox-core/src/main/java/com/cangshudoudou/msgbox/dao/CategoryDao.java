package com.cangshudoudou.msgbox.dao;


import java.util.List;

import com.cangshudoudou.msgbox.vo.Category;

public interface CategoryDao {
    Long createCategory(Category category);
    void deleteCategory(Long id);
    void updateCategory(Category category);
    Category getCategory(Long id);
    List<Category> listCategories();
    void addChildCategory(Long parentId, Long childId);
    void removeChildCategory(Long parentId, Long childId);
}
