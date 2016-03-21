package ar.fiuba.tdd.template.tp0.delegates;

import ar.fiuba.tdd.template.tp0.utils.CharUtils;

import static ar.fiuba.tdd.template.tp0.utils.CharUtils.getCharacterPosition;

/**
 * Created by RUITZEI on 20/03/2016.
 */
public class QuantifiersDelegate {

    // I'm assuming that regex strings shoul be as short as possible/
    public String handle(String regExp) {
        regExp = replaceUnescapedQuantifier(regExp, '+');
        regExp = replaceUnescapedQuantifier(regExp, '*');
        regExp = replaceUnescapedQuantifier(regExp, '?');

        return regExp;
    }

    public String replaceUnescapedQuantifier(String regExp, char character) {
        int index = CharUtils.getCharacterPosition(regExp, character);

        while (index > 0) {
            StringBuilder sb = new StringBuilder(regExp);
            sb.deleteCharAt(index);
            regExp = sb.toString();

            System.out.println("Removed one " + character);
            index = CharUtils.getCharacterPosition(regExp, character);
        }

        return regExp;
    }
}
