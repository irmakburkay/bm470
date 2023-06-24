package tr.edu.duzce.mf.bm.bm470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm.bm470.dao.UserDAO;
import tr.edu.duzce.mf.bm.bm470.model.User;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void addUser(User user) {
        userDAO.saveOrUpdateObject(user);
    }

    public User getUserById(Long id){
        return userDAO.getUserById(id);
    }

    public User checkUserExists(User user){
        return userDAO.checkUserExists(user);
    }
    public boolean checkUsernameExists(String username){
        return userDAO.checkUsernameExists(username);
    }

    public boolean checkEmailExists(String email){
        return userDAO.checkEmailExists(email);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

}
