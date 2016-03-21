package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.QuantifiersDelegate;
import ar.fiuba.tdd.template.tp0.utils.CharUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class QuantifierDelegateTest {

    QuantifiersDelegate quantifiersDelegate = new QuantifiersDelegate();

    @Test
    public void testShouldRemoveQuantifiersAfterHandle() {
        String stringWithQuantifiers = "Hola*Como+Estas?";
        String handledString = quantifiersDelegate.handle(stringWithQuantifiers);
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(handledString, '+'));
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(handledString, '*'));
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(handledString, '?'));
    }

    @Test
    public void testShouldDoNothingWithEscapedQuantifiers() {
        String stringWithEscapedQuantifiers = "Hola\\*Como\\+Estas\\?";
        String handledString = quantifiersDelegate.handle(stringWithEscapedQuantifiers);

        assertEquals(stringWithEscapedQuantifiers, handledString);
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(handledString, '+'));
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(handledString, '*'));
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(handledString, '?'));
    }
}
