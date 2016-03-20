package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.BracketsDelegate;
import ar.fiuba.tdd.template.tp0.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {

    private int maxLength;
    private Validator validator;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
        this.validator = new Validator();
    }

    // TODO: Uncomment parameters
    public List<String> generate(String regEx, int numberOfResults) {
        if (numberOfResults < maxLength) {
            System.out.println("Number of results: " + numberOfResults);
        }

        String generatedString = getRegExWithBackSlashesRemoved(regEx);
        BracketsDelegate bracketsDelegate = new BracketsDelegate();
        generatedString = bracketsDelegate.handleBrackets(generatedString);
        generatedString = getRegExWithBackSlashesRemoved(generatedString);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(generatedString);

        return arrayList;
    }

    private String getRegExWithBackSlashesRemoved(String regExp) {
        if (shouldRemoveBackSlashes(regExp)) {
            return regExp.replaceAll("\\\\", "");
        }

        return regExp;
    }

    private boolean shouldRemoveBackSlashes(String regExp) {
        System.out.println(regExp);
        // no special characters or every spacial character escaped.
        return (validator.hasAllSpecialCharactersEscaped(regExp)
                || !validator.hasSpecialCharacters(regExp));
    }
}