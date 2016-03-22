package ar.fiuba.tdd.template.tp0.delegates;

import ar.fiuba.tdd.template.tp0.utils.CharUtils;
import ar.fiuba.tdd.template.tp0.utils.RandomChar;

import java.util.Random;

import static ar.fiuba.tdd.template.tp0.utils.CharUtils.getUnescapedCharacterPosition;

/**
 * Created by RUITZEI on 20/03/2016.
 */
public class DotDelegate {

    private static final Integer NUMBER = 5;

    private Random random;

    public DotDelegate() {
        this.random = new Random();
    }

    public String handleDot(String regExp) {
        boolean shouldKeepGoing = canHandle(regExp);

        while (shouldKeepGoing) {
            int position = getUnescapedCharacterPosition(regExp, '.');
            boolean mightHaveQuantifier = (position >= 0) && (position < regExp.length() - 1);

            if (mightHaveQuantifier) {
                regExp = handleQuantifier(regExp, position);
                shouldKeepGoing = canHandle(regExp);
            } else {
                regExp = handleNoQuantifier(regExp, position);
                shouldKeepGoing = false;
            }
        }
        return regExp;
    }

    private boolean canHandle(String regExp) {
        int position = CharUtils.getUnescapedCharacterPosition(regExp, '.');
        return (position >= 0 );
    }

    private String handleQuantifier(String regExp, int position) {
        char quantifier = regExp.charAt(position + 1);

        if (quantifier == '*') {
            return handleAsterisk(regExp, position);
        } else if (quantifier == '+') {
            return handlePlus(regExp, position);
        }

        return handleNoQuantifier(regExp, position);

    }

    private String handleNoQuantifier(String regexp, int position) {
        return regexp.substring(0, position) + RandomChar.getRandomChar() + regexp.substring( position + 1);
    }

    private String handleAsterisk(String regExp, int position) {
        return abstractHandle(regExp, position, 1);
    }

    private String handlePlus(String regExp, int position) {
        // don't wanna get a 0 here.
        return abstractHandle(regExp, position, random.nextInt(NUMBER) + 1);
    }

    private String abstractHandle(String regExp, int position, int times) {
        // Getting the dot + quantifier.
        String charactersToAdd = getCharactersToAdd(times);
        String returning = regExp.substring(0,position) + charactersToAdd + regExp.substring(position + 2);
        return returning;
    }

    private String getCharactersToAdd(int times) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < times; i++) {
            buf.append(RandomChar.getRandomChar());
        }

        return buf.toString();
    }
}
