package ar.fiuba.tdd.template.tp0.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RUITZEI on 19/03/2016.
 */
public class Patterns {

    private static final String SPECIAL_CHARS_CAPTURE_ESCAPED_PATTERN = ".*\\\\([\\.\\*\\+\\?\\[\\]]).*";
    private static final String SPECIAL_CHARS_ESCAPED_PATTERN = ".*\\\\[\\.\\*\\+\\?\\[\\]].*";
    private static final String SPECIAL_CHARS_PATTERN = ".*[\\.\\*\\+\\?\\[\\]].*";
    private static final String SPECIAL_CHARS_CAPTURE_PATTERN = ".*([\\.\\*\\+\\?\\[\\]]).*";

    private static final String SPECIAL_PREFIX = ".*[";
    private static final String SPECIAL_POSTFIX = "].*";

    private static final String ESCAPED_SPECIAL_PREFIX = ".*\\\\[";

    private static final String CAPTURE_ESCAPED_SPECIAL_PREFIX = ".*\\\\([";
    private static final String CAPTURE_POSTFIX = "]).*";

    private static final String CAPTURE_SPECIAL_PREFIX = ".*([";

    private ArrayList<String> specialChars;

    // Should add all the special chars here.
    private ArrayList<String> getSpecialChars() {
        return new ArrayList<String>() {
            {
                add("*");
                add(".");
                add("+");
                add("[");
                add("]");
                add("?");
            }
        };
    }

    public Patterns() {
        this.specialChars = getSpecialChars();
    }

    public String getSpecialCharsPattern() {
        return getAbstractPattern(SPECIAL_PREFIX, SPECIAL_POSTFIX);
    }

    public String getSpecialCharsEscappedPattern() {
        return getAbstractPattern(ESCAPED_SPECIAL_PREFIX, SPECIAL_POSTFIX);
    }

    public String getSpecialCharsEscapedCapturePattern() {
        return getAbstractPattern(CAPTURE_ESCAPED_SPECIAL_PREFIX, CAPTURE_POSTFIX);
    }

    public String getAbstractPattern(String prefix, String postfix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (String specialChar : specialChars) {
            sb.append("\\" + specialChar);
        }
        sb.append(postfix);

        return sb.toString();
    }

    public String getSpecialCharsCapturePattern() {
        return getAbstractPattern(CAPTURE_SPECIAL_PREFIX, CAPTURE_POSTFIX);
    }

    public ArrayList<String> getSpecialCharacters() {
        return this.specialChars;
    }

}
