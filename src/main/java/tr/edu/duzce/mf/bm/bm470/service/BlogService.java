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

    public List<Blog> loadBlogs() {
        List<Blog> blogList = blogDAO.getAllBlogs();
        return blogList;
    }

}
