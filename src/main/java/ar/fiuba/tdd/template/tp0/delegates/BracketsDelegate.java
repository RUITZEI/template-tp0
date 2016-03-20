package ar.fiuba.tdd.template.tp0.delegates;

import ar.fiuba.tdd.template.tp0.exceptions.BracketContainsSpecialCharsException;
import ar.fiuba.tdd.template.tp0.utils.CharUtils;
import ar.fiuba.tdd.template.tp0.utils.RandomChar;
import ar.fiuba.tdd.template.tp0.validator.Validator;

import static ar.fiuba.tdd.template.tp0.utils.CharUtils.getCharacterPosition;

/**
 *  [...] means that we've got to pick any of the characters inside it and replace the whole thing
 *  for that char:
 *  eg:  [abcde] -> a
 *       [abcde] -> b..e
 */
public class BracketsDelegate {

    public String handleBrackets(String regExp) {
        boolean shouldKeepGoing = canHandle(regExp);

        while (shouldKeepGoing) {
            try {
                String onBracketsString = getFirstStringBetweenBrackets(regExp);

                if (mightHaveQuantifier(regExp, onBracketsString)) {
                    regExp = handleQuantifier(regExp, onBracketsString);
                    shouldKeepGoing = canHandle(regExp);
                } else {
                    regExp = handleNoQuantifier(regExp, onBracketsString);
                    shouldKeepGoing = false;
                }

            } catch (BracketContainsSpecialCharsException e) {
                shouldKeepGoing = false;
                e.printStackTrace();
            }
        }

        return regExp;
    }

    // We're able to handle the regexp if and only if there are two non-escaped brackets
    public boolean canHandle(String regExp) {
        int openingBracketIndex = getCharacterPosition(regExp, '[');
        int closingBracketIndex = getCharacterPosition(regExp, ']');

        return (openingBracketIndex >= 0 && closingBracketIndex > 0)
                && (closingBracketIndex > openingBracketIndex);
    }

    // We should always keep it minimum -> asteriscs should become a 0x
    public String handleQuantifier(String regExp, String bracketSentence) {
        char quantifier = getQuantifier(regExp, stringWithbrackets(bracketSentence));

        if (quantifier == '*') {
            return handleAsterisk(regExp, bracketSentence);
        } else if (quantifier == '+') {
            return handleAsterisk(regExp, bracketSentence);
        }

        return replaceBracketSentenceWithChar(regExp, bracketSentence);
    }

    public String handleAsterisk(String regExp, String bracketSentence) {
        StringBuilder sb = new StringBuilder(regExp);
        sb.deleteCharAt(getExtraBracketSentenceCharPosition(regExp, stringWithbrackets(bracketSentence)) - 1);
        regExp = sb.toString();
        return replaceBracketSentenceWithChar(regExp, bracketSentence);
    }

    public String deleteCharAt(String regExp, int position) {
        StringBuilder sb = new StringBuilder(regExp);
        sb.deleteCharAt(position);
        return sb.toString();
    }

    public String replaceBracketSentenceWithChar(String regExp, String bracketSentence) {
        char character = RandomChar.getRandomCharFromString(bracketSentence);
        String regex = replace(regExp,
                stringWithbrackets(bracketSentence),
                Character.toString(RandomChar.getRandomCharFromString(bracketSentence)));
        if (character == '\0') {
            return deleteCharAt(regex, regex.indexOf(character));
        }

        return regex;
    }

    // the String.replace() would replace all of the occurences.
    public String replace(String regex, String strinWithBrackets, String character) {
        int index = regex.indexOf(strinWithBrackets);
        String returning = regex.substring(0, index) + character + regex.substring(index + strinWithBrackets.length());
        return returning;
    }


    public String handleNoQuantifier(String regExp, String bracketSentence) {
        return replace(regExp,
                stringWithbrackets(bracketSentence),
                Character.toString(RandomChar.getRandomCharFromString(bracketSentence)));
    }

    public String stringWithbrackets(String bracketSentence) {
        return "[" + bracketSentence + "]";
    }


    public String getFirstStringBetweenBrackets(String regExp) throws BracketContainsSpecialCharsException {
        int openingBracketIndex = getCharacterPosition(regExp, '[');
        int closingBracketIndex = getCharacterPosition(regExp, ']');

        String betweenBrackets = regExp.substring(openingBracketIndex + 1, closingBracketIndex);
        System.out.println(betweenBrackets);

        Validator validator = new Validator();

        if (validator.hasSpecialCharacters(betweenBrackets)) {
            throw new BracketContainsSpecialCharsException("Can't have special characters between two brackets");
        }

        return betweenBrackets;
    }

    // Should receive the sentence with the brackets!
    public char getQuantifier(String regExp, String bracketSentence) {
        return regExp.charAt(getExtraBracketSentenceCharPosition(regExp, bracketSentence) - 1);
    }

    // Might have quantifier when there's a char proceding the sentence
    public boolean mightHaveQuantifier(String regExp, String bracketSentence) {
        return ( getExtraBracketSentenceCharPosition(regExp, stringWithbrackets(bracketSentence)) - 1 ) > 0;
    }


    // Should receive the sentence with the brackets!
    public int getExtraBracketSentenceCharPosition(String regExp, String bracketSentence) {
        int bracketStart = regExp.indexOf(bracketSentence);

        int returningIndex = bracketStart + bracketSentence.length() + 1;
        if (returningIndex > regExp.length()) {
            return -1;
        }
        return returningIndex;
    }

    private int getReturningValue(int realPosition, int relativePosition) {
        return relativePosition < 0 ? relativePosition : realPosition;
    }
}
