package com.yao.app.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    public static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");

    public static boolean isValidEmail(String email) {
        Matcher m = PATTERN_EMAIL.matcher(email);
        return m.matches();
    }

}
