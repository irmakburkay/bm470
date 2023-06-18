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
    public ModelAndView getBlogById(@PathVariable("blogId") String stringId, Model model) {

        Blog blog;

        try {
            blog = blogService.loadBlogById(Long.parseLong(stringId));
            if (blog == null) throw new Exception();
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }

        List<Blog> blogList = new ArrayList<>();
        blogList.add(blog);
        model.addAttribute("blogList", blogList);

        return new ModelAndView("blog");
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
    public String saveBlog(@ModelAttribute("blog") Blog blog, Model model){
        blogService.addBlog(blog);

        model.addAttribute("meesage", "Kayıt Başarılı");
        model.addAttribute("blog", blog);
        return "blog";
    }
}
