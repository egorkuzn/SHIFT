package ru.cft.clorental.model;
import ru.cft.clorental.model.request_forms.NewCardForm;

import java.util.ArrayList;
import java.util.Random;
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
        String regex = "^\\+7\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }

    static ArrayList<Character> list = new ArrayList<>();
    static Random random = new Random();
    static int LENGTH = 12;
    public static String stringRand() {
        if(list.size() == 0)
            genList();

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < LENGTH; i++)
            stringBuilder.append(randChar());

        return stringBuilder.toString();
    }

    private static char randChar(){
        return list.get(random.nextInt(list.size()));
    }

    private static void genList() {
        for(char i = '!'; i <'z'; i++)
            list.add(i);
    }

    public static boolean newCardFormValid(NewCardForm form) {
//        return isValidID(form.userID) &&
//        isValidCategory(form.category) &&
//        isValidDescription(form.description) &&
//        form.price;
//        form.term;
//        form.title;
        return true;
    }
}
