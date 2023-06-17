package tr.edu.duzce.mf.bm.bm470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm.bm470.dao.UserDAO;
import tr.edu.duzce.mf.bm.bm470.model.User;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = false)
    public void addUser(User user) {
        userDAO.saveOrUpdateObject(user);
    }

    public List<User> loadUsers() {
        List<User> userList = userDAO.getAllUsers();
        return userList;
    }

}
