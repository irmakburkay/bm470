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

    public void updateUser(Object object){
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public User getUserById(Long id){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("userID"), id);
        criteriaQuery.select(root).where(predicateUsername);
        Query<User> query = currentSession.createQuery(criteriaQuery);
        try{
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        finally {
            currentSession.close();
        }
    }

    public User checkUserExists(User user){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("username"), user.getUsername());
        Predicate predicatePassword = criteriaBuilder.equal(root.get("password"), user.getPassword());
        criteriaQuery.select(root).where(criteriaBuilder.and(predicateUsername, predicatePassword));
        Query<User> query = currentSession.createQuery(criteriaQuery);
        try{
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        finally {
            currentSession.close();
        }

    }
    public boolean checkUsernameExists(String username){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("username"), username);

        criteriaQuery.select(root).where(predicateUsername);
        Query<User> query = currentSession.createQuery(criteriaQuery);
        try{
            User returnedUser = query.getSingleResult();
            return true;
        }catch (Exception e){
            return false;
        }
        finally {
            currentSession.close();
        }
    }

    public boolean checkEmailExists(String email){
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicateUsername = criteriaBuilder.equal(root.get("email"), email);

        criteriaQuery.select(root).where(predicateUsername);
        Query<User> query = currentSession.createQuery(criteriaQuery);
        try{
            User returnedUser = query.getSingleResult();
            return true;
        }catch (Exception e){
            return false;
        }
        finally {
            currentSession.close();
        }
    }
}