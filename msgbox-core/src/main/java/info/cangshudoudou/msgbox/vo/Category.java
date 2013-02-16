package info.cangshudoudou.msgbox.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="CATEGORIES")
//@SequenceGenerator(name="idGen", sequenceName="CATEGORY_SEQUENCE", initialValue=1, allocationSize=1)
public class Category {
    @Id 
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idGen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    Long id;
    
    @Column(name="NAME",length=255, unique=true, nullable=false)
    String name;
    
    @Column(name="DESCRIPTION",length=255, nullable=true)
    String description;
    
    //@Transient
    @ManyToOne(optional=true, fetch=FetchType.EAGER)
    @JoinColumn(name="PARENT_CATEGORY_ID")
    Category parent;
    
    //@Transient
    @OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
    @JsonIgnore
    Set<Category> children = new HashSet<Category>();
    
    @Transient
    Boolean deletable;
    
    @Transient
    @JsonIgnore
    String parentId;
    
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
    }

    public Category() {
        
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getParent() {
        return parent;
    }
    public void setParent(Category parent) {
        this.parent = parent;
    }
    public Set<Category> getChildren() {
        return children;
    }
    public void setChildren(Set<Category> children) {
        this.children = children;
    }
    public void addChild(Category child) {
        if (child.getParent() != null) {
            child.getParent().removeChild(child);
        }
        this.getChildren().add(child);
        child.setParent(this);
    }

    public void removeChild(Category child) {
        if (!this.equals(child.getParent())) {
//            throw new BusinessException(0, "remove child category failed, child category does not belong to parent category, " + "parent category id:"
//                    + this.getId() + " child category id:" + child.getId());
            
            // TODO log
            return;
        }
        this.getChildren().remove(child);
        child.setParent(null);
    }
}
