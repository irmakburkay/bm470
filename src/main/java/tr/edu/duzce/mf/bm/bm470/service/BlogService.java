package tr.edu.duzce.mf.bm.bm470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm.bm470.dao.BlogDAO;
import tr.edu.duzce.mf.bm.bm470.model.Blog;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class BlogService {

    @Autowired
    private BlogDAO blogDAO;

    @Transactional(readOnly = false)
    public void addBlog(Blog blog) {
        blogDAO.saveOrUpdateObject(blog);
    }

    public List<Blog> loadBlogs() {
        List<Blog> blogList = blogDAO.getAllBlogs();
        return blogList;
    }

    public List<Blog> loadBlogsWithPaging(int pageNumber, int maxResult) {
        List<Blog> blogList = blogDAO.getBlogsWithPaging(pageNumber, maxResult);
        return blogList;
    }

    public Blog loadBlogById(Long id) {
        Blog blog = (Blog) blogDAO.loadObject(Blog.class, id);
        return blog;
    }

    public Long getBlogCount() {
        Long blogSize = blogDAO.getBlogCount();
        return blogSize;
    }

}
