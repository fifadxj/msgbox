package info.cangshudoudou.msgbox.dao;

import info.cangshudoudou.msgbox.vo.Category;

import java.util.List;

public interface CategoryDao {
    Long createCategory(Category category);
    void deleteCategory(Long id);
    void updateCategory(Category category);
    Category getCategory(Long id);
    List<Category> listCategories();
    void addChildCategory(Long parentId, Long childId);
    void removeChildCategory(Long parentId, Long childId);
}
