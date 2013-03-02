package com.cangshudoudou.msgbox.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Index;

@Entity
@Table(name="MESSAGES")
//@SequenceGenerator(name="idGen", sequenceName="MESSAGE_SEQUENCE", initialValue=1, allocationSize=1)
public class Message {
    @Id 
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idGen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    Long id;
    
//    @Column(name="TITLE",length=255, unique=true, nullable=false)
//    String title;
    
    @Column(name="CONTENT", nullable=false)
    @Lob
    String content;
    
    @Column(name="RANK", nullable=false)
    Integer rank;
    
    @Column(name="SOURCE",length=255, nullable=true)
    String source;
    
    @Column(name="DISABLED", nullable=false)
    boolean disabled;
 
    @Column(name="TOP", nullable=false)
    boolean top;
    
    @Column(name="CREATED_DATE", nullable=false, columnDefinition="DATETIME")
    Date creationDate;
    
    @Column(name="MODIFIED_DATE", nullable=false, columnDefinition="DATETIME")
    Date lastModifiedDate;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="CATEGORY_ID")
    @JsonIgnore
    Category category;
    
//    @OneToMany(mappedBy="message")
//    @OrderBy("date desc")
//    List<Comment> comments;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public boolean isTop() {
        return top;
    }
    public void setTop(boolean top) {
        this.top = top;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
