package tr.edu.duzce.mf.bm.bm470.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.edu.duzce.mf.bm.bm470.model.Blog;
import tr.edu.duzce.mf.bm.bm470.model.User;
import tr.edu.duzce.mf.bm.bm470.service.BlogService;
import tr.edu.duzce.mf.bm.bm470.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(Model model) {
        User user1 = new User();
        user1.setEmail("ahmet@gmail.com");
        user1.setUsername("ahmet");
        user1.setPassword("ahmet123");
        userService.addUser(user1);
        Blog blog1 = new Blog();
        blog1.setTitle("blog1_title");
        blog1.setContent("blog1_content");
        blog1.setUser(user1);
        blogService.addBlog(blog1);

        List<Blog> blogList = blogService.loadBlogs();
        model.addAttribute("blogList", blogList);

        return "index";
    }

    private String convertTime(Long timeAsLong) {
        Date time = new Date(timeAsLong);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return sdf.format(time);
    }

}
