package ar.fiuba.tdd.template.tp0.delegates;

/**
 * Created by RUITZEI on 21/03/2016.
 */
public class DelegatesManager {

    private BracketsDelegate bracketsDelegate;
    private DotDelegate dotDelegate;
    private QuantifiersDelegate quantifiersDelegate;

    public DelegatesManager() {
        this.bracketsDelegate = new BracketsDelegate();
        this.dotDelegate = new DotDelegate();
        this.quantifiersDelegate = new QuantifiersDelegate();
    }

    public String handleRegularExpression(String regExp) {
        String handledRegex = bracketsDelegate.handleBrackets(regExp);
        handledRegex = dotDelegate.handleDot(handledRegex);
        handledRegex = quantifiersDelegate.handle(handledRegex);

        return handledRegex;
    }
}
