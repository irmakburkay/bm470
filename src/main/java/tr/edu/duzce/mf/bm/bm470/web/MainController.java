package tr.edu.duzce.mf.bm.bm470.web;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
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
import java.util.*;

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

//        Random r = new Random();
//        String alphabet = "abcdefghijklmnopqrstuvwxyz";
//        String number = "0123456789";
//        StringBuilder username = new StringBuilder();
//        StringBuilder password = new StringBuilder();
//        for (int i = 0; i < 6; i++)
//            username.append(alphabet.charAt(r.nextInt(alphabet.length())));
//        for (int i = 0; i < 4; i++)
//            password.append(number.charAt(r.nextInt(number.length())));

        Lorem lorem = LoremIpsum.getInstance();

        String username = lorem.getName();
        String email = lorem.getEmail();
        String password = lorem.getWords(1);

        String title = lorem.getTitle(1, 3);
        String content = lorem.getWords(5,25);
        int a = content.length();

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(user);
        blogService.addBlog(blog);

        if (session.getAttribute("maxResult") == null)
            session.setAttribute("maxResult", 5);


        return "forward:/page?pageNumber=0&maxResult=" + session.getAttribute("maxResult").toString();
    }

    @GetMapping("/page")
    public String pagination(@RequestParam("pageNumber") int pageNumber, @RequestParam("maxResult") int maxResult, Model model, HttpSession session) {
        boolean flag = true;
        if (Arrays.asList(5, 10, 20).contains(maxResult)) {
            if ((int) session.getAttribute("maxResult") != maxResult)
                session.setAttribute("maxResult", maxResult);
        } else {
            flag = false;
            maxResult = 5;
            session.setAttribute("maxResult", maxResult);
        }

        Long blogSize = blogService.getBlogCount();
        int pageSize = (int) Math.ceil(blogSize / (double) maxResult);

        if (pageNumber < 0 || pageNumber > pageSize - 1) {
            flag = false;
            pageNumber = 0;
        }

        if (!flag)
            return "redirect:/page?pageNumber=" + pageNumber + "&maxResult=" + maxResult;

        List<Blog> blogList = blogService.loadBlogsWithPaging(pageNumber, maxResult);
//        session.setAttribute("loginUser", blogList.get(1).getUser());

        model.addAttribute("blogList", blogList);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        return "index";
    }

    private String convertTime(Long timeAsLong) {
        Date time = new Date(timeAsLong);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return sdf.format(time);
    }

}
