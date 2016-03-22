package ar.fiuba.tdd.template.tp0.utils;

import java.util.Random;

/**
 * Created by RUITZEI on 19/03/2016.
 */
public abstract class RandomChar {
    public static char getRandomChar() {
        Random rand = new Random();
        int randomInt = rand.nextInt(127) + 32;
        char character = (char)(randomInt);
        return character;
    }

    public static char getRandomCharFromString(String string) {
        Random rand = new Random();
        int stringLength = string.length();
        if (stringLength == 0) {
            return '\0';
        }
        int pos = rand.nextInt(string.length());

        return string.charAt(pos);
    }
}
