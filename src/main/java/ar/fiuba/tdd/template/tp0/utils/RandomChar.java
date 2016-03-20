package ar.fiuba.tdd.template.tp0.utils;

import java.util.Random;

/**
 * Created by RUITZEI on 19/03/2016.
 */
public abstract class RandomChar {
    public static char getRandomChar() {
        Random rand = new Random();
        char character = (char)(rand.nextInt(255) + 'a');

        System.out.println("Char: " + character);
        return character;
    }

    public static char getRandomCharFromString(String string) {
        Random rand = new Random();
        int stringLength = string.length();
        if (stringLength == 0) {
            return '\0';
        }
        int pos = rand.nextInt(string.length());
        System.out.println("Char: " + string.charAt(pos));

        return string.charAt(pos);
    }
}
