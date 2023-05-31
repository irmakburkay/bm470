package tr.edu.duzce.mf.bm.bm470.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.edu.duzce.mf.bm.bm470.model.Blog;
import tr.edu.duzce.mf.bm.bm470.model.User;
import tr.edu.duzce.mf.bm.bm470.service.BlogService;
import tr.edu.duzce.mf.bm.bm470.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllBlogs(Model model) {
        List<User> userList = userService.loadUsers();
        model.addAttribute("userList", userList);

        String partialView = "";
        for (User user : userList) {
            partialView += "<tr>";
            partialView += "<td>" + user.getUserID() + "</td>";
            partialView += "<td>" + user.getUsername() + "</td>";
            partialView += "<td>" + user.getPassword() + "</td>";
            partialView += "<td>" + user.getEmail() + "</td>";
            partialView += "<td>" + user.getIsActive() + "</td>";
            partialView += "</tr>";
        }
        model.addAttribute("partialView", partialView);

        return "user";
    }

}
