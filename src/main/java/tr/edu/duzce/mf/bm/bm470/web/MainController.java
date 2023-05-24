package tr.edu.duzce.mf.bm.bm470.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

}
