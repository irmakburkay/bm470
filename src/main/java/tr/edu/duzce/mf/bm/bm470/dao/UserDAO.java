package tr.edu.duzce.mf.bm.bm470.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.mf.bm.bm470.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public boolean saveOrUpdateObject(Object object){
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

    public List<User> getAllUsers(){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);

        Query<User> dbQuery = currentSession.createQuery(criteriaQuery);
        List<User> userList = dbQuery.getResultList();

        return userList;
    }

    public User checkUserExists(User user){
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("username"), user.getUsername());
        Predicate predicatePassword = criteriaBuilder.equal(root.get("password"), user.getPassword());
        criteriaQuery.select(root).where(criteriaBuilder.and(predicateUsername, predicatePassword));
        Query<User> query = session.createQuery(criteriaQuery);
        try{
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }

    }
    public boolean checkUsernameExists(String username){
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("username"), username);

        criteriaQuery.select(root).where(predicateUsername);
        Query<User> query = session.createQuery(criteriaQuery);
        try{
            User returnedUser = query.getSingleResult();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean checkEmailExists(String email){
        Session session = getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("email"), email);

        criteriaQuery.select(root).where(predicateUsername);
        Query<User> query = session.createQuery(criteriaQuery);
        try{
            User returnedUser = query.getSingleResult();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}