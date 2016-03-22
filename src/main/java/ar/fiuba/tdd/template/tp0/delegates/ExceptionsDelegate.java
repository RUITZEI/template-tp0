package ar.fiuba.tdd.template.tp0.delegates;

import ar.fiuba.tdd.template.tp0.exceptions.UnsupportedRegexExpression;
import ar.fiuba.tdd.template.tp0.utils.CharUtils;

/**
 * Created by RUITZEI on 21/03/2016.
 */
public class ExceptionsDelegate {

    public String handle(String regExp, int maxLength) {
        int openingIndex = CharUtils.getUnescapedCharacterPosition(regExp, '(');
        int closingIndex = CharUtils.getUnescapedCharacterPosition(regExp, ')');

        if (openingIndex >= 0 || closingIndex >= 0) {
            throw new UnsupportedRegexExpression("Can't have parenthesis ");
        }

        if (regExp.length() > maxLength) {
            throw new UnsupportedRegexExpression("Regexp length cant be that long");
        }

        return regExp;
    }
}
