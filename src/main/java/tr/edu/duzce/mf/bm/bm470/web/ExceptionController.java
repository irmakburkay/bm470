package tr.edu.duzce.mf.bm.bm470.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFound(HttpServletRequest request, Exception e) {

        System.err.println(request.getRequestURL() + " istegine karsilik karsilayici bulunamadi. " +
                "Hata mesaji: " + e.getMessage());
        return "error";
    }

//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public String handleBadRequest(HttpServletRequest request, Exception e, Model model) {
//        model.addAttribute("message", "BAD BAD REQUEEEST!");
//        return "error";
//    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalServerError(HttpServletRequest request, Exception e, Model model) {
        System.err.println(request.getRequestURL() + " istegine karsilik karsilayici bulunamadi. " +
                "Hata mesaji: " + e.getMessage());
        model.addAttribute("message", "Hay aksi! Stajyerimiz yine bir şeyleri bozdu anlaşılan. Birazdan döneriz.");
        return "error";
    }
}
