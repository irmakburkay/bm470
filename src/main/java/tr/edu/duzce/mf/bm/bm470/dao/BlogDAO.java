package tr.edu.duzce.mf.bm.bm470.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm.bm470.model.Blog;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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

    public boolean updateObject(Object object) {
        boolean success = true;
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            session.close();
        }
        return success;
    }

    public List<Blog> getBlogsWithPaging(int pageNumber, int maxResult) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);
        Root<Blog> root = criteriaQuery.from(Blog.class);

        Predicate predicate = criteriaBuilder.equal(root.get("isActive"), 1);

        criteriaQuery.select(root).where(predicate);

        Query<Blog> dbQuery = currentSession.createQuery(criteriaQuery);
        dbQuery.setFirstResult(pageNumber * maxResult);
        dbQuery.setMaxResults(maxResult);

        List<Blog> blogList = dbQuery.getResultList();

        currentSession.close();

        return blogList;
    }

    public Long getBlogCount() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Blog> root = criteriaQuery.from(Blog.class);

        Predicate predicate = criteriaBuilder.equal(root.get("isActive"), 1);

        criteriaQuery.select(criteriaBuilder.count(root)).where(predicate);

        Query<Long> dbQuery = currentSession.createQuery(criteriaQuery);
        Long totalCount = dbQuery.getSingleResult();

        currentSession.close();

        return totalCount;
    }

    public List<Blog> getBlogsByUserId(Long id){
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);
        Root<Blog> root = criteriaQuery.from(Blog.class);
        Predicate predicateAdi = criteriaBuilder.equal(root.get("user"), id);

        criteriaQuery.select(root).where(predicateAdi);

        Query<Blog> query = session.createQuery(criteriaQuery);
        List<Blog> blogList = query.getResultList();
        return blogList;
    }

}
