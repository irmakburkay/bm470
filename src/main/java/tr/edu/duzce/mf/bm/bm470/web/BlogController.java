package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tr.edu.duzce.mf.bm.bm470.model.Blog;
import tr.edu.duzce.mf.bm.bm470.service.BlogService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/blog/*")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String getAllBlogs(Model model) {
        List<Blog> blogList = blogService.loadBlogs();
        model.addAttribute("blogList", blogList);

        String partialView = "";
        for (Blog blog : blogList) {
            partialView += "<tr>";
            partialView += "<td>" + blog.getBlogID() + "</td>";
            partialView += "<td>" + blog.getTitle() + "</td>";
            partialView += "<td>" + blog.getContent() + "</td>";
            partialView += "<td>" + blog.getIsActive() + "</td>";
            partialView += "<td>" + convertTime(blog.getCreationDate()) + "</td>";
            partialView += "<td>" + convertTime(blog.getLastChangeDate()) + "</td>";
            partialView += "</tr>";
        }
        model.addAttribute("partialView", partialView);

        return "blog";
    }

    private String convertTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(time);
    }

}
