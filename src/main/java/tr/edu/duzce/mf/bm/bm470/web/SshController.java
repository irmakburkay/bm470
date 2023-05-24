package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/ssh/*")
public class SshController {

    @GetMapping("/")
    public @ResponseBody String connectSSH(Model model) {

//        JSch jSch = new JSch();

        return "connect SSH";
    }

}
