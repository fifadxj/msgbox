package info.cangshudoudou.msgbox.service;

import info.cangshudoudou.msgbox.vo.Category;

import java.util.List;

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
