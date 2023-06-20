package tr.edu.duzce.mf.bm.bm470.web;

import com.mysql.cj.xdevapi.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm.bm470.model.Blog;
import tr.edu.duzce.mf.bm.bm470.model.User;
import tr.edu.duzce.mf.bm.bm470.service.BlogService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/blog/*")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/{blogId}")
    public String getBlogById(@PathVariable("blogId") String stringId, Model model, HttpSession session) {

        Blog blog;

        try {
            blog = blogService.loadBlogById(Long.parseLong(stringId));
            if (blog == null) throw new Exception();
        } catch (Exception e) {
            return "redirect:/";
        }

        model.addAttribute("blog", blog);

        return "blog";
    }

    @PostMapping("/approve")
    public @ResponseBody String approve(@RequestParam Long blogID) {
        Blog blog = blogService.loadBlogById(blogID);
        blog.setIsActive(true);
        blog.setLastChangeDate(new Date());
        blogService.updateBlog(blog);
        return "redirect:/user/" + blog.getUser().getUserID();
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestParam Long blogID) {
        Blog blog = blogService.loadBlogById(blogID);
        blog.setIsActive(false);
        blog.setLastChangeDate(new Date());
        blogService.updateBlog(blog);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long blogID, Model model) {
        Blog blog = blogService.loadBlogById(blogID);
        model.addAttribute("blog", blog);
        return "addblog";
    }

    private String convertTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(time);
    }

    @ModelAttribute("blog")
    public Blog setBlog(){
        return new Blog();
    }

    @GetMapping("/post")
    public String showBlogForm(){
        return "addblog";
    }

    @RequestMapping(value = "/saveblog", method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute("blog") Blog blog, Model model, HttpSession session){
        if (blog.getBlogID() == null) {
            blog.setUser((User) session.getAttribute("loginUser"));
            blogService.addBlog(blog);
            model.addAttribute("meesage", "Kayıt Başarılı");
        } else {
            blog.setUser((User) session.getAttribute("loginUser"));
            blogService.updateBlog(blog);
            model.addAttribute("meesage", "Kayıt Başarılı");
        }

        model.addAttribute("blog", blog);
        return "blog";
    }
}
