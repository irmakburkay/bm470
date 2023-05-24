package tr.edu.duzce.mf.bm.bm470.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm.bm470.model.Blog;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class BlogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getCurrentSession().get(clazz, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        boolean success = true;
        try {
            Serializable s = getCurrentSession().save(object);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    public boolean removeObject(Object object) {
        boolean success = true;
        try {
            getCurrentSession().remove(object);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public List<Blog> getAllBlogs() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);
        Root<Blog> root = criteriaQuery.from(Blog.class);

        criteriaQuery.select(root);

        Query<Blog> dbQuery = currentSession.createQuery(criteriaQuery);
        List<Blog> blogList = dbQuery.getResultList();

        return blogList;
    }

}
