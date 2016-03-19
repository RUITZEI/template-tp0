package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.validator.Validator;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by RUITZEI on 19/03/2016.
 */
public class ValidatorTest {


    private Validator validator = new Validator();

    @Test
    public void testShouldReturnTrueIfSpecialCharactersAreFound() {
        assertTrue(validator.hasSpecialCharacters("asd.123"));
        assertTrue(validator.hasSpecialCharacters("?"));
        assertTrue(validator.hasSpecialCharacters("[asd]"));
        assertTrue(validator.hasSpecialCharacters("[as.]"));
        assertTrue(validator.hasSpecialCharacters(".123"));
        assertTrue(validator.hasSpecialCharacters(".12[-3+"));
    }

    @Test
    public void testShouldReturnFalseIfNoSpecialCharactersAreFound() {
        assertFalse(validator.hasSpecialCharacters("Heywhatsup"));
        assertFalse(validator.hasSpecialCharacters("Hey man!"));
    }

    @Test
    public void testShouldReturnTrueIfSpecialCharactersAreEscaped() {
        assertTrue(validator.hasSpecialCharactersEscaped("Hello \\*my friend"));
        assertTrue(validator.hasSpecialCharactersEscaped("Hello \\+my friend"));
        assertTrue(validator.hasSpecialCharactersEscaped("\\[]Hello my friend"));
        assertFalse(validator.hasSpecialCharactersEscaped("\\@"));
    }


    @Test
    public void testHasAllSpecialCharactersEscaped() {
        assertTrue(validator.hasAllSpecialCharactersEscaped("Hey\\.there\\*buddy\\["));
        assertFalse(validator.hasAllSpecialCharactersEscaped("\\@"));
        assertFalse(validator.hasAllSpecialCharactersEscaped("Hey\\.there\\*buddy\\[]]"));
    }

    @Test
    public void testShouldReturnFalseIfSpecialCharactersAreNotEscaped() {
        assertFalse(validator.hasSpecialCharactersEscaped("Hello my friend."));
        assertFalse(validator.hasSpecialCharactersEscaped("Hello *my friend"));
        assertFalse(validator.hasSpecialCharactersEscaped("Hello +my friend"));
        assertFalse(validator.hasSpecialCharactersEscaped("[]Hello my friend"));
    }

    @Test
    public void testShouldMatchLastEscapedCharacted() {
        assertEquals("*", validator.getLastEscapedSpecialCharacter("hola como est \\+as\\*"));
        assertEquals("[", validator.getLastEscapedSpecialCharacter("hola como est \\.as\\["));
        assertEquals("]", validator.getLastEscapedSpecialCharacter("hola como est \\+as\\]"));
        assertEquals("+", validator.getLastEscapedSpecialCharacter("hola como est \\*as\\+"));
        assertEquals(".", validator.getLastEscapedSpecialCharacter("hola como est \\.as\\."));
    }

    @Test
    public void testSpecialCharactersCount() {
        assertEquals(2, validator.getSpecialCharacterCount("** asd asda sd"));
        assertEquals(4, validator.getSpecialCharacterCount("** as]d asd?a sd"));
        assertEquals(3, validator.getSpecialCharacterCount("** asd as]da sd"));
        assertEquals(5, validator.getSpecialCharacterCount("** a.sd as*da s[d"));
        assertNotEquals(10, validator.getSpecialCharacterCount("** a.sd as*da s[d"));
    }

    @Test
    public void testSpecialEscapedCharacterCount() {
        assertEquals(2, validator.getSpecialCharactersEscapedCount("***\\*\\+."));
        assertEquals(4, validator.getSpecialCharactersEscapedCount("\\[\\][\\*\\+."));
        assertNotEquals(6, validator.getSpecialCharactersEscapedCount("[][\\*\\+."));
    }
}
