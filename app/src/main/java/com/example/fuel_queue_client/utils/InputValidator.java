package com.example.fuel_queue_client.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean emailValidate(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);

        return emailMatcher.find();
    }

    public static boolean usernameValidate(String username) {
        String usernameRegex = "^[aA-zZ0-9_-]\\w{5,30}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(username);

        return usernameMatcher.matches();
    }
}
