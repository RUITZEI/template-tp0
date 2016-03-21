package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.DotDelegate;
import ar.fiuba.tdd.template.tp0.utils.CharUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DotDelegateTest {

    DotDelegate delegate = new DotDelegate();

    @Test
    public void testShouldReturnSameStringIfCantHandle() {
        assertEquals("HelloMyNameIsJohn*", delegate.handleDot("HelloMyNameIsJohn*"));
        assertEquals("Hello\\.MyNameIs\\.John*", delegate.handleDot("Hello\\.MyNameIs\\.John*"));
    }

    @Test
    public void shouldReturnDifferentStringIfCanHandle() {
        assertNotEquals("Hello.My.NameIsJohn*", delegate.handleDot("Hello.My.NameIsJohn*"));
        assertNotEquals("Hello\\.MyNameIs.John*", delegate.handleDot("Hello\\.MyNameIs.John*"));
    }

    @Test
    public void testShouldReturnSameLengthStringIfCanHandle() {
        String string = "Hey.Hola.Halo.";
        assertEquals(string.length(), delegate.handleDot(string).length());
    }

    @Test
    public void testShouldHaveNoUnescapedDotsAfterHandle() {
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(delegate.handleDot("Hola.Hey.La"), '.'));
        assertEquals(-1, CharUtils.getUnescapedCharacterPosition(delegate.handleDot("Hola\\.Hey.La"), '.'));
    }

}
