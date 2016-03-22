package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.*;
import ar.fiuba.tdd.template.tp0.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {

    private int maxLength;
    private Validator validator;
    private DelegatesManager delegatesManager;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
        this.validator = new Validator();
        this.delegatesManager = new DelegatesManager();
    }


    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < numberOfResults; i++) {
            String generatedString = getHandledString(regEx);
            arrayList.add(generatedString);
        }
        return arrayList;
    }

    private String getHandledString(String regEx) {
        String generatedString = regEx;

        ExceptionsDelegate exceptionsDelegate = new ExceptionsDelegate();
        exceptionsDelegate.handle(regEx, maxLength);

        if (shouldRemoveBackSlashes(regEx)) {
            return getRegExWithBackSlashesRemoved(regEx);
        }

        generatedString = delegatesManager.handleRegularExpression(generatedString);

        if (shouldRemoveBackSlashes(generatedString)) {
            generatedString = getRegExWithBackSlashesRemoved(generatedString);
        }

        return generatedString;
    }

    private String getRegExWithBackSlashesRemoved(String regExp) {
        return regExp.replaceAll("\\\\", "");
    }

    private boolean shouldRemoveBackSlashes(String regExp) {
        // no special characters or every spacial character escaped.
        return (validator.hasAllSpecialCharactersEscaped(regExp)
                || !validator.hasSpecialCharacters(regExp));
    }
}