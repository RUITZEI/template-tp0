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

    private ArrayList<String> specialChars;


    /**
     * You should add all the special characters here
     */
    private ArrayList<String> getSpecialChars(){
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

    public String getSpecialCharsPattern(){
        StringBuilder sb = new StringBuilder();
        sb.append(".*[");
        for (String specialChar : specialChars){
            sb.append("\\" + specialChar);
        }
        sb.append("].*");

        return sb.toString();
    }

    public String getSpecialCharsEscappedPattern(){
        StringBuilder sb = new StringBuilder();
        sb.append(".*\\\\[");
        for (String specialChar : specialChars){
            sb.append("\\" + specialChar);
        }
        sb.append("].*");

        return sb.toString();
    }

    public String getSpecialCharsEscapedCapturePattern(){
        StringBuilder sb = new StringBuilder();
        sb.append(".*\\\\([");
        for (String specialChar : specialChars){
            sb.append("\\" + specialChar);
        }
        sb.append("]).*");

        return sb.toString();
    }

    public String getSpecialCharsCapturePattern(){
        StringBuilder sb = new StringBuilder();
        sb.append(".*([");
        for (String specialChar : specialChars){
            sb.append("\\" + specialChar);
        }
        sb.append("]).*");

        return sb.toString();
    }

    public ArrayList<String> getSpecialCharacters(){
        return this.specialChars;
    }

}
