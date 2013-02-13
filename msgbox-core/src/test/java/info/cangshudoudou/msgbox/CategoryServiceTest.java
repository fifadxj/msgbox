package info.cangshudoudou.msgbox;

import java.util.List;
import java.util.Set;

import info.cangshudoudou.msgbox.service.CategoryService;
import info.cangshudoudou.msgbox.vo.Category;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class CategoryServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private Long CATEGORY_ID = null;
    private String CATEGORY_NAME = "name";
    private String CATEGORY_DESCRIPTION = "desc";
    
    private Long CATEGORY_ID_2 = null;
    private String CATEGORY_NAME_2 = "name_2";
    private String CATEGORY_DESCRIPTION_2 = "desc_2";
    
    private Long PARENT_CATEGORY_ID = null;
    private String PARENT_CATEGORY_NAME = "parent name";
    private String PARENT_CATEGORY_DESCRIPTION = "parent desc";
    
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code
    }

    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    }

    @Before
    public void setUp() {
        SessionFactory sf = (SessionFactory) this.applicationContext.getBean("sessionFactory");
        Session session = sf.getCurrentSession();
        Category parent = new Category();
        parent.setName(PARENT_CATEGORY_NAME);
        parent.setDescription(PARENT_CATEGORY_DESCRIPTION);
        parent.setParent(null);
        Long id = (Long) session.save(parent);
        PARENT_CATEGORY_ID = id;
        System.out.println("parent category id is: " + PARENT_CATEGORY_ID);
        
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);
        category.setParent(parent);
        id = (Long) session.save(category);
        CATEGORY_ID = id;
        
        Category category2 = new Category();
        category2.setName(CATEGORY_NAME_2);
        category2.setDescription(CATEGORY_DESCRIPTION_2);
        category2.setParent(null);
        id = (Long) session.save(category2);
        CATEGORY_ID_2 = id;
        
        session.flush();
        session.clear();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateCategory() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        Category newCategory = new Category();
        newCategory.setName("testCreateCategory name");
        newCategory.setDescription("testCreateCategory desc");
        Category parent = new Category();
        parent.setId(PARENT_CATEGORY_ID);
        newCategory.setParent(parent);
        newCategory = service.createCategory(newCategory);
        Long id = newCategory.getId();
        SessionFactory sf = (SessionFactory) this.applicationContext.getBean("sessionFactory");
        Session session = sf.getCurrentSession();
        session.flush();
        session.clear();
        
        Query query = session.createQuery("from Category where id=:id");
        query.setParameter("id", id);
        Category category = (Category) query.uniqueResult();
        Assert.assertNotNull(category);
        Assert.assertEquals(category.getId(), id);
        Assert.assertEquals(category.getName(), "testCreateCategory name");
        Assert.assertEquals(category.getDescription(), "testCreateCategory desc");
        Assert.assertEquals(category.getParent().getId(), PARENT_CATEGORY_ID);
        Assert.assertEquals(category.getParent().getName(), PARENT_CATEGORY_NAME);
        Assert.assertEquals(category.getParent().getDescription(), PARENT_CATEGORY_DESCRIPTION);
    }
    
    @Test
    public void testDeleteCategory() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        service.deleteCategory(CATEGORY_ID);
        SessionFactory sf = (SessionFactory) this.applicationContext.getBean("sessionFactory");
        Session session = sf.getCurrentSession();
        session.flush();
        session.clear();
        
        Query query = session.createQuery("from Category where id=:id");
        query.setParameter("id", CATEGORY_ID);
        Category category = (Category) query.uniqueResult();
        Assert.assertNull(category);
    }
    
    @Test
    public void testGetCategory() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        Category category = service.getCategory(CATEGORY_ID);
        Assert.assertNotNull(category);
        Assert.assertEquals(category.getId(), CATEGORY_ID);
        Assert.assertEquals(category.getName(), CATEGORY_NAME);
        Assert.assertEquals(category.getDescription(), CATEGORY_DESCRIPTION);
        Assert.assertEquals(category.getParent().getId(), PARENT_CATEGORY_ID);
    }
    
    @Test
    public void testListCategories() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        List<Category> categories = service.listCategories();
        
        Assert.assertEquals(categories.size(), 3);
        Category category = categories.get(1);// first(index=0) record is parent category
        Assert.assertEquals(category.getId(), CATEGORY_ID);
        Assert.assertEquals(category.getName(), CATEGORY_NAME);
        Assert.assertEquals(category.getDescription(), CATEGORY_DESCRIPTION);
        Assert.assertEquals(category.getParent().getId(), PARENT_CATEGORY_ID);
    }

    @Test
    public void testUpdateCategory() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        Category updatedcategory = new Category();
        updatedcategory.setId(CATEGORY_ID);
        updatedcategory.setName("testUpdateCategory name");
        updatedcategory.setDescription("testUpdateCategory desc");
        service.updateCategory(updatedcategory);
        SessionFactory sf = (SessionFactory) this.applicationContext.getBean("sessionFactory");
        Session session = sf.getCurrentSession();
        session.flush();
        session.clear();
        
        Query query = session.createQuery("from Category where id=:id");
        query.setParameter("id", updatedcategory.getId());
        Category category = (Category) query.uniqueResult();
        Assert.assertNotNull(category);
        Assert.assertEquals(category.getId(), updatedcategory.getId());
        Assert.assertEquals(category.getName(), "testUpdateCategory name");
        Assert.assertEquals(category.getDescription(), "testUpdateCategory desc");
        Assert.assertEquals(category.getParent().getId(), PARENT_CATEGORY_ID);
        Assert.assertEquals(category.getParent().getName(), PARENT_CATEGORY_NAME);
        Assert.assertEquals(category.getParent().getDescription(), PARENT_CATEGORY_DESCRIPTION);
    }
    
    @Test
    public void testAddChildCategory() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        service.addChildCategory(PARENT_CATEGORY_ID, CATEGORY_ID_2);
        SessionFactory sf = (SessionFactory) this.applicationContext.getBean("sessionFactory");
        Session session = sf.getCurrentSession();
        session.flush();
        session.clear();
        
        Query query = session.createQuery("from Category where id=:id");
        query.setParameter("id", CATEGORY_ID_2);
        Category category = (Category) query.uniqueResult();
        Assert.assertEquals(category.getParent().getId(), PARENT_CATEGORY_ID);
        
        query = session.createQuery("from Category where id=:id");
        query.setParameter("id", PARENT_CATEGORY_ID);
        category = (Category) query.uniqueResult();
        Assert.assertEquals(category.getChildren().size(), 2);
    }
    
    @Test
    public void testRemoveChildCategory() {
        CategoryService service = (CategoryService) this.applicationContext.getBean("categoryService");
        service.removeChildCategory(PARENT_CATEGORY_ID, CATEGORY_ID);
        SessionFactory sf = (SessionFactory) this.applicationContext.getBean("sessionFactory");
        Session session = sf.getCurrentSession();
        session.flush();
        session.clear();
        
        Query query = session.createQuery("from Category where id=:id");
        query.setParameter("id", CATEGORY_ID);
        Category category = (Category) query.uniqueResult();
        Assert.assertEquals(category.getParent(), null);
        
        query = session.createQuery("from Category where id=:id");
        query.setParameter("id", PARENT_CATEGORY_ID);
        category = (Category) query.uniqueResult();
        Assert.assertEquals(category.getChildren().size(), 0);
    }
    
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(CategoryServiceTest.class);
    }
}
