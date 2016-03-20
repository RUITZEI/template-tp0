package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.BracketsDelegate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DelegatesTests {
    BracketsDelegate bracketsDelegate = new BracketsDelegate();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testHandleBrackets() {
//        assertEquals("asd",bracketsDelegate.handleBrackets("[asd]"));
        System.out.println("Before strange");
//        assertEquals("asd",bracketsDelegate.handleBrackets("asd\\*[asd]*[12\\[3d]"));
        System.out.println("After strange");
//        assertEquals("asd",bracketsDelegate.handleBrackets("[asd\\*[asd]]."));
    }

    @Test
    public void testCharacterFinder() {
        assertEquals(3, bracketsDelegate.getCharacterPosition("asd[", '['));
        assertEquals(3, bracketsDelegate.getCharacterPosition("asd]", ']'));
        assertEquals(-1, bracketsDelegate.getCharacterPosition("asd[", '*'));
        assertEquals(3, bracketsDelegate.getCharacterPosition("asd[asdasd[asdas]", '['));
        assertEquals(16, bracketsDelegate.getCharacterPosition("asd[asdasd[asdas]]", ']'));
        assertEquals(11, bracketsDelegate.getCharacterPosition("asd" + "\\" + "[asdasd[asdas]]", '['));
        assertEquals(13, bracketsDelegate.getCharacterPosition("asd" + "\\" + "[asd\\[asd[asdas]]", '['));
        assertEquals(8, bracketsDelegate.getCharacterPosition("asd\\[asd[asd[asdas]]", '['));
        assertEquals(-1, bracketsDelegate.getCharacterPosition("asd" + "\\" + "[asd\\[asd\\[asdas]]", '['));
        assertEquals(8, bracketsDelegate.getCharacterPosition("\\[Johhny[asd]*", '['));
    }

    @Test
    public void testGetQuantifier() {
        assertEquals('*', bracketsDelegate.getQuantifier("asd[asd]*a", "[asd]"));
        assertEquals('*', bracketsDelegate.getQuantifier("asd[asd]*a", "[asd]"));
    }

    @Test
    public void testExtraCharacterBracketPosition() {
        assertEquals(9, bracketsDelegate.getExtraBracketSentenceCharPosition("asd[asd]*a", "[asd]"));
        assertEquals(-1, bracketsDelegate.getExtraBracketSentenceCharPosition("asd[asd]","[asd]"));
    }
}
