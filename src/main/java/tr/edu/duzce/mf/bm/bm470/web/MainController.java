package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.edu.duzce.mf.bm.bm470.model.Blog;
import tr.edu.duzce.mf.bm.bm470.model.User;
import tr.edu.duzce.mf.bm.bm470.service.BlogService;
import tr.edu.duzce.mf.bm.bm470.service.UserService;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(HttpSession session) {
        if(session.getAttribute("loginUser") == null){
            // button textleri için
            session.setAttribute("login_logout_text", "Giriş Yap");
            session.setAttribute("register_profile_text", "Kaydol");
            // linkler için
            session.setAttribute("login_logout_link", "login");
            session.setAttribute("register_profile_link", "register");
        }
        else{
            // button textleri için
            session.setAttribute("login_logout_text", "Çıkış Yap");
            session.setAttribute("register_profile_text", "Profil");
            // linkler için
            session.setAttribute("login_logout_link", "logout");
            session.setAttribute("register_profile_link", "profile");
        }

        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 6; i++)
            username.append(alphabet.charAt(r.nextInt(alphabet.length())));
        for (int i = 0; i < 4; i++)
            password.append(number.charAt(r.nextInt(number.length())));

        User user = new User();
        user.setEmail(username + "@gmail.com");
        user.setUsername(username.toString());
        user.setPassword(password.toString());
        userService.addUser(user);
        Blog blog = new Blog();
        blog.setTitle("blog_title");
        blog.setContent("blog_content");
        blog.setUser(user);
        blogService.addBlog(blog);

        return "forward:/page?pageNumber=0&maxResult=5";
    }

    @GetMapping("/page")
    public String pagination(@RequestParam("pageNumber") int pageNumber, @RequestParam("maxResult") int maxResult, Model model) {
        List<Blog> blogList;
        try {
            blogList = blogService.loadBlogsWithPaging(pageNumber, maxResult);
            if (blogList.isEmpty()) throw new Exception();
        } catch (Exception e) {
            pageNumber = 0;
            maxResult = 5;
            blogList = blogService.loadBlogsWithPaging(pageNumber, maxResult);
        }
        model.addAttribute("blogList", blogList);
        Long blogSize = blogService.getBlogCount();
        int pageSize = (int) Math.ceil(blogSize / (double) maxResult);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("maxResult", maxResult);
        return "index";
    }

    private String convertTime(Long timeAsLong) {
        Date time = new Date(timeAsLong);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return sdf.format(time);
    }

}
