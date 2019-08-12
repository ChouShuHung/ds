package com.innovasolutions.ds.config.service.security.password;

import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;
import static com.innovasolutions.ds.service.security.rules.Impl.CharacterRules.ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT;
import static com.innovasolutions.ds.service.security.rules.Impl.LengthRules.ERROR_PASSWORD_LENGTH;
import static com.innovasolutions.ds.service.security.rules.Impl.SequenceRules.ERROR_PASSWORD_REPEATING_SEQUENCE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordValidationServiceTests {

    @Autowired
    IPasswordValidationService pwdValidationService;

    private Set<String> result;

    @Before
    public void setup() {
        result = new HashSet<>();
    }


    @Test
    public void testAtLeastOneDigitAndLetter() {
        result = pwdValidationService.validatePassword("pas5w0rd");
        // The validation should pass.
        assertTrue(result.contains(PASS_VALIDATION));

        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
    }

    @Test
    public void testOnlyLowerCaseLetters() {
        result = pwdValidationService.validatePassword("password");
        // The validation should fail.  It needs a digit as well.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testUpperCaseLetters() {
        result = pwdValidationService.validatePassword("PASSWORD");
        // The validation should fail. Capital letter is not allowed.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testOnlyOneLetter() {
        result = pwdValidationService.validatePassword("p");
        // The validation should fail. It needs a digit as well.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testOnlyOneDigits() {
        result = pwdValidationService.validatePassword("0");
        // The validation should fail. It needs a letter as well.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testOnlyDigits() {
        result = pwdValidationService.validatePassword("00000");
        // The validation should fail. It needs a letter as well.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testSymbols() {
        result = pwdValidationService.validatePassword("pa$$w0rd");
        // The validation should fail. Only letter and digit.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testWhitespace() {
        result = pwdValidationService.validatePassword(" ");
        // The validation should fail. Only letter and digit.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testEmptyString() {
        result = pwdValidationService.validatePassword("");
        // The validation should fail. Only letter and digit.
        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testLength5And12() {
        // The validation should pass. The length is between 5 to 12.
        result = pwdValidationService.validatePassword("pas51");
        // The validation should pass.
        assertTrue(result.contains(PASS_VALIDATION));

        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));

        result.clear();
        result = pwdValidationService.validatePassword("1234567890pa");
        // The validation should pass.
        assertTrue(result.contains(PASS_VALIDATION));

        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
    }

    @Test
    public void testLengthLessThan5() {
        result = pwdValidationService.validatePassword("1234");
        // The validation should fail. The length is less than 5;
        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));

        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(PASS_VALIDATION));
    }


    @Test
    public void testLengthGreaterThan12() {
        result = pwdValidationService.validatePassword("1234567890123");
        // The validation should fail. The length is greater than 12;
        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));

        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testNonRepeatingSequence() {
        result = pwdValidationService.validatePassword("pas5w0rd");
        // The validation should pass.
        assertTrue(result.contains(PASS_VALIDATION));

        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
    }

    @Test
    public void testSequenceRepeatLetters() {
        result = pwdValidationService.validatePassword("wordword");
        // The validation should fail. "word" is repeated.
        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));

        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testRepeatSingleLetter() {
        result = pwdValidationService.validatePassword("pp");
        // The validation should fail. "pp" is repeated.
        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));

        assertTrue(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testRepeatLettersAndDigits() {
        result = pwdValidationService.validatePassword("w0w0");
        // The validation should fail. "w0" is repeated
        assertTrue(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));

        assertTrue(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(PASS_VALIDATION));
    }

    @Test
    public void testSeparateDuplicatedLetters() {
        result = pwdValidationService.validatePassword("abc123abc");
        // The validation should pass.
        assertTrue(result.contains(PASS_VALIDATION));

        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
    }

    @Test
    public void testSeparateDuplicatedDigits() {
        result = pwdValidationService.validatePassword("123abc123");
        // The validation should pass.
        assertTrue(result.contains(PASS_VALIDATION));

        assertFalse(result.contains(ERROR_PASSWORD_LENGTH));
        assertFalse(result.contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(result.contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
    }
}
