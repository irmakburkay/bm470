package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm.bm470.model.Blog;
import tr.edu.duzce.mf.bm.bm470.model.User;
import tr.edu.duzce.mf.bm.bm470.service.BlogService;
import tr.edu.duzce.mf.bm.bm470.service.UserService;
import tr.edu.duzce.mf.bm.bm470.util.UserValidation;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @ModelAttribute("user")
    public User setUser(){
        return new User();
    }

    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/saveuser")
    public ModelAndView saveUser(@ModelAttribute("user") User user, Model model){
        boolean isUsernameValid = UserValidation.isUsernameValid(user.getUsername());
        boolean isPasswordValid = UserValidation.isPasswordValid(user.getPassword());
        boolean isEmailValid = UserValidation.isEmailValid(user.getEmail());
        boolean doesPasswordsMatch = UserValidation.doesPasswordsMatch(user.getPassword(), user.getPasswordRe());

        if(!isUsernameValid || !isPasswordValid || !isEmailValid || !doesPasswordsMatch){
            if(!isUsernameValid){
                model.addAttribute("usernameValidity", "Kullanıcı adı 3-40 karakter arası olmalıdır.");
            }
            if(!isPasswordValid){
                model.addAttribute("passwordValidity", "Şifre minimum 8 karakter olmalıdır.");
            }
            if(!isEmailValid){
                model.addAttribute("emailValidity", "Lütfen geçerli bir e-mail giriniz.");
            }
            if(!doesPasswordsMatch){
                model.addAttribute("passwordMatch", "Şifreler eşleşmemektedir.");
            }
        }
        else{
            boolean doesUsernameExist = userService.checkUsernameExists(user.getUsername());
            boolean doesEmailExist = userService.checkEmailExists(user.getEmail());

            if(doesUsernameExist || doesEmailExist){
                if(doesUsernameExist){
                    model.addAttribute("usernameAvailability", "Kullanıcı adı daha önce alınmış!");
                }
                if(doesEmailExist){
                    model.addAttribute("emailAvailability", "E-mail kullanımda!");
                }
            }
            else{
                userService.addUser(user);
                model.addAttribute("message", "OK");
            }
        }
        return new ModelAndView("register");
    }

    @GetMapping("/{userId}")
    public ModelAndView getBlogById(@PathVariable("userId") String StringId, Model model) {
        User user = null;
        Long id;

        try{
            id = Long.parseLong(StringId);
            user = userService.getUserById(id);
        }catch (Exception e){
            return new ModelAndView("redirect:/");
        }

        List<Blog> blogList;

        if(user != null){
            blogList = blogService.getBlogsByUserId(id);
            model.addAttribute("user", user);

            if(blogList.size() == 0){
                model.addAttribute("lurker", "Bu kullanıcı henüz paylaşımda bulunmamış.");
            }
            else{
                model.addAttribute("blogList", blogList);
            }
            return new ModelAndView("user");
        }
        else {
            model.addAttribute("message", "Kullanıcı bulunamadı!");
            return new ModelAndView("error");
        }
    }

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
    public String profile(Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestParam Long userID, HttpSession session) {
        User user = userService.getUserById(userID);
        user.setIsActive(false);
        userService.updateUser(user);
        logout(session);
        return user.getIsActive() ? "Aktif" : "Deaktif";
    }

}
