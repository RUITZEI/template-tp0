package ar.fiuba.tdd.template.tp0.delegates;

import ar.fiuba.tdd.template.tp0.utils.CharUtils;
import ar.fiuba.tdd.template.tp0.utils.RandomChar;

import static ar.fiuba.tdd.template.tp0.utils.CharUtils.getCharacterPosition;

/**
 * Created by RUITZEI on 20/03/2016.
 */
public class DotDelegate {

    private static final Integer NUMBER = 5;

    public String handleDot(String regExp) {
        boolean shouldKeepGoing = canHandle(regExp);

        while (shouldKeepGoing) {
            System.out.println("Should keep going");
            int position = getCharacterPosition(regExp, '.');
            boolean mightHaveQuantifier = (position > 0) && (position < regExp.length() - 1);

            if (mightHaveQuantifier) {
                System.out.println("Might have quantifier");

                regExp = handleQuantifier(regExp, position);
                shouldKeepGoing = canHandle(regExp);
            } else {
                regExp = handleNoQuantifier(regExp, position);
                shouldKeepGoing = false;
            }
        }
        return regExp;
    }

    public boolean canHandle(String regExp) {
        int position = CharUtils.getCharacterPosition(regExp, '.');
        return (position >= 0 );
    }

    public String handleQuantifier(String regExp, int position) {
        char quantifier = regExp.charAt(position + 1);

        if (quantifier == '*') {
            return handleAsterisk(regExp, position);
        } else if (quantifier == '+') {
            return handlePlus(regExp, position);
        }

        System.out.println("had no quantifier");
        return handleNoQuantifier(regExp, position);

    }

    public String handleNoQuantifier(String regexp, int position) {
        return regexp.substring(0, position) + RandomChar.getRandomChar() + regexp.substring( position + 1);
    }

    public String handleAsterisk(String regExp, int position) {
        return abstractHandle(regExp, position, 1);
    }

    public String handlePlus(String regExp, int position) {
        return abstractHandle(regExp, position, NUMBER);
    }

    public String abstractHandle(String regExp, int position, int times) {
        // Getting the dot + quantifier.
        String subString = regExp.substring(position, position + 1);
        System.out.println("handle substring: " + subString);
        //TODO :
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
