package ar.fiuba.tdd.template.tp0;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static ar.fiuba.tdd.template.tp0.utils.CharUtils.getUnescapedCharacterPosition;
import static org.junit.Assert.assertEquals;

public class CharUtilsTest {

    @Test
    public void testGetUnescapedCharacterPosition() {
        assertEquals(3, getUnescapedCharacterPosition("asd[", '['));
        assertEquals(3, getUnescapedCharacterPosition("asd]", ']'));
        assertEquals(3, getUnescapedCharacterPosition("asd[asdasd[asdas]", '['));
        assertEquals(16, getUnescapedCharacterPosition("asd[asdasd[asdas]]", ']'));
        assertEquals(11, getUnescapedCharacterPosition("asd" + "\\" + "[asdasd[asdas]]", '['));
        assertEquals(13, getUnescapedCharacterPosition("asd" + "\\" + "[asd\\[asd[asdas]]", '['));
        assertEquals(8, getUnescapedCharacterPosition("asd\\[asd[asd[asdas]]", '['));
        assertEquals(8, getUnescapedCharacterPosition("\\[Johhny[asd]*", '['));
    }

    @Test
    public void testShouldReturnMinusOneIfNoCharsWereFound() {
        assertEquals(-1, getUnescapedCharacterPosition("asd[", '*'));
        assertEquals(-1, getUnescapedCharacterPosition("asd[Johnny", '*'));
    }
}
