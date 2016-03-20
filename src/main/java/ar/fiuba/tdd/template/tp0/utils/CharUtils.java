package ar.fiuba.tdd.template.tp0.utils;

/**
 * Created by RUITZEI on 19/03/2016.
 */
public abstract class CharUtils {
//
//    private static final String PREFIX = ".*";
//    private static final String POSTFIX = ".*";
//
//    public static boolean hasEscapedChar(String charToFind) {
//        String pattern = getPattern("\\" + charToFind);
//        return hasCharAbstract(charToFind, pattern);
//    }
//
//    public static int getFirstPositionOf(char character, String regEx){
//        return 1;
//    }
//
//    public static boolean hasChar(String charToFind) {
//        String pattern = getPattern(charToFind);
//        return hasCharAbstract(charToFind, pattern);
//    }
//
//    private static boolean hasCharAbstract(String charToFind, String pattern) {
//        return true;
//    }
//
//    private static String getPattern(String charToFind) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(PREFIX);
//        sb.append(charToFind);
//        sb.append(POSTFIX);
//
//        return sb.toString();
//    }

    // We search for the character in the regex, but making sure it's not escaped
    // and return it's position on the string.
    // Greedy.
    public static int getCharacterPosition(String regEx, char character) {
        boolean found = false;
        String regExaux = regEx;
        int realPosition = 0;

        while (!found) {
            int relativePosition = regExaux.indexOf(character);
            realPosition += relativePosition;

            boolean haveEscapedSlash = relativePosition > 0 && '\\' == (regExaux.charAt(relativePosition - 1));
            //should return when index is valid and the character before was not an escape.
            boolean shouldReturn = (relativePosition > 0 && '\\' != (regExaux.charAt(relativePosition - 1))
                    || (relativePosition == 0));

            if (haveEscapedSlash) {
                realPosition++;
            }

            if (shouldReturn || relativePosition < 0) {
                return getReturningValue(realPosition, relativePosition);
            }

            regExaux = regExaux.substring(relativePosition + 1, regExaux.length());
        }

        return -1;
    }

    private static int getReturningValue(int realPosition, int relativePosition) {
        return relativePosition < 0 ? relativePosition : realPosition;
    }
}
