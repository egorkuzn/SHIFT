package ru.cft.clorental.model;

import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidPassword(String password) {
        return true;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        String regex = "^[А-Я][а-я]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(name).matches();
    }

    public static boolean isValidSurname(String surname) {
        String regex = "^[А-Я][а-я]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(surname).matches();
    }

    public static boolean isValidPhone(String phone) {
        String regex = "^\\+7\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }
}
