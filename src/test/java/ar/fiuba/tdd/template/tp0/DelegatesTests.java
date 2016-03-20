package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.delegates.BracketsDelegate;
import ar.fiuba.tdd.template.tp0.utils.CharUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static ar.fiuba.tdd.template.tp0.utils.CharUtils.getCharacterPosition;
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
        assertEquals(3, getCharacterPosition("asd[", '['));
        assertEquals(3, getCharacterPosition("asd]", ']'));
        assertEquals(-1, getCharacterPosition("asd[", '*'));
        assertEquals(3, getCharacterPosition("asd[asdasd[asdas]", '['));
        assertEquals(16, getCharacterPosition("asd[asdasd[asdas]]", ']'));
        assertEquals(11, getCharacterPosition("asd" + "\\" + "[asdasd[asdas]]", '['));
        assertEquals(13, getCharacterPosition("asd" + "\\" + "[asd\\[asd[asdas]]", '['));
        assertEquals(8, getCharacterPosition("asd\\[asd[asd[asdas]]", '['));
        assertEquals(-1, getCharacterPosition("asd" + "\\" + "[asd\\[asd\\[asdas]]", '['));
        assertEquals(8, getCharacterPosition("\\[Johhny[asd]*", '['));
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
