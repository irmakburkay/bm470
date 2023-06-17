package tr.edu.duzce.mf.bm.bm470.util;

public class UserValidation {
    public static boolean isUsernameValid(String username){
        if(username.length() < 3 && username.length() > 40){
            return false;
        }
        return true;
    }
}
