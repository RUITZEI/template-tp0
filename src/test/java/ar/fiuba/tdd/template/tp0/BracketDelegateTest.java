package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.BracketsDelegate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BracketDelegateTest {

    BracketsDelegate delegate = new BracketsDelegate();

    @Test
    public void testShouldRemoveBracketSentenceAfterHandle() {
        String stringWithBrackets = "[asd]hey";
        assertEquals(-1, delegate.handleBrackets(stringWithBrackets).indexOf("[asd]"));
    }

    @Test
    public void shouldDoNothingWithStringWithNoBrackets() {
        String stringWithoutBrackets = "[asd\\]hey";
        assertEquals(stringWithoutBrackets, delegate.handleBrackets(stringWithoutBrackets));
    }
}
