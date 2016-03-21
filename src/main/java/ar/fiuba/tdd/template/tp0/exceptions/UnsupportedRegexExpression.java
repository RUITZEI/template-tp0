package ar.fiuba.tdd.template.tp0.exceptions;

/**
 * Created by RUITZEI on 19/03/2016.
 */
public class UnsupportedRegexExpression extends RuntimeException {

    private static final long serialVersionUID = -1234L;

    public UnsupportedRegexExpression(String msg) {
        super(msg);
    }
}
