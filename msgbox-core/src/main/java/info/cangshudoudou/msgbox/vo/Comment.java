package info.cangshudoudou.msgbox.vo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

@Entity
@Table(name="COMMENTS")
@SequenceGenerator(name="idGen", sequenceName="COMMENT_SEQUENCE", initialValue=1, allocationSize=1)
public class Comment {
    @Id 
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idGen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    Long id;
    
    @Column(name="TITLE",length=255, nullable=false)
    String title;
    
    @Column(name="CONTENT", nullable=false)
    @Lob
    String content;
    
    @Column(name="AUTHOR",length=255, nullable=false)
    String author;
    
    @Column(name="CREATION_DATE", nullable=false, columnDefinition="DATE")
    Date date;
    
    @ManyToOne
    @JoinColumn(name="MESSAGE_ID")
    Message message;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
}
