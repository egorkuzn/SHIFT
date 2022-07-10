package ru.cft.clorental.model;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class Validator {
    public static boolean isValidPassword(String password) {
        return true;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(email).matches()) {
            log.info("yes");
            return true;
        }
        else {
            log.info("no");
            return false;
        }
    }

    public static boolean isValidName(String name) {
        String regex = "^[А-Я][а-я]+$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(name).matches()) {
            log.info("yes");
            return true;
        }
        else {
            log.info("no");
            return false;
        }
    }

    public static boolean isValidSurname(String surname) {
        String regex = "^[А-Я][а-я]+$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(surname).matches()) {
            log.info("yes");
            return true;
        }
        else {
            log.info("no");
            return false;
        }
    }

    public static boolean isValidPhone(String phone) {
        String regex = "^\\+7\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(phone).matches()) {
            log.info("yes");
            return true;
        }
        else {
            log.info("no");
            return false;
        }
    }
}
