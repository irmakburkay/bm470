package tr.edu.duzce.mf.bm.bm470.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "blog", schema = "bm470")
public class Blog implements Serializable {

    public Blog() {
        setIsActive(true);
        setCreationDate(new Date());
        setLastChangeDate(new Date());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogID", nullable = false)
    private Long blogID;
    @Column(name = "title", nullable = false, length = 45)
    private String title;
    @Column(name = "content", nullable = false, length = 450)
    private String content;
    @Column(name = "creationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "lastChangeDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangeDate;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userID")
    private User user;

    public Long getBlogID() {
        return blogID;
    }

    public void setBlogID(Long blogID) {
        this.blogID = blogID;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(blogID, blog.blogID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogID);
    }
}
