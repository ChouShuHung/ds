package com.innovasolutions.ds.config.service.security.rules;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;
import static com.innovasolutions.ds.service.security.rules.Impl.CharacterRules.ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterRulesTests {

    @Autowired
    @Qualifier("characterRules")
    private IValidationRules characterRules;


    @Test
    public void testAtLeastOneDigitAndLetter() {
        // The validation should pass.
        assertEquals(PASS_VALIDATION, characterRules.validate("passw0rd"));
    }

    @Test
    public void testOnlyLowerCaseLetters() {
        // The validation should fail.  It needs a digit as well.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("password"));
    }

    @Test
    public void testUpperCaseLetters() {
        // The validation should fail. Capital letter is not allowed.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("PASSWORD"));
    }

    @Test
    public void testOnlyOneLetter() {
        // The validation should fail. It needs a digit as well.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("p"));
    }

    @Test
    public void testOnlyOneDigits() {
        // The validation should fail. It needs a letter as well.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("0"));
    }

    @Test
    public void testOnlyDigits() {
        // The validation should fail. It needs a letter as well.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("00000"));
    }

    @Test
    public void testSymbols() {
        // The validation should fail. Only letter and digit.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("pa$$w0rd"));
    }

    @Test
    public void testWhitespace() {
        // The validation should fail. Only letter and digit.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate("  "));
    }

    @Test
    public void testEmptyString() {
        // The validation should fail. Only letter and digit.
        assertEquals(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT, characterRules.validate(""));
    }
}
