package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/session/*", method = RequestMethod.GET)
public class SessionController {

    @GetMapping("/")
    public String session(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("creationTime", convertTime(session.getCreationTime()));
        model.addAttribute("lastAccessedTime", convertTime(session.getLastAccessedTime()));
        return "session";
    }

    private String convertTime(Long timeAsLong) {
        Date time = new Date(timeAsLong);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return sdf.format(time);
    }

}
