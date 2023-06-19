package tr.edu.duzce.mf.bm.bm470.util;

import java.util.regex.Pattern;

public class UserValidation {

    // RFC 5322 for Email Validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static boolean isUsernameValid(String username){
        return username.length() >= 3 && username.length() <= 40;
    }

    public static boolean isPasswordValid(String password){
        return password.length() >= 8;
    }

    public static boolean isEmailValid(String email){
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean doesPasswordsMatch(String pass1, String pass2){
        return pass1.equals(pass2);
    }
}
