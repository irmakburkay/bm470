package tr.edu.duzce.mf.bm.bm470.util;

public class BlogValidation {
    public static boolean isTitleValid(String title){
        return title.length() > 0 && title.length() <= 45;
    }
    public static boolean isContentValid(String content){
        return content.length() > 0 && content.length() <= 450;
    }
}
