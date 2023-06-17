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
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getCurrentSession().get(clazz, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        boolean success = true;
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            session.close();
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

    public List<Blog> getBlogsWithPage(int pageNumber) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);
        Root<Blog> root = criteriaQuery.from(Blog.class);

        criteriaQuery.select(root);

        Query<Blog> dbQuery = currentSession.createQuery(criteriaQuery);
        dbQuery.setFirstResult(pageNumber * 5);
        dbQuery.setMaxResults(5);

        List<Blog> blogList = dbQuery.getResultList();

        return blogList;
    }

    public Long getBlogCount() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Blog> root = criteriaQuery.from(Blog.class);

        criteriaQuery.select(criteriaBuilder.count(root));

        Query<Long> dbQuery = currentSession.createQuery(criteriaQuery);
        Long totalCount = dbQuery.getSingleResult();
        return totalCount;
    }

}
