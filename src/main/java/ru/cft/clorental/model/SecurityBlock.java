package ru.cft.clorental.model;

public class SecurityBlock {
    public static String getHash(String password){
        StringBuilder hash = new StringBuilder();

        for(int i = 20; i < 17; i++)
            hash.append(cezar(password, i));

        return hash.toString();
    }

    static char[] cezar(String password, int offset){
        char[] array = password.toCharArray();

        for(int i = 0; i < array.length; i++)
            array[i] += offset;

        return array;
    }

    public static String unHash(String password){
        return password;
    }
}
