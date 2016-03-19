package ar.fiuba.tdd.template.tp0.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RUITZEI on 19/03/2016.
 */
public class Validator {

    private Patterns patterns;

    public Validator() {
        this.patterns = new Patterns();
    }

    // We need to check if the regular expression we receive has special characters, such as . [ ] +, etc
    // because this will need extra attention to be able to create strings from it
    public boolean hasSpecialCharacters(String regEx) {
        return (regEx.matches(patterns.getSpecialCharsPattern()));
    }

    // We might be in presence of special characters, but we should ignore them if they are escaped.
    public boolean hasSpecialCharactersEscaped(String regEx) {
        return (regEx.matches(patterns.getSpecialCharsEscappedPattern()));
    }


    // Watch out! Matcher starts looking from end to start!
    public String getLastEscapedSpecialCharacter(String regEx) {
        return getLastSpecialCharacterAbstract(regEx, patterns.getSpecialCharsEscapedCapturePattern());
    }

    public String getLastSpecialCharacter(String regEx) {
        return getLastSpecialCharacterAbstract(regEx, patterns.getSpecialCharsCapturePattern());
    }

    public String getLastSpecialCharacterAbstract(String regEx, String patternAux) {
        String escapedCharacter = "";
        Pattern pattern = Pattern.compile(patternAux);

        Matcher matcher = pattern.matcher(regEx);
        while (matcher.find()) {
            escapedCharacter = matcher.group(1);
        }

        return escapedCharacter;
    }

    public boolean hasAllSpecialCharactersEscaped(String regEx) {

        if (!hasSpecialCharacters(regEx)) {
            return false;
        }

        return getSpecialCharacterCount(regEx) == getSpecialCharactersEscapedCount(regEx);
    }

    public int getSpecialCharactersEscapedCount(String regEx) {
        String subRegEx = regEx;
        int specialCharCount = 0;

        while (hasSpecialCharactersEscaped(subRegEx)) {
            specialCharCount++;

            String lastEscapedChar = getLastEscapedSpecialCharacter(subRegEx);

            int lastIndexOf = subRegEx.lastIndexOf(lastEscapedChar);
            subRegEx = subRegEx.substring(0, lastIndexOf);
        }

        return specialCharCount;
    }

    public int getSpecialCharacterCount(String regEx) {
        if (!hasSpecialCharacters(regEx)) {
            return 0;
        }

        String subRegEx = regEx;
        int specialCharCount = 0;

        while (hasSpecialCharacters(subRegEx)) {
            specialCharCount++;

            String lastEscapedChar = getLastSpecialCharacter(subRegEx);

            int lastIndexOf = subRegEx.lastIndexOf(lastEscapedChar) ;
            subRegEx = subRegEx.substring(0, lastIndexOf);
        }

        return specialCharCount;
    }
}
