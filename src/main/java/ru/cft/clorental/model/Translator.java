package ru.cft.clorental.model;

import java.util.HashMap;

public class Translator {
    private static boolean firstTime = true;
    private static final HashMap<String, String> translator = new HashMap<>();
    public static String get(String category) {
        if(firstTime) {
            create();
            firstTime = false;
        }

        return translator.get(category);
    }

    static void create(){
        translator.put("appliances", "Бытовая техника");
        translator.put("recreation", "Отдых");
        translator.put("home", "Всё для дома");
        translator.put("clothes", "Одежда");
        translator.put("sport", "Спорт");
        translator.put("hobby", "Отдых и хобби");
        translator.put("things", "Личные вещи");
        translator.put("xxx", "18+");
        translator.put("dacha", "Для дома и дачи");
        translator.put("games", "Компьютерные игры");
    }
}
