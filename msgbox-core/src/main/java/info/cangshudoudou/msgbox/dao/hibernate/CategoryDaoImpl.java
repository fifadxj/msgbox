package info.cangshudoudou.msgbox.dao.hibernate;

import info.cangshudoudou.msgbox.dao.CategoryDao;
import info.cangshudoudou.msgbox.vo.Category;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;

public class CategoryDaoImpl extends DaoSupport implements CategoryDao {

    @Override
    public Long createCategory(Category category) {
        getCurrentSession().save(category);
        return category.getId();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = (Category) getCurrentSession().get(Category.class, id);
        getCurrentSession().delete(category);
    }

    @Override
    public Category getCategory(Long id) {
        Category category = (Category) getCurrentSession().get(Category.class, id);
        if (category != null) {
            Hibernate.initialize(category.getChildren());
        }
        
        return category;
    }

    @Override
    public List<Category> listCategories() {
        Query query = getCurrentSession().createQuery("from Category");
        List<Category> categories = (List<Category>) query.list();
//        for (Category category : categories) {
//            Hibernate.initialize(category.getChildren());
//        }
        
        return categories;
    }

    @Override
    public void updateCategory(Category category) {
//        Category old = getCategory(category.getId());
//        old.setName(category.getName());
//        old.setDescription(category.getDescription());
//        old.setParent(category.getParent());
        
        getCurrentSession().merge(category);
    }

    @Override
    public void addChildCategory(Long parentId, Long childId) {
        Category parent = getCategory(parentId);
        Category child = getCategory(childId);
        parent.addChild(child);
    }

    @Override
    public void removeChildCategory(Long parentId, Long childId) {
        Category parent = getCategory(parentId);
        Category child = getCategory(childId);
        parent.removeChild(child);
    }

}
