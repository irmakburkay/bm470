package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm.bm470.model.User;
import tr.edu.duzce.mf.bm.bm470.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllBlogs(Model model) {
        User us1 = new User();
        us1.setUsername("sadfasdf");
        us1.setPassword("asdfa");
        us1.setEmail("asdf");
        userService.addUser(us1);
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

    @ModelAttribute("user")
    public User setUser(){
        return new User();
    }

    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }

//    @RequestMapping("/saveuser")
//    public ModelAndView seveuser(){
//
//    }

//    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, HttpSession session){
        session.removeAttribute("usernameAvailability");
        session.removeAttribute("emailAvailability");
        session.removeAttribute("message");

        boolean doesUsernameExist = userService.checkUsernameExists(user.getUsername());
        boolean doesEmailExist = userService.checkEmailExists(user.getEmail());

        if(doesUsernameExist){
            session.setAttribute("usernameAvailability", "Kullanıcı adı alınmış!");
        }

        if(doesEmailExist){
            session.setAttribute("emailAvailability", "Email kullanımda!");
        }

        if(!doesUsernameExist && !doesEmailExist){
            if(user.getUsername() != null && user.getPassword() != null && user.getEmail() != null
                    && !user.getUsername().trim().isEmpty()
                    && !user.getPassword().trim().isEmpty()
                    && !user.getEmail().trim().isEmpty()
            ) {
                userService.addUser(user);
            }
            else {
                session.setAttribute("message", "Sorun var!");
            }
        }
        return "redirect:/";
    }

    @PostMapping("/saveuser")
    public ModelAndView saveUser(@ModelAttribute("user") User user, Model model){
        boolean doesUsernameExist = userService.checkUsernameExists(user.getUsername());
        boolean doesEmailExist = userService.checkEmailExists(user.getEmail());

        if(doesUsernameExist || doesEmailExist){
            if(doesUsernameExist){
                model.addAttribute("usernameAvailability", "Kullanıcı adı daha önce alınmış!");
            }
            if(doesEmailExist){
                model.addAttribute("emailAvailability", "E-mail kullanımda!");
            }
            return new ModelAndView("register");
        }
        userService.addUser(user);
        model.addAttribute("message", "OK");
        return new ModelAndView("register");
    }

//    @GetMapping("/{blogId}")
//    public ModelAndView getBlogById(@PathVariable("blogId") Long id, Model model) {
//
//        Blog blog = blogService.loadBlogById(id);
//
//        if (blog == null)
//            return new ModelAndView("redirect:http://localhost:9090/bm470/");
//
//        List<Blog> blogList = new ArrayList<>();
//        blogList.add(blog);
//        model.addAttribute("blogList", blogList);
//
//        String partialView = "";
//        for (Blog blogItem : blogList) {
//            partialView += "<tr>";
//            partialView += "<td>" + blogItem.getBlogID() + " ne saçma şey </td>";
//            partialView += "<td>" + blogItem.getTitle() + "</td>";
//            partialView += "<td>" + blogItem.getContent() + "</td>";
//            partialView += "<td>" + blogItem.getIsActive() + "</td>";
//            partialView += "<td>" + convertTime(blogItem.getCreationDate()) + "</td>";
//            partialView += "<td>" + convertTime(blogItem.getLastChangeDate()) + "</td>";
//            partialView += "</tr>";
//        }
//        model.addAttribute("partialView", partialView);
//
//        return new ModelAndView("blog");
//    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginLogout(@ModelAttribute("user") User user, Model model, HttpSession session){
        User lUser = userService.checkUserExists(user);
        if(lUser != null){
            session.setAttribute("loginUser", lUser);
            return "redirect:/";
        }
        else {
            model.addAttribute("login_message", "Kullanıcı adı veya şifre hatalı");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if(session.getAttribute("loginUser") != null){
            session.removeAttribute("loginUser");
        }
        return "redirect:/";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "profile";
    }
}
