package com.cangshudoudou.msgbox.service.impl;


import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cangshudoudou.msgbox.BusinessException;
import com.cangshudoudou.msgbox.dao.CategoryDao;
import com.cangshudoudou.msgbox.dao.MessageDao;
import com.cangshudoudou.msgbox.service.CategoryService;
import com.cangshudoudou.msgbox.utils.ErrorCodeConstants;
import com.cangshudoudou.msgbox.vo.Category;

@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    private MessageDao messageDao;

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Category createCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException();
        }
        
        if (! StringUtils.hasText(category.getName()))  {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NAME_REQUIRED, "name must not be null");
        }
        
        if (category.getName().length() > 20) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NAME_TOO_LONG);
        }
        
        List<Category> categories = categoryDao.listCategories();
        for (Category c : categories) {
            if (c.getName().trim().equalsIgnoreCase(category.getName().trim())) {
                throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NAME_DUPLICATED, "name already existed");
            }
        }
        
        if (category.getDescription().length() > 200) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_DESCRIPTION_TOO_LONG);
        }
        
        if (category.getParent() != null && category.getParent().getId() != null) {
            Category parent = categoryDao.getCategory(category.getParent().getId());
            if (parent == null) {
                throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_PARENT_NOT_EXISTED, "parent category does not exist. parentId: " + category.getParent().getId());
            }
            category.setParent(parent);
        }
        
        categoryDao.createCategory(category);
        
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Category category = categoryDao.getCategory(id);
        if (category == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NOT_EXISTED, "category does not exist. id: " + id);
        }
        
        if (category.getChildren().size() > 0) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_DELETE_HAS_CHILDREN, "category must not be deleted with child categories associated");
        }
        
        categoryDao.deleteCategory(id);
    }

    @Override
    public Category getCategory(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        
        Category category = categoryDao.getCategory(id);
        
        Long count = messageDao.getMessageCountByCategoryId(id);
        if (count == 0 && category.getChildren().size() == 0) {
            category.setDeletable(true);
        }
        else {
            category.setDeletable(false);
        }
        
        return category;
    }

    @Override
    public List<Category> listCategories() {
        List<Category> categories = categoryDao.listCategories();
        
        for (Category category : categories) {;
            Long count = messageDao.getMessageCountByCategoryId(category.getId());
            if (count == 0 && category.getChildren().size() == 0) {
                category.setDeletable(true);
            }
            else {
                category.setDeletable(false);
            }
        }
        
        return categories;
    }

    @Override
    public Category updateCategory(Category category) {
        if (category == null || category.getId() == null) {
            throw new IllegalArgumentException();
        }
        
        if (! StringUtils.hasText(category.getName()))  {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NAME_REQUIRED, "name must not be null");
        }
        
        if (category.getName().length() > 20) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NAME_TOO_LONG);
        }
        
        List<Category> categories = categoryDao.listCategories();
        for (Category c : categories) {
            if (c.getName().trim().equalsIgnoreCase(category.getName().trim()) && !category.getId().equals(c.getId())) {
                throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NAME_DUPLICATED, "name already existed");
            }
        }
        
        if (category.getDescription().length() > 200) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_DESCRIPTION_TOO_LONG);
        }
        
        if (categoryDao.getCategory(category.getId()) == null) {
            throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_NOT_EXISTED, "category does not exist. id: " + category.getId());
        }
        
        if (category.getParent() != null && category.getParent().getId() != null) {
            Category parent = categoryDao.getCategory(category.getParent().getId());
            if (parent == null) {
                throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_PARENT_NOT_EXISTED, "parent category does not exist. parentId: " + category.getParent().getId());
            }
            
            if (category.getId().equals(parent.getId())) {
                throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_PARENT_EQUALS_SELF, "must not set self as parent. id: " + category.getId() + " parentId: " + parent.getId());
            }
            
            if (!validateNewParent(category.getId(), category.getParent().getId())) {
                throw new BusinessException(ErrorCodeConstants.ERR_CATEGORY_HIERARCHY_INVALID,
                        "invalid category hierarchy.id: " + category.getId() + " parentId: " + parent.getId());
            }
            
            category.setParent(parent);
        }
        
        categoryDao.updateCategory(category);
        category = categoryDao.getCategory(category.getId());
        
        return category;
    }

    @Override
    public void addChildCategory(Long parentId, Long childId) {
        if (parentId == null || childId == null) {
            throw new IllegalArgumentException();
        }
        
        Category parent = categoryDao.getCategory(parentId);
        if (parent == null) {
            throw new BusinessException("010401", "parent category does not exist. parentId: " + parentId);
        }
        
        Category child = categoryDao.getCategory(childId);
        if (child == null) {
            throw new BusinessException("010402", "child category does not exist. childId: " + child);
        }
        
        categoryDao.addChildCategory(parentId, childId);
    }

    @Override
    public void removeChildCategory(Long parentId, Long childId) {
        if (parentId == null || childId == null) {
            throw new IllegalArgumentException();
        }
        
        Category parent = categoryDao.getCategory(parentId);
        if (parent == null) {
            throw new BusinessException("010501", "parent category does not exist. parentId: " + parentId);
        }
        
        Category child = categoryDao.getCategory(childId);
        if (child == null) {
            throw new BusinessException("010502", "child category does not exist. childId: " + child);
        }

        categoryDao.removeChildCategory(parentId, childId);
    }
    
    private boolean validateNewParent(Long id, Long parentId) {
        List<Category> candidates = getParentCandidates(id);

        for (Category c : candidates) {
            if (c.equals(parentId)) {
                return false;
            }
        }
        
        return true;
    }
    
    public List<Category> getParentCandidates(Long id) {
        List<Category> candidates = categoryDao.listCategories();
        for (Category c : candidates) {
            if (id.equals(c.getId())) {
                candidates.remove(c);
                break;
            }
        }
        
        Category self = categoryDao.getCategory(id);
        List<Category> result = removeChildrenFromParentCandidates(self, candidates);
        
        return result;
    }
    
    private List<Category> removeChildrenFromParentCandidates(Category self, List<Category> candidates) {
        Set<Category> children = self.getChildren();
        
        if (children.size() > 0) {
            for (Category child : children) {
                candidates.remove(child);
                if (child.getChildren().size() > 0) {
                    removeChildrenFromParentCandidates(child, candidates);
                }
            }
        }
        
        return candidates;
    }
}
